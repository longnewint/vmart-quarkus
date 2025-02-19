package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import newint.vmart.data.CategoryRepo;
import newint.vmart.entity.ProductCategory;
import org.jboss.resteasy.reactive.RestPath;

import java.util.ArrayList;
import java.util.List;

@Path("/category/{categoryId}")
public class CategoryApi {
  @Inject
  CategoryRepo repo;

  @GET
  public List<ProductCategory> get(@RestPath int categoryId) {
    return repo.getProduct(101, categoryId);
  }
}
