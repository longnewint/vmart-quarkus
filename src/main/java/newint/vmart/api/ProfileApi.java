package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.data.ProfileRepo;
import newint.vmart.entity.ProfileRead;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Optional;

@Path("/profile")
public class ProfileApi {
  @Inject ProfileRepo repo;

  @GET
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Optional<ProfileRead> get(@RestPath int userId) {
    return repo.getProfile(userId);
  }

  @PATCH
  @Path("/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Result
}
