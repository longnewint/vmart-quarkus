package newint.vmart.entity;

public record PaymentRead(
  int paymentMethodId,
  int paymentTypeId,
  String cardNumber
) {}
