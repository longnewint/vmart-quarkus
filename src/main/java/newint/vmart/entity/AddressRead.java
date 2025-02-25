package newint.vmart.entity;

public record AddressRead(
  int addressId,
  String unitNumber,
  String streetNumber,
  String addressLine1,
  String addressLine2,
  String postalCode,
  String city,
  String province,
  boolean isDefault
) {}
