package newint.vmart.entity;

public record OrderRead(
  int orderId,
  String storeName,
  int shippingMethodId,
  long orderDate,
  float orderTotal,
  int orderStatusId
) {}
