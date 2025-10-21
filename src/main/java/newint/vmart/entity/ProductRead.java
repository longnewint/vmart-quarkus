package newint.vmart.entity;

public record ProductCategory(
  int productId,
  String brand,
  String productName,
  float listPrice,
  float discountPrice,
  String packageSize,
  String thumbnailUrl
) {}
