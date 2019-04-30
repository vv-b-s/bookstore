using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore.Models
{
    public class EpayRequest
    {
        public EpayRequest()
        {

        }

        public EpayRequest(string encoded, Func<EpayRequest, string> checksumFuction)
        {
            Encoded = encoded;
            Checksum = checksumFuction(this);
        }

        public string Encoded { get; set; }
        public string Checksum { get; set; }
    }
}
