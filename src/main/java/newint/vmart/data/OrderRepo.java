package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.CurrentOrderMapper;
import newint.vmart.entity.CurrentOrderRead;
import newint.vmart.entity.OrderWrite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderRepo {
  @Inject AgroalDataSource pool;

  private static final String SELECT_CURRENT_ORDER_QUERY = "SELECT * FROM get_current_order(?)";
  private static final String INSERT_QUERY = "CALL create_order(?, ?, ?, ?, ?, ?)";
  public List<CurrentOrderRead> getCurrentOrder(int userId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement ps = connection.prepareStatement(SELECT_CURRENT_ORDER_QUERY)) {
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

  public boolean addOrder(int userId, OrderWrite order) {
    try(Connection connection = pool.getConnection()) {
      try(CallableStatement stm = connection.prepareCall(INSERT_QUERY)) {
        stm.setInt(1, userId);
        stm.setInt(2, order.storeId());
        stm.setInt(3, order.cartId());
        stm.setInt(4, order.shippingMethodId());
        stm.setInt(5, order.addressId());
        stm.setInt(6, order.paymentMethodId());

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
