package bg.unwe.bookstore.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class HelloService {

    @WebMethod
    public String sayHello() {
        return "Hello World!";
    }
}
