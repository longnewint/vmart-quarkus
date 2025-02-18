package newint.vmart.api;

import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

public class Result {
  private String message;
  private boolean isSuccess;

  Result(String message) {
    this.isSuccess = true;
    this.message = message;
  }

  Result(Set<? extends ConstraintViolation<?>> violations) {
    this.isSuccess = false;
    this.message = violations.stream()
      .map(cv -> cv.getMessage())
      .collect(Collectors.joining(", "));
  }

  public String getMessage() { return message; }
  public boolean isSuccess() { return isSuccess; }
}
