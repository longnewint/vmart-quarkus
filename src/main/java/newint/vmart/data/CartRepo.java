package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.CartItemMapper;
import newint.vmart.entity.CartItemRead;
import newint.vmart.entity.CartItemWrite;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class CartRepo {
  @Inject
  AgroalDataSource pool;

  private static final String SELECT_QUERY = "SELECT * FROM get_cart(?, ?)";
  private static final String INSERT_QUERY = "CALL add_to_cart(?, ?, ?)";

  public List<CartItemRead> getCartItem(int cartId) {
    List<CartItemRead> cartItems = Collections.emptyList();

    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_QUERY)) {
        stm.setInt(1, cartId);
        stm.setInt(2, 101);

        try(ResultSet rs = stm.executeQuery()) {
          cartItems = new ArrayList<>();
          var mapper = new CartItemMapper();

          while (rs.next()) {
            cartItems.add(mapper.map(rs));
          }
          return cartItems;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean addCartItem(int cartId, CartItemWrite item) {
    try(Connection connection = this.pool.getConnection()) {
      try(CallableStatement stm = connection.prepareCall(INSERT_QUERY)) {
        stm.setInt(1, cartId);
        stm.setInt(2, item.productId());
        stm.setInt(3, item.quantity());

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
