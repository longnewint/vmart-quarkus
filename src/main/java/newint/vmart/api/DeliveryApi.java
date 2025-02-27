package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import newint.vmart.api.validation.Result;
import newint.vmart.data.AddressRepo;
import newint.vmart.entity.AddressRead;
import newint.vmart.entity.AddressWrite;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;
import java.util.Set;

@Path("/delivery/{userId}")
public class DeliveryApi {
  @Inject AddressRepo repo;

  @Inject Validator validator;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<AddressRead> get(@RestPath int userId) {
    return repo.getAddress(userId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Result post(@RestPath int userId, @Valid AddressWrite address) {
    Set<ConstraintViolation<AddressWrite>> violations = validator.validate(address);

    if(violations.isEmpty()) {
      boolean isSuccessful = repo.addAddress(userId, address);

      return new Result(true, "Operation successful!");
    }
    else
      return new Result(violations);
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public void delete(@RestPath int userId, @RestQuery int addressId) {
    boolean isSuccessful = repo.deleteAddress(userId, addressId);
  }
}
