package newint.vmart.api.validation;

import jakarta.validation.ConstraintViolation;

import java.util.Set;
import java.util.stream.Collectors;

public class Result {
  private boolean isSuccess;
  private String message;

  public Result(boolean isSuccess, String message) {
    this.isSuccess = isSuccess;
    this.message = message;
  }

  public Result(Set<? extends ConstraintViolation<?>> violations) {
    this.isSuccess = false;
    this.message = violations.stream()
      .map(cv -> cv.getMessage())
      .collect(Collectors.joining(", "));
  }

  public String getMessage() { return message; }
  public boolean isSuccess() { return isSuccess; }
}
