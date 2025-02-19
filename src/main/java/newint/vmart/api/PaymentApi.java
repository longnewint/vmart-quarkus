package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import newint.vmart.data.PaymentRepo;
import newint.vmart.entity.Payment;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/payment/{userId}")
public class PaymentApi {
  @Inject PaymentRepo repo;

  @GET
  public List<Payment> get(@RestPath int userId) {
    return repo.getPayment(userId);
  }
}
