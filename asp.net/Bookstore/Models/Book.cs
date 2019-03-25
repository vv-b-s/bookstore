using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore.Models
{
    public class Book
    {
        public Book()
        {

        }

        public Book(string title, int year, decimal price, sbyte[] cover, string isbn, Category category)
        {
            Title = title;
            Year = year;
            Price = price;
            Cover = cover;
            ISBN = isbn;
            Category = category;

        }
       

        public int Id { get; set; }
        public string Title { get; set; }
        public int Year { get; set; }
        public decimal Price { get; set; }
        public sbyte[] Cover { get; set; }
        public string ISBN { get; set; }
        public Category Category { get; set; }
    }
}
