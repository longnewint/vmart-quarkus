package newint.vmart.entity;

public record CartItemRead(
  String productId,
  String productName,
  String listPrice,
  String thumbnailUrl,
  String quantity
) {}
