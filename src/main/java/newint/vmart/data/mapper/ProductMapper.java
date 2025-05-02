package newint.vmart.data.mapper;

import newint.vmart.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ResultMapper<Product> {
  public Product map(final ResultSet rs) throws SQLException {
    return new Product(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getFloat(4),
      rs.getFloat(5),
      rs.getString(6),
      rs.getString(7),
      rs.getString(8),
      rs.getString(9),
      rs.getString(10),
      rs.getString(11)
    );
  }
}
