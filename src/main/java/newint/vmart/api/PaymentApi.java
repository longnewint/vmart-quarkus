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

@Path("/payment/{userId}")
public class PaymentApi {
  @Inject PaymentRepo repo;

  @Inject Validator validator;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<PaymentRead> get(@RestPath int userId) {
    return repo.getPayment(userId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Result post(@RestPath int userId, @Valid PaymentWrite payment) {
    Set<ConstraintViolation<PaymentWrite>> violations = validator.validate(payment);

    if(violations.isEmpty()) {
      boolean isSuccessful = repo.addPayment(userId, payment);

      return new Result(true, "Operation successful!");
    }
    else
      return new Result(violations);
  }
}
