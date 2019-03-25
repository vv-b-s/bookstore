using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore.Models
{
    public class BooksViewModel
    {
        public BooksViewModel(IEnumerable<Category> categories, IEnumerable<Book> books)
        {
            Categories = categories;
            Books = books;
        }

        public IEnumerable<Category> Categories { get; set; }
        public IEnumerable<Book> Books { get; set; }
    }
}
