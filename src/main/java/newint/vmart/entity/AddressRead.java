package newint.vmart.entity;

public record AddressRead(
  int addressId,
  String unitNumber,
  String streetNumber,
  String addressLine1,
  String addressLine2,
  String city,
  String province,
  String postalCode
) {}
