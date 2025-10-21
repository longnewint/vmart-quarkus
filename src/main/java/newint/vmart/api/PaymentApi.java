package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.api.validation.Result;
import newint.vmart.data.PaymentRepo;
import newint.vmart.entity.PaymentRead;
import newint.vmart.entity.PaymentWrite;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;
import java.util.Set;

@Path("/payment")
public class PaymentApi {
  @Inject PaymentRepo repo;

  @Inject Validator validator;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<PaymentRead> get() {
    return repo.getPayment(12321);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public int post(@Valid PaymentWrite payment) {
      return repo.addPayment(12321, payment);
  }
}
