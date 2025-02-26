package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.data.ProductRepo;
import newint.vmart.entity.Product;
import newint.vmart.entity.ProductCategory;
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
  public List<ProductCategory> getByCategory(@RestPath int categoryId, @RestQuery int storeId) {
    return repo.getProductByCategory(storeId, categoryId);
  }

  @GET
  @Path("/{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Optional<Product> getById(@RestPath int productId, @RestQuery int storeId) {
    return repo.getProductById(storeId, productId);
  }
}
