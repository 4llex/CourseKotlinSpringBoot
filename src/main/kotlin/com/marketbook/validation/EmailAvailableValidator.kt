package com.marketbook.validation

import com.marketbook.service.CustomerService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(
    private var customerService: CustomerService
): ConstraintValidator<EmailAvailable, String> {

  override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
      if (value.isNullOrEmpty()) {
        return false
      } else {
        return customerService.emailAvailable(value)
      }
  }
}
