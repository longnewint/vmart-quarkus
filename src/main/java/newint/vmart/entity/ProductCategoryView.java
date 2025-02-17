package newint.vmart.entity;

public record ProductCategoryView(
  String productId,
  String brand,
  String productName,
  String listPrice,
  String discountPrice,
  String thumbnailUrl) {
}
