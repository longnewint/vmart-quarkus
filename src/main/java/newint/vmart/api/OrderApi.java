package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.data.OrderRepo;
import newint.vmart.entity.CurrentOrder;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/order")
public class OrderApi {
  @Inject OrderRepo repo;

  @GET
  @Path("/current/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<CurrentOrder> getCurrentOrder(@RestPath int userId) {
    return repo.getCurrentOrder(userId);
  }
}
