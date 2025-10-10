package newint.vmart.entity;

public record CartItemRead(
  int productId,
  String brand,
  String productName,
  float listPrice,
  float discountPrice,
  String packageSize,
  String thumbnailUrl,
  int quantity
) {}
