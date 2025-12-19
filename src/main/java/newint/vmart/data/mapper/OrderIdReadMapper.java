package newint.vmart.data.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import newint.vmart.entity.OrderIdRead;
import newint.vmart.entity.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderIdReadMapper implements ResultMapper<OrderIdRead> {
  public OrderIdRead map(final ResultSet rs) throws SQLException {
    return new OrderIdRead(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      rs.getString(5),
      rs.getString(6),
      rs.getString(7),
      rs.getString(8),
      toArray(rs.getString(9))
    );
  }

  private OrderItem[] toArray(String json) {
    var om = new ObjectMapper();

    try {
      OrderItem[] items = om.readValue(json, new TypeReference<OrderItem[]>() {});

      return items;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
