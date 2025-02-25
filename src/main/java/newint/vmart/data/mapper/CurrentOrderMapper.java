package newint.vmart.data.mapper;

import newint.vmart.entity.CurrentOrder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentOrderMapper implements ResultMapper<CurrentOrder> {
  public CurrentOrder map(final ResultSet rs) throws SQLException {
    return new CurrentOrder(
      rs.getInt(1),
      rs.getString(2),
      rs.getInt(3),
      rs.getTimestamp(4),
      rs.getFloat(5),
      rs.getInt(6),
      formatAddress(
        rs.getString(7),
        rs.getString(8),
        rs.getString(9),
        rs.getString(10),
        rs.getString(11),
        rs.getString(12),
        rs.getString(13)
      )
    );
  }

  public String formatAddress(
    String unitNumber,
    String streetNumber,
    String addressLine1,
    String addressLine2,
    String city,
    String province,
    String postalCode) {
    String tmpUnitNumber = !unitNumber.equals("none") ? unitNumber + ", " : "";
    String tmpAddressLine2 = !addressLine2.equals("none") ? addressLine2 + ", " : "";

    return String.format("%s%s %s, %s%s, %s %s",
      tmpUnitNumber,
      streetNumber,
      addressLine1,
      tmpAddressLine2,
      city,
      province,
      postalCode);
  }
}
