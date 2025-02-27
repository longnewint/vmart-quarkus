package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.api.validation.Result;
import newint.vmart.data.CartRepo;
import newint.vmart.entity.CartItemRead;
import newint.vmart.entity.CartItemWrite;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;
import java.util.Set;

@Path("/cart/{cartId}")
public class CartApi {
  @Inject CartRepo repo;

  @Inject Validator validator;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<CartItemRead> get(@RestPath int cartId) {
    return repo.getCartItem(cartId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Result post(@RestPath int cartId, @Valid CartItemWrite item) {
    Set<ConstraintViolation<CartItemWrite>> violations = validator.validate(item);

    if (violations.isEmpty()) {
      boolean isSuccessful = repo.addCartItem(cartId, item);

      return new Result(true, "Operation succeeds!");
    } else {
      return new Result(violations);
    }
  }
}
