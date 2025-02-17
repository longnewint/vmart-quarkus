package newint.vmart.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class CartItem {
  @NotBlank(message = "productId cannot be blank")
  public String productId;
  public int quantity;
}
