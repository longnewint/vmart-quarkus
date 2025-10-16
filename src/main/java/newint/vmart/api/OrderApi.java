package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.data.OrderRepo;
import newint.vmart.entity.OrderRead;
import newint.vmart.entity.OrderWrite;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/order")
public class OrderApi {
  @Inject OrderRepo repo;

  @GET
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<OrderRead> getOrder(@RestPath int userId) {
    return repo.getOrder(userId);
  }

  @POST
  @Path("/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void post(@RestPath int userId, OrderWrite order) {
    boolean isSuccessful = repo.addOrder(userId, order);
  }
}
