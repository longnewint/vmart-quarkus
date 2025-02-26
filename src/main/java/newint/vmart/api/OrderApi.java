package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.api.validation.Result;
import newint.vmart.data.OrderRepo;
import newint.vmart.entity.CurrentOrderRead;
import newint.vmart.entity.OrderWrite;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/order")
public class OrderApi {
  @Inject OrderRepo repo;

  @GET
  @Path("/current/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<CurrentOrderRead> getCurrentOrder(@RestPath int userId) {
    return repo.getCurrentOrder(userId);
  }

  @POST
  @Path("/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void post(@RestPath int userId, OrderWrite order) {
    repo.addOrder(userId, order);
  }
}
