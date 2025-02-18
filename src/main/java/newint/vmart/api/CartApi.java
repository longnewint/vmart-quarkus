package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.api.validation.Result;
import newint.vmart.entity.CartItemWrite;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Set;

@Path("/cart/{cartId}")
public class CartApi {
  @Inject
  Validator validator;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String get(@RestPath int cartId) {
    return String.valueOf(cartId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Result get(@RestPath int cartId, @Valid CartItemWrite item) {
    Set<ConstraintViolation<CartItemWrite>> violations = validator.validate(item);

    if (violations.isEmpty()) {
      return new Result("Item added to cart!");
    } else {
      return new Result(violations);
    }
  }
}
