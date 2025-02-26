package newint.vmart.data.mapper;

import newint.vmart.entity.ProfileRead;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements  ResultMapper<ProfileRead> {
  public ProfileRead map(final ResultSet rs) throws SQLException {
    return new ProfileRead(
      rs.getString(1),
      rs.getString(2),
      rs.getString(3)
    );
  }
}
