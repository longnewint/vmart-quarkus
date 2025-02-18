package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.entity.CartItemRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CartRepo {
  @Inject
  AgroalDataSource pool;

  private static final String READ_QUERY = "SELECT * FROM get_cart(?, ?)";

  public List<CartItemRead> getCartItem(int cartId, int storeId) {
    List<CartItemRead> cartItems = Collections.emptyList();

    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(READ_QUERY)) {
        stm.setInt(1, cartId);
        stm.setInt(2, 101);

        try(ResultSet rs = stm.executeQuery()) {
          cartItems = new ArrayList<>();

          while (rs.next()) {
            cartItems.add(new CartItemRead(
              rs.getInt(1),
              rs.getString(2),
              rs.getFloat(3),
              rs.getString(4),
              rs.getInt(5)
            ));
          }
          return cartItems;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
