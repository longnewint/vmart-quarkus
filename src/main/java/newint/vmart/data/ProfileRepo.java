package newint.vmart.data;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import newint.vmart.data.mapper.ProfileMapper;
import newint.vmart.entity.ProfileRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
public class ProfileRepo {
  @Inject AgroalDataSource pool;

  private static final String SELECT_QUERY = "SELECT * FROM get_profile(?)";

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
}
