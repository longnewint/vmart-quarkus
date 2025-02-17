package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import newint.vmart.data.CategoryRepo;
import newint.vmart.entity.ProductCategoryView;
import org.jboss.resteasy.reactive.RestPath;

import java.util.ArrayList;

@Path("/category/{categoryId}")
public class CategoryApi {
  @Inject
  CategoryRepo repo;

  @GET
  public ArrayList<ProductCategoryView> get(@RestPath int categoryId) {
    return repo.getProductByCategory(101, categoryId);
  }
}
