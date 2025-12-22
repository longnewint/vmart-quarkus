package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.ProductReadMapper;
import newint.vmart.data.mapper.ProductIdReadMapper;
import newint.vmart.entity.ProductIdRead;
import newint.vmart.entity.ProductRead;

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

  private static final String SELECT_BY_CATEGORY_QUERY = "SELECT * FROM get_product_by_category(?, ?)";
  private static final String SELECT_BY_ID_QUERY = "SELECT * FROM get_product_by_id(?, ?)";

  public List<ProductRead> getProduct(int storeId, int categoryId) {
    List<ProductRead> products = Collections.emptyList();

    try (Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_BY_CATEGORY_QUERY)) {
        stm.setInt(1, categoryId);
        stm.setInt(2, storeId);

        try(ResultSet rs = stm.executeQuery()) {
          products = new ArrayList<>();
          var mapper = new ProductReadMapper();

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


  public Optional<ProductIdRead> getProductById(int storeId, int productId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
        stm.setInt(1, productId);
        stm.setInt(2, storeId);

        try(ResultSet rs = stm.executeQuery()) {
          if(rs.next()) {
            ProductIdReadMapper mapper = new ProductIdReadMapper();
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
