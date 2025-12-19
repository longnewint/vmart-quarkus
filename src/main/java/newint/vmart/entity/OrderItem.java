package newint.vmart.entity;

public record OrderItem(
  int productId,
  String brand,
  String productName,
  float listPrice,
  float discountPrice,
  String packageSize,
  String thumbnailUrl,
  int quantity
) {}
