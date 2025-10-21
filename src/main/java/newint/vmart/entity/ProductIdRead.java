package newint.vmart.entity;

public record ProductId(
  int productId,
  String brand,
  String productName,
  float listPrice,
  float discountPrice,
  String packageSize,
  String url,
  String sku,
  String ingredients,
  String nutritions
) {}
