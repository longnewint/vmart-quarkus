package newint.vmart.entity;

public record CartItemRead(
  int productId,
  String brand,
  String productName,
  float listPrice,
  float discount_price,
  String package_size,
  String unit_price,
  String thumbnailUrl,
  int quantity
) {}
