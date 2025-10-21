package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.data.ProductRepo;
import newint.vmart.entity.ProductIdRead;
import newint.vmart.entity.ProductRead;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;
import java.util.Optional;

@Path("/product")
public class ProductApi {
  @Inject ProductRepo repo;

  @GET
  @Path("/c/{categoryId}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ProductRead> get(@RestPath int categoryId, @RestQuery int storeId) {
    return repo.getProduct(storeId, categoryId);
  }

  @GET
  @Path("/{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Optional<ProductIdRead> getById(@RestPath int productId, @RestQuery int storeId) {
    return repo.getProductById(storeId, productId);
  }
}
