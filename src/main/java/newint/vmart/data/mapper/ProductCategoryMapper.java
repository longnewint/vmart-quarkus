package newint.vmart.data.mapper;

import newint.vmart.entity.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryMapper implements  ResultMapper<ProductCategory> {
  public ProductCategory map(final ResultSet rs) throws SQLException {
    return new ProductCategory(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getFloat(4),
      rs.getFloat(5),
      rs.getString(6),
      rs.getString(7));
  }
}
