package newint.vmart.entity;

public record PaymentWrite (
  int paymentTypeId,
  String cardNumber,
  String expMonth,
  String expYear,
  String cvv
) {}
