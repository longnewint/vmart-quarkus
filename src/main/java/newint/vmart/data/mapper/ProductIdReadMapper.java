package newint.vmart.data.mapper;

import newint.vmart.entity.ProductIdRead;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductIdReadMapper implements ResultMapper<ProductIdRead> {
  public ProductIdRead map(final ResultSet rs) throws SQLException {
    return new ProductIdRead(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getFloat(4),
      rs.getFloat(5),
      rs.getString(6),
      rs.getString(7),
      rs.getString(8),
      rs.getString(9),
      rs.getString(10)
    );
  }
}
