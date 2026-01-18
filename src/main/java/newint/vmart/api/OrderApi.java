package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.data.OrderRepo;
import newint.vmart.entity.OrderIdRead;
import newint.vmart.entity.OrderRead;
import newint.vmart.entity.OrderStatusWrite;
import newint.vmart.entity.OrderWrite;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;
import java.util.Optional;

@Path("/order")
public class OrderApi {
  @Inject OrderRepo repo;

  private static final Logger LOG = Logger.getLogger(OrderApi.class);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<OrderRead> getOrder(@RestPath int userId) {
    return repo.getOrder(12321);
  }

  @GET
  @Path("/{orderId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Optional<OrderIdRead> getOrderById(@RestPath int orderId) {
    return repo.getOrderById(orderId);
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void post(OrderWrite order) {
    LOG.info(order.toString());
    boolean isSuccessful = repo.addOrder(12321, order);
  }

  @POST
  @Path("/status")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void post(OrderStatusWrite osw) {
    boolean isSuccessful = repo.updateOrderStatus(osw.orderStatusId(), osw.orderId());
  }
}
