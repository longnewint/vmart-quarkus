package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.OrderIdReadMapper;
import newint.vmart.data.mapper.OrderReadMapper;
import newint.vmart.entity.OrderIdRead;
import newint.vmart.entity.OrderRead;
import newint.vmart.entity.OrderWrite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepo {
  @Inject AgroalDataSource pool;

  private static final String GET_ORDER_QUERY = "SELECT * FROM get_order(?)";
  private static final String GET_ORDER_BY_ID_QUERY = "SELECT * FROM get_order_by_id(?)";
  private static final String INSERT_QUERY = "CALL create_order(?, ?, ?, ?, ?, ?)";
  private static final String UPDATE_STATUS = "UPDATE vmart_order SET order_status_id = ? WHERE order_id = ?";

  public Optional<OrderIdRead> getOrderById(int orderId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement ps = connection.prepareStatement(GET_ORDER_BY_ID_QUERY)) {
        ps.setInt(1, orderId);

        try(ResultSet rs = ps.executeQuery()) {

          if(rs.next()) {
            var mapper = new OrderIdReadMapper();
            return Optional.ofNullable(mapper.map(rs));
          }
          else
            return Optional.empty();
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<OrderRead> getOrder(int userId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement ps = connection.prepareStatement(GET_ORDER_QUERY)) {
        ps.setInt(1, userId);

        try(ResultSet rs = ps.executeQuery()) {
          List<OrderRead> orders = new ArrayList<>();
          var mapper = new OrderReadMapper();

          while(rs.next()) {
            orders.add(mapper.map(rs));
          }

          return orders;
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
        stm.setInt(6, order.paymentId());

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateOrderStatus(int status, int orderId) {
    try(Connection conn = pool.getConnection()) {
      try(PreparedStatement stm = conn.prepareStatement(UPDATE_STATUS)) {
        stm.setInt(1, status);
        stm.setInt(2, orderId);

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
