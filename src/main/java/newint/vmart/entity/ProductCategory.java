package newint.vmart.entity;

public record ProductCategory(
  String productId,
  String brand,
  String productName,
  String listPrice,
  String discountPrice,
  String thumbnailUrl) {
}
