package newint.vmart.data.mapper;

import newint.vmart.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements ResultMapper<Payment> {
  public Payment map(final ResultSet rs) throws SQLException {
    return new Payment(
      rs.getInt(1),
      rs.getInt(2),
      rs.getString(3),
      rs.getBoolean(4)
    );
  }
}
