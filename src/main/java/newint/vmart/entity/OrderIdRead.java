package newint.vmart.entity;

public record OrderIdRead(
  int orderId,
  String storeName,
  int shippingMethodId,
  long orderDate,
  float orderTotal,
  int orderStatusId,
  String address
) {}
