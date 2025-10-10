package newint.vmart.data.mapper;

import newint.vmart.entity.AddressRead;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements ResultMapper<AddressRead> {
  public AddressRead map(final ResultSet rs) throws SQLException {
    return new AddressRead(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      rs.getString(5),
      rs.getString(6),
      rs.getString(7),
      rs.getString(8)
    );
  }
}
