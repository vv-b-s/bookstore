using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Bookstore.Models;
using DefaultNamespace;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace Bookstore.Controllers
{
    public class BooksController : Controller
    {
        private readonly string urlBooks;
        private readonly string urlCategories;

        public BooksController(SystemProperties systemProperties)
        {
            urlBooks = systemProperties.BackendApi + "/Books";
            urlCategories = systemProperties.BackendApi + "/Categories";
        }

        public IActionResult Index(int? categoryId)
        {
            var endpoint = categoryId == null ? urlBooks : urlBooks + $"/Category/{categoryId.Value}";

            using (var client = new WebClient())
            {
                var booksJson = client.DownloadString(endpoint);
                var books = JsonConvert.DeserializeObject<IEnumerable<Book>>(booksJson);


                var categoriesJson = client.DownloadString(urlCategories);
                var categories = JsonConvert.DeserializeObject<IEnumerable<Category>>(categoriesJson);

                var booksVM = new BooksViewModel(categories, books);

                return View(booksVM);
            }
        }

        public async Task<IActionResult> Get(int id)
        {

            Book book = await GetBook(id);
            ViewBag.SimilarBooks = await GetSimilarBooks(book.Title);


            return View(book);
        }

        public async Task<IActionResult> Cover(int id)
        {
            var book = await GetBook(id);
            var cover = new byte[book.Cover.Length];

            for (int i = 0; i < cover.Length; i++)
            {
                cover[i] = (byte)book.Cover[i];
            }

            return File(cover, "image/jpeg");
        }

        private async Task<Book> GetBook(int bookId)
        {
            var endpoint = urlBooks + $"/{bookId}";
            using (var client = new HttpClient())
            {
                var bookJson = await client.GetStringAsync(endpoint);
                var book = JsonConvert.DeserializeObject<Book>(bookJson);

                return book;
            }
        }

        private async Task<IEnumerable<GoogleBook>> GetSimilarBooks(string title)
        {
            var endpoint = $"{urlBooks}/Like?bookName={title}";

            using (var client = new HttpClient())
            {
                var booksJson = await client.GetStringAsync(endpoint);
                var books = JsonConvert.DeserializeObject<IEnumerable<GoogleBook>>(booksJson);

                return books;
            }
        }


    }
}