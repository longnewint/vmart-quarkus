package newint.vmart.entity;

public record CartItemRead(
  int productId,
  String productName,
  float listPrice,
  String thumbnailUrl,
  int quantity
) {}
