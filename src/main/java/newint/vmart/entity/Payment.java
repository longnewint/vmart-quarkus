package newint.vmart.entity;

public record Payment(
  int paymentMethodId,
  int paymentType,
  String cardNumber,
  boolean isDefault
) {}
