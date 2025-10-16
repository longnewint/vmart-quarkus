package newint.vmart.entity;

import java.sql.Timestamp;

public record OrderRead(
  int orderId,
  String storeName,
  int shippingMethodId,
  long orderDate,
  float orderTotal,
  int orderStatusId,
  String address
) {}
