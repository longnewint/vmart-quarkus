package newint.vmart.entity;

import java.sql.Timestamp;

public record CurrentOrderRead(
  int orderId,
  String storeName,
  int shippingMethodId,
  Timestamp orderDate,
  float orderTotal,
  int orderStatusId,
  String address
) {}
