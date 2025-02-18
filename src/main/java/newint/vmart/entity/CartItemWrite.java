package newint.vmart.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;

public record CartItemWrite(
  int productId,

  @PositiveOrZero(message = "quantity must be greater than 0")
  @Max(value = 25, message = "quantity must be less than 25")
  int quantity
) {}
