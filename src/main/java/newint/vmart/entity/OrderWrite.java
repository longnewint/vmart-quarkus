package newint.vmart.entity;

public record OrderWrite(
  int storeId,
  int cartId,
  int shippingMethodId,
  int addressId,
  int paymentMethodId
) {}
