package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.entity.ProductCategoryView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@ApplicationScoped
public class CategoryRepo {
  @Inject
  AgroalDataSource pool;

  private static final String SQL = "SELECT * FROM get_product_view(?, ?)";

  public ArrayList<ProductCategoryView> getProductByCategory(int storeId, int categoryId) {
    ArrayList<ProductCategoryView> products = new ArrayList<>();

    try (Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SQL)) {
        stm.setInt(1, storeId);
        stm.setInt(2, categoryId);

        try(ResultSet rs = stm.executeQuery()) {
          while(rs.next()) {
            products.add(new ProductCategoryView(
              rs.getString(1),
              rs.getString(2),
              rs.getString(3),
              rs.getString(4),
              rs.getString(5),
              rs.getString(6)));
          }

          return products;
        }
      }
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
