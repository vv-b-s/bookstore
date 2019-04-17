using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Bookstore.Models;
using Microsoft.AspNetCore.Mvc;

namespace Bookstore.Controllers
{
    public class CartController : Controller
    {
        private readonly string urlCarts;

        public CartController(SystemProperties systemProperties)
        {
            urlCarts = systemProperties.BackendApi + "/Carts";
        }

        public IActionResult Index()
        {
            return View();
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
            return await REST.SendPut(urlCarts,cart);
        }

        public async Task<Cart> FinalizeOrder(int cartId)
        {
            var target = $"{urlCarts}/{cartId}";
            return await REST.SendPatch<Cart>(target);
        }
    }
}