using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using Bookstore.Models;
using Microsoft.AspNetCore.Mvc;

namespace Bookstore.Controllers
{
    public class CartsController : Controller
    {
        private readonly string urlCarts;
        private readonly string ePayClientID;
        private readonly string ePaySecretKey;

        public CartsController(SystemProperties systemProperties)
        {
            urlCarts = systemProperties.BackendApi + "/Carts";
            ePayClientID = systemProperties.GetProperty("ePayClientID");
            ePaySecretKey = systemProperties.GetProperty("ePaySecretKey");
        }

        public IActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public async Task<ActionResult> Payment(int cartId)
        {
            decimal totalSum = (await GetCartItems(cartId)).Sum(cb => cb.Book.Price * cb.Quantity);

            var request = $"MIN={ePayClientID}\r\n" +
                $"INVOICE={cartId}\r\n" +
                $"AMOUNT={totalSum.ToString("G")}\r\n" +
                $"CURRENCY=BGN\r\n" +
                $"EXP_TIME={DateTime.Now.AddDays(2).ToString("dd.MM.yyyy")}\r\n" +
                $"DESCR=Buying books";

            var ePayRequest = new EpayRequest(Convert.ToBase64String(Encoding.ASCII.GetBytes(request)), re => HMACSHA1(re.Encoded, ePaySecretKey));

            return View(ePayRequest);
        }

        [HttpPost]
        public async Task<ActionResult> PaymentResponse(EpayRequest epay)
        {
            string encoded = epay.Encoded;
            string checksum = epay.Checksum;

            if (checksum != HMACSHA1(encoded, ePaySecretKey))
            {
                return BadRequest();
            }

            else
            {
                string decoded = Encoding.ASCII.GetString(Convert.FromBase64String(encoded));
                string[] kvPairs = decoded.Split(':'); string invoice = "", status = "";
                foreach (var pair in kvPairs)
                {
                    string[] kv = pair.Split('=');
                    switch (kv[0])
                    {
                        case "INVOICE":
                            invoice = kv[1];
                            break;
                        case "STATUS":
                            status = kv[1];
                            break;
                    }
                }


                if(status == "OK")
                {
                    await FinalizeOrder(int.Parse(invoice));
                }

                return Content($"INVOICE={invoice}:STATUS={status}");
            }
        }


        public async Task<Cart> GetCart(int cartId)
        {
            var target = $"{urlCarts}/{cartId}";
            return await REST.SendGet<Cart>(target);
        }

        public async Task<IEnumerable<CartBook>> GetCartItems(int cartId)
        {
            var target = $"{urlCarts}/{cartId}/Items";
            return await REST.SendGet<IEnumerable<CartBook>>(target);
        }

        public async Task<Cart> CreateCart(User user)
        {
            return await REST.SendPost<Cart, User>(urlCarts, user);
        }

        public async Task<CartBook> AddBook(int cartId, int bookId)
        {
            var target = $"{urlCarts}/{cartId}/Items?bookId={bookId}";
            return await REST.SendPost<CartBook>(target);
        }

        public void DeleteCartBook(int cartId, int bookId)
        {
            var target = $"{urlCarts}/{cartId}/Items?bookId={bookId}";
            REST.SendDelete(target);
        }

        public async Task<Cart> EditCart(Cart cart)
        {
            return await REST.SendPut(urlCarts, cart);
        }

        public async Task<Cart> FinalizeOrder(int cartId)
        {
            var target = $"{urlCarts}/{cartId}";
            return await REST.SendPatch<Cart>(target);
        }

        private string HMACSHA1(string text, string secretkey)
        {
            byte[] byteArray = Encoding.ASCII.GetBytes(text);
            byte[] key = Encoding.ASCII.GetBytes(secretkey);
            using (var h = new HMACSHA1(key))
            {
                var hashArray = h.ComputeHash(byteArray);
                var result = new StringBuilder();
                foreach (var b in hashArray)
                {
                    result.Append(b.ToString("X2"));
                }
                return result.ToString();
            }
        }
    }
}