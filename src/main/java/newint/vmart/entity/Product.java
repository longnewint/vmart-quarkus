package newint.vmart.entity;

public record Product(
  String productId,
  String brand,
  String productName,
  String listPrice,
  String discountPrice,
  String url,
  String sku,
  String ingredients,
  String nutritions
) {}
