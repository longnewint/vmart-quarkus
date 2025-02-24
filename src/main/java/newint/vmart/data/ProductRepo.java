package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.ProductCategoryMapper;
import newint.vmart.data.mapper.ProductMapper;
import newint.vmart.entity.Product;
import newint.vmart.entity.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductRepo {
  @Inject AgroalDataSource pool;

  private static final String SELECT_BY_CATEGORY_QUERY = "SELECT * FROM get_product(?, ?)";
  private static final String SELECT_BY_ID_QUERY = "SELECT * FROM get_product_by_id(?, ?)";

  public List<ProductCategory> getProductByCategory(int storeId, int categoryId) {
    List<ProductCategory> products = Collections.emptyList();

    try (Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_BY_CATEGORY_QUERY)) {
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


  public Optional<Product> getProductById(int storeId, int productId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
        stm.setInt(1, storeId);
        stm.setInt(2, productId);

        try(ResultSet rs = stm.executeQuery()) {
          if(rs.next()) {
            ProductMapper mapper = new ProductMapper();
            return Optional.ofNullable(mapper.map(rs));
          }
          else
            return Optional.empty();
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
