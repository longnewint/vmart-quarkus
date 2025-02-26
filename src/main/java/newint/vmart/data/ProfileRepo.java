package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.ProfileMapper;
import newint.vmart.entity.ProfileRead;
import newint.vmart.entity.ProfileWrite;

import java.sql.*;
import java.util.Optional;

@ApplicationScoped
public class ProfileRepo {
  @Inject AgroalDataSource pool;

  private static final String SELECT_QUERY = "SELECT * FROM get_profile(?)";
  private static final String UPDATE_QUERY = "CALL update_profile(?, ?, ?)";

  public Optional<ProfileRead> getProfile(int userId) {
    try(Connection connection = pool.getConnection()) {
      try(PreparedStatement stm = connection.prepareStatement(SELECT_QUERY)) {
        stm.setInt(1, userId);

        try(ResultSet rs = stm.executeQuery()) {
          if(rs.next()) {
            ProfileMapper mapper = new ProfileMapper();
            return Optional.ofNullable(mapper.map(rs));
          }
          else
            return Optional.empty();
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateProfile(int userId, ProfileWrite profile) {
    try(Connection connection = pool.getConnection()) {
      try(CallableStatement stm = connection.prepareCall(UPDATE_QUERY)) {
        stm.setInt(1, userId);
        stm.setString(2, profile.name());
        stm.setString(3, profile.phoneNumber());

        return stm.execute();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
