package newint.vmart.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import newint.vmart.data.AddressRepo;
import newint.vmart.entity.Address;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/delivery/{userId}")
public class DeliveryApi {
  @Inject AddressRepo repo;

  @GET
  public List<Address> get(@RestPath int userId) {
    return repo.getAddress(userId);
  }
}
