package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.api.validation.Result;
import newint.vmart.entity.CartItem;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Set;

@Path("/cart/{cartId}")
public class CartApi {
  @Inject
  Validator validator;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Result get(@RestPath int cartId, @Valid CartItem item) {
    Set<ConstraintViolation<CartItem>> violations = validator.validate(item);

    if (violations.isEmpty()) {
      return new Result("Item added to cart!");
    } else {
      return new Result(violations);
    }
  }
}
