package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.PaymentMapper;
import newint.vmart.entity.PaymentRead;
import newint.vmart.entity.PaymentWrite;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class PaymentRepo {
  @Inject
  AgroalDataSource pool;

  private static final String SELECT_QUERY = "SELECT * FROM get_payment(?)";
  private static final String INSERT_QUERY = "CALL add_payment(?, ?, ?, ?, ?, ?, ?)";

  public List<PaymentRead> getPayment(int userId) {
    List<PaymentRead> payments = Collections.emptyList();

    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_QUERY)) {
        stm.setInt(1, userId);

        try(ResultSet rs = stm.executeQuery()) {
          payments = new ArrayList<>();
          var mapper = new PaymentMapper();

          while (rs.next()) {
            payments.add(mapper.map(rs));
          }
          return payments;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean addPayment(PaymentWrite payment) {
    try(Connection connection = this.pool.getConnection()) {
      try(CallableStatement stm = connection.prepareCall(INSERT_QUERY)) {
        stm.setInt(1, payment.customerId());
        stm.setInt(2, payment.paymentTypeId());
        stm.setString(3, payment.cardNumber());
        stm.setString(4, payment.expMonth());
        stm.setString(5, payment.expYear());
        stm.setString(6, payment.cvv());
        stm.setBoolean(7, false);

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
