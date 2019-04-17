using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore.Models
{
    public class User
    {
        public User()
        {

        }

        public User(string id)
        {
            UserId = id;
        }

        public string UserId { get; set; }
    }
}
