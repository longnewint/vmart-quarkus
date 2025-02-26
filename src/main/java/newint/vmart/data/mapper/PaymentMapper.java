package newint.vmart.data.mapper;

import newint.vmart.entity.PaymentRead;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements ResultMapper<PaymentRead> {
  public PaymentRead map(final ResultSet rs) throws SQLException {
    return new PaymentRead(
      rs.getInt(1),
      rs.getInt(2),
      rs.getString(3),
      rs.getBoolean(4)
    );
  }
}
