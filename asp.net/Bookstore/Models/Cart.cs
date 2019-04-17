using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore.Models
{
    public class Cart
    {
        public Cart()
        {

        }

        public Cart(int id, string userId, DateTime createdOn, DateTime lastModification, bool isPaid, string paymentMethod, string deliveryOption)
        {
            Id = id;
            UserId = userId;
            CreatedOn = createdOn;
            LastModification = lastModification;
            IsPaid = isPaid;
            PaymentMethod = paymentMethod;
            DeliveryOption = deliveryOption;
        }

        public int Id { get; set; }
        public string UserId { get; set; }
        public DateTime CreatedOn { get; set; }
        public DateTime LastModification { get; set; }
        public bool IsPaid { get; set; }
        public string PaymentMethod { get; set; }
        public string DeliveryOption { get; set; }

    }
}
