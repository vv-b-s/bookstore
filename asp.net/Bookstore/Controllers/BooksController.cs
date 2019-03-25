using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Bookstore.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace Bookstore.Controllers
{
    public class BooksController : Controller
    {
        private const string UrlBooks = Constants.BackendHome + "/Books";
        private const string UrlCategories = Constants.BackendHome + "/Categories";

        public IActionResult Index()
        {
            using (var client = new WebClient())
            {
                var booksJson = client.DownloadString(UrlBooks);
                var books = JsonConvert.DeserializeObject<IEnumerable<Book>>(booksJson);


                var categoriesJson = client.DownloadString(UrlCategories);
                var categories = JsonConvert.DeserializeObject<IEnumerable<Category>>(categoriesJson);

                var booksVM = new BooksViewModel(categories, books);

                return View(booksVM);
            }
        }
    }
}