package newint.vmart.entity;

public record OrderIdRead(
  int orderId,
  String unitNumber,
  String streetNumber,
  String addressLine1,
  String addressLine2,
  String city,
  String province,
  String postalCode,
  OrderItem[] items
) {}