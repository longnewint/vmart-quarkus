package newint.vmart.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultMapper<T> {
  T map(final ResultSet rs) throws SQLException;
}
