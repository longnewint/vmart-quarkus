package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.CurrentOrderMapper;
import newint.vmart.entity.CurrentOrderRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderRepo {
  @Inject AgroalDataSource pool;

  private static final String SELECT_CURRENT_ORDER = "SELECT * FROM get_current_order(?)";

  public List<CurrentOrderRead> getCurrentOrder(int userId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement ps = connection.prepareStatement(SELECT_CURRENT_ORDER)) {
        ps.setInt(1, userId);

        try(ResultSet rs = ps.executeQuery()) {
          List<CurrentOrderRead> currentOrders = new ArrayList<>();
          CurrentOrderMapper mapper = new CurrentOrderMapper();

          while(rs.next()) {
            currentOrders.add(mapper.map(rs));
          }

          return currentOrders;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
