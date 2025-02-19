package newint.vmart.data.mapper;

import newint.vmart.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements ResultMapper<Product> {
  public Product map(final ResultSet rs) throws SQLException {
    return new Product(
      rs.getString(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      rs.getString(5),
      rs.getString(6),
      rs.getString(7),
      rs.getString(8),
      rs.getString(9)
    );
  }
}
