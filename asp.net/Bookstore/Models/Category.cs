using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore.Models
{
    public class Category
    {
        public Category()
        {

        }

        public Category(string name)
        {
            this.Name = name;
        }

        public int Id { get; set; }
        public string Name { get; set; }
    }
}
