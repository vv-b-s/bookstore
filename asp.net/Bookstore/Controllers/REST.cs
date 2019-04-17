using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace Bookstore.Controllers
{
    public static class REST
    {
        public static async Task<TResult> SendGet<TResult>(string target)
        {
            using (var client = new HttpClient())
            {
                return await SendGet<TResult>(target, client);
            }
        }

        public static async Task<TResult> SendGet<TResult>(string target, HttpClient client)
        {
            var dtoJSON = await client.GetStringAsync(target);
            var result = JsonConvert.DeserializeObject<TResult>(dtoJSON);

            return result;
        }

        public static async Task<TResult> SendPost<TResult>(string target)
        {
            using (var client = new HttpClient())
            {
                return await SendPost<TResult>(target, client);
            }
        }

        public static async Task<TResult> SendPost<TResult, TBody>(string target, TBody body)
        {
            using(var client = new HttpClient())
            {
                return await SendPost<TResult, TBody>(target, body, client);
            }
        }

        public static async Task<TResult> SendPost<TResult>(string target, HttpClient client)
        {
            var dtoJSON = await client.PostAsync(target, null).Result.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<TResult>(dtoJSON);
        }

        public static async Task<TResult> SendPost<TResult, TBody>(string target, TBody body, HttpClient client)
        {
            var dtoJSON = await client.PostAsJsonAsync(target, body).Result.Content.ReadAsStringAsync();
            var result = JsonConvert.DeserializeObject<TResult>(dtoJSON);
           
            return result;
        }

        public static async Task<TItem> SendPut<TItem>(string target, TItem item)
        {
            using(var client = new HttpClient())
            {
                return await SendPut(target, item, client);
            }
        }

        public static async Task<TItem> SendPut<TItem>(string target, TItem item, HttpClient client)
        {
            var dtoJSON = await client.PutAsJsonAsync(target, item).Result.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<TItem>(dtoJSON);
        }

        public static void SendDelete(string target)
        {
            using(var client = new HttpClient())
            {
                SendDelete(target, client);
            }
        }

        public static async Task<TResult> SendPatch<TResult>(string target)
        {
            using (var client = new HttpClient())
            {
                return await SendPatch<TResult>(target);
            }
        }

        public static async Task<TResult> SendPatch<TResult>(string target, HttpClient client)
        {
            var dtoJson = await client.PatchAsync(target, null).Result.Content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<TResult>(dtoJson);
        }

        public static void SendDelete(string target, HttpClient client)
        {
            client.DeleteAsync(target);
        }
    }
}
