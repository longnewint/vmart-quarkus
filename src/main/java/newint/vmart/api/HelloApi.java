package newint.vmart.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class HelloApi {
  @GET
  public String get() {
    return "Hello from Duke on Quarkus 3.15!";
  }
}
