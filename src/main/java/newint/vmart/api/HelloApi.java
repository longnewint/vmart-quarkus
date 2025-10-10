package newint.vmart.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloApi {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String get() {
    return "Hello from Duke on Quarkus 3.15!";
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public void post(String message) { System.out.println(message); }
}
