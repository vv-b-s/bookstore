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
        private readonly string urlBooks;
        private readonly string urlCategories;

        public BooksController(SystemProperties systemProperties)
        {
            urlBooks = systemProperties.BackendApi + "/Books";
            urlCategories = systemProperties.BackendApi + "/Categories";
        }
        
        public IActionResult Index()
        {
            
            
            using (var client = new WebClient())
            {
                var booksJson = client.DownloadString(urlBooks);
                var books = JsonConvert.DeserializeObject<IEnumerable<Book>>(booksJson);


                var categoriesJson = client.DownloadString(urlCategories);
                var categories = JsonConvert.DeserializeObject<IEnumerable<Category>>(categoriesJson);

                var booksVM = new BooksViewModel(categories, books);

                return View(booksVM);
            }
        }
    }
}