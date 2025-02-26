package newint.vmart.entity;

public record PaymentRead(
  int paymentMethodId,
  int paymentType,
  String cardNumber,
  boolean isDefault
) {}
