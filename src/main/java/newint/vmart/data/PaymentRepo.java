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
  private static final String INSERT_QUERY = "SELECT add_payment(?, ?, ?, ?, ?, ?)";

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

  public int addPayment(int userId, PaymentWrite payment) {
    int paymentId = 0;

    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(INSERT_QUERY)) {
        stm.setInt(1, userId);
        stm.setInt(2, payment.paymentTypeId());
        stm.setString(3, payment.cardNumber());
        stm.setString(4, payment.expMonth());
        stm.setString(5, payment.expYear());
        stm.setString(6, payment.cvv());

        try(ResultSet rs = stm.executeQuery()) {
          if(rs.next())
            paymentId = rs.getInt(1);

          return paymentId;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
