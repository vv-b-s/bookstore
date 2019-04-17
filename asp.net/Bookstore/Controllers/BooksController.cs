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

        public async Task<IActionResult> Index(int? categoryId)
        {
            var endpoint = categoryId == null ? urlBooks : urlBooks + $"/Category/{categoryId.Value}";

            using (var client = new HttpClient())
            {
                var books = await REST.SendGet<IEnumerable<Book>>(urlBooks, client);
                var categories = await REST.SendGet<IEnumerable<Category>>(urlCategories, client);

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
            return await REST.SendGet<Book>(endpoint);
        }

        private async Task<IEnumerable<GoogleBook>> GetSimilarBooks(string title)
        {
            var endpoint = $"{urlBooks}/Like?bookName={title}";

            return await REST.SendGet<IEnumerable<GoogleBook>>(endpoint);
        }


    }
}