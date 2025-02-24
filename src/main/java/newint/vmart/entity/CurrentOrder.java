package newint.vmart.entity;

import java.sql.Timestamp;

public record CurrentOrder(
  int orderId,
  String storeName,
  int shippingMethodId,
  Timestamp orderDate,
  float orderTotal,
  int orderStatusId,
  String address
) {}
