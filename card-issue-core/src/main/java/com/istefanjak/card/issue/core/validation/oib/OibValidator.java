package com.istefanjak.card.issue.core.validation.oib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/** Class containing OIB validation logic. Implicitly used with the annotation {@link Oib} */
public class OibValidator implements ConstraintValidator<Oib, String> {

  /**
   * This method checks if the provided OIB is valid.
   *
   * @param oib OIB to be checked
   * @param context context
   * @return true if valid, else false
   */
  @Override
  public boolean isValid(String oib, ConstraintValidatorContext context) {
    if (oib.length() != 11) {
      return false;
    }

    for (var digit : oib.toCharArray()) {
      if (digit < '0' || digit > '9') {
        return false;
      }
    }

    var remainder = 10;
    for (var i = 0; i < 10; i++) {
      var firstDigit = oib.charAt(i) - '0' + remainder;

      var op = firstDigit % 10;
      if (op == 0) {
        op = 10;
      }
      op *= 2;
      op %= 11;

      remainder = op;
    }

    var lastDigit = (remainder == 1) ? 0 : 11 - remainder;

    return oib.charAt(oib.length() - 1) - '0' == lastDigit;
  }
}
