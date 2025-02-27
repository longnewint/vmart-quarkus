package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.api.validation.Result;
import newint.vmart.data.ProfileRepo;
import newint.vmart.entity.ProfileRead;
import newint.vmart.entity.ProfileWrite;
import org.jboss.resteasy.reactive.RestPath;

import java.util.Optional;
import java.util.Set;

@Path("/profile")
public class ProfileApi {
  @Inject ProfileRepo repo;

  @Inject Validator validator;

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
  public Result patch(@RestPath int userId, @Valid ProfileWrite profile) {
    Set<ConstraintViolation<ProfileWrite>> violations = validator.validate(profile);

    if(violations.isEmpty()) {
      boolean isSuccessful = repo.updateProfile(userId, profile);

      return new Result(true, "Operation successful!");
    }
    else
      return new Result(violations);
  }
}
