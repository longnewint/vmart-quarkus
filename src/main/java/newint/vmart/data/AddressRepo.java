package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.AddressMapper;
import newint.vmart.data.mapper.CartItemMapper;
import newint.vmart.entity.AddressRead;
import newint.vmart.entity.AddressWrite;
import newint.vmart.entity.CartItemRead;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class AddressRepo {
  @Inject
  AgroalDataSource pool;

  private static final String SELECT_QUERY = "SELECT * FROM get_address(?)";
  private static final String INSERT_QUERY = "CALL add_address(?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private static final String DELETE_QUERY = "DELETE FROM address WHERE address_id = ?";

  public List<AddressRead> getAddress(int userId) {
    List<AddressRead> addresses = Collections.emptyList();

    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_QUERY)) {
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

  public boolean addAddress(int userId, AddressWrite address) {
    try(Connection connection = this.pool.getConnection()) {
      try(CallableStatement stm = connection.prepareCall(INSERT_QUERY)) {
        stm.setInt(1, userId);
        stm.setBoolean(2, false);

        String unitNumber = address.unitNumber() == null ? "none" : address.unitNumber();
        stm.setString(3, unitNumber);

        stm.setString(4, address.streetNumber());
        stm.setString(5, address.addressLine1());

        String addressLine2 = address.addressLine2() == null ? "none" : address.addressLine2();
        stm.setString(6, addressLine2);

        stm.setString(7, address.city());
        stm.setString(8, address.province());
        stm.setString(9, address.postalCode());

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteAddress(int userId, int addressId) {
    try(Connection connection = this.pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(DELETE_QUERY)) {
        stm.setInt(1, addressId);

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
