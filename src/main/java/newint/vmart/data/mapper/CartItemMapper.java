package newint.vmart.data.mapper;

import newint.vmart.entity.CartItemRead;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements ResultMapper<CartItemRead> {
  public CartItemRead map(final ResultSet rs) throws SQLException {
    return new CartItemRead(
      rs.getInt(1),
      rs.getString(2),
      rs.getFloat(3),
      rs.getString(4),
      rs.getInt(5)
    );
  }
}
