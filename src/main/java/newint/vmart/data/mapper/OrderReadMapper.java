package newint.vmart.data.mapper;

import newint.vmart.entity.OrderRead;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderReadMapper implements ResultMapper<OrderRead> {
  public OrderRead map(final ResultSet rs) throws SQLException {
    return new OrderRead(
      rs.getInt(1),
      rs.getString(2),
      rs.getInt(3),
      rs.getTimestamp(4).getTime(),
      rs.getFloat(5),
      rs.getInt(6)
    );
  }
}
