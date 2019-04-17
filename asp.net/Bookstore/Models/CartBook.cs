using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore.Models
{
    public class CartBook
    {
        public CartBook()
        {

        }

        public CartBook(int id, Cart cart, Book book, DateTime addedOn, int quantity) 
        {
            Id = id;
            Cart = cart;
            Book = book;
            AddedOn = addedOn;
            Quantity = quantity;
        }

        public int Id { get; set; }
        public Cart Cart { get; set; }
        public Book Book { get; set; }
        public DateTime AddedOn { get; set; }
        public int Quantity { get; set; }
    }
}
