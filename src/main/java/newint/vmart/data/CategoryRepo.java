package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.ProductCategoryMapper;
import newint.vmart.entity.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CategoryRepo {
  @Inject
  AgroalDataSource pool;

  private static final String SQL = "SELECT * FROM get_product(?, ?)";

  public List<ProductCategory> getProduct(int storeId, int categoryId) {
    List<ProductCategory> products = Collections.emptyList();

    try (Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SQL)) {
        stm.setInt(1, storeId);
        stm.setInt(2, categoryId);

        try(ResultSet rs = stm.executeQuery()) {
          products = new ArrayList<>();
          var mapper = new ProductCategoryMapper();

          while(rs.next()) {
            products.add(mapper.map(rs));
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
