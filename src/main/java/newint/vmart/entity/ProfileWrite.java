package newint.vmart.entity;

import jakarta.validation.constraints.Email;

public record ProfileWrite(
  @Email
  String email,
  String name
) {}
