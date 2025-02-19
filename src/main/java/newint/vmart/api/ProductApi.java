package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import newint.vmart.data.ProductRepo;
import newint.vmart.entity.Product;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.Optional;

@Path("/product/{productId}")
public class ProductApi {
  @Inject ProductRepo repo;

  @GET
  public Optional<Product> get(@RestPath int productId, @RestQuery int storeId) {
    return repo.getProduct(productId, storeId);
  }
}
