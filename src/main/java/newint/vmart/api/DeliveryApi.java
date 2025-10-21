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

@Path("/delivery")
public class DeliveryApi {
  @Inject AddressRepo repo;

  @Inject Validator validator;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<AddressRead> get() {
    return repo.getAddress(12321);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public int post(@Valid AddressWrite address) {
    return repo.addAddress(12321, address);
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public void delete(@RestQuery int addressId) {
    boolean isSuccessful = repo.deleteAddress(12321, addressId);
  }
}
