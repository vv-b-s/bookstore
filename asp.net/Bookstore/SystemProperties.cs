using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Bookstore
{
    public class SystemProperties
    {
        private IConfiguration configuration;

        public SystemProperties(IConfiguration configuration)
        {
            this.configuration = configuration;
        }

        public string BackendApi => configuration["BackendAPI"];

        public string GetProperty(string propertyName) => configuration[propertyName];
    }
}
