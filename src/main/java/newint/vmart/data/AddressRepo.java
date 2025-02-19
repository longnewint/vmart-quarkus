package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.AddressMapper;
import newint.vmart.data.mapper.CartItemMapper;
import newint.vmart.entity.Address;
import newint.vmart.entity.CartItemRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class AddressRepo {
  @Inject
  AgroalDataSource pool;

  private static final String READ_QUERY = "SELECT * FROM get_address(?)";

  public List<Address> getAddress(int userId) {
    List<Address> addresses = Collections.emptyList();

    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(READ_QUERY)) {
        stm.setInt(1, userId);

        try(ResultSet rs = stm.executeQuery()) {
          addresses = new ArrayList<>();
          var mapper = new AddressMapper();

          while (rs.next()) {
            addresses.add(mapper.map(rs));
          }
          return addresses;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
