package newint.vmart.entity;

import jakarta.validation.constraints.Email;

public record ProfileWrite(
  String name,
  String phoneNumber
) {}
