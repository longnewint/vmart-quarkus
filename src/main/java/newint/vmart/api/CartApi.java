package newint.vmart.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.entity.CartItem;
import org.jboss.resteasy.reactive.RestPath;

@Path("/cart/{cartId}")
public class CartApi {



  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public String get(@RestPath int cartId, CartItem item) {
    return String.valueOf(cartId).concat(item.productId);
  }
}
