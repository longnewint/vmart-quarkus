package newint.vmart.entity;

public record Product(
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
