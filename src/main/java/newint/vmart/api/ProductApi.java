package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import newint.vmart.data.CategoryRepo;
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

  @Inject
  CategoryRepo other;

  @GET
  @Path("/c/{categoryId}")
  public List<ProductCategory> getByCategory(@RestPath int categoryId, @RestQuery int storeId)) {
    return other.getProduct(101, categoryId);
  }

  @GET
  @Path("/{productId}")
  public Optional<Product> getById(@RestPath int productId, @RestQuery int storeId) {
    return repo.getProduct(storeId, productId);
  }
}
