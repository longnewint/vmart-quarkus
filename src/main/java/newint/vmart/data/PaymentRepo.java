package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.AddressMapper;
import newint.vmart.data.mapper.PaymentMapper;
import newint.vmart.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class PaymentRepo {
  @Inject
  AgroalDataSource pool;

  private static final String READ_QUERY = "SELECT * FROM get_payment(?)";

  public List<Payment> getPayment(int userId) {
    List<Payment> payments = Collections.emptyList();

    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(READ_QUERY)) {
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
}
