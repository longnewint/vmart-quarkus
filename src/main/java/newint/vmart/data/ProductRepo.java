package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.ProductMapper;
import newint.vmart.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductRepo {
  @Inject AgroalDataSource pool;

  private static final String SELECT_QUERY = "SELECT * FROM get_product_by_id(?, ?)";

  public Optional<Product> getProduct(int productId, int storeId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_QUERY)) {
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
