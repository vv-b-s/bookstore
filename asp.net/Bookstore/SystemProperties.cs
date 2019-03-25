using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore
{
    public class SystemProperties
    {
        private string BackendAPI;

        public SystemProperties(string backendApi)
        {
            this.BackendAPI = backendApi;
        }

        public string BackendApi => BackendAPI;
    }
}
