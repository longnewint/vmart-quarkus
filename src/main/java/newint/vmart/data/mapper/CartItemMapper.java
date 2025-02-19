package newint.vmart.data.mapper;

import newint.vmart.entity.CartItemRead;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements ResultMapper<CartItemRead> {
  public CartItemRead map(final ResultSet rs) throws SQLException {
    return new CartItemRead(
      rs.getString(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      rs.getString(5)
    );
  }
}
