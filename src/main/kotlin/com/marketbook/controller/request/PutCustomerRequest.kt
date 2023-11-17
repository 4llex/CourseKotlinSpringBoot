package com.marketbook.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PutCustomerRequest(
        @field:NotEmpty(message = "Nome deve ser informado no put!")
        var name: String,

        @field:Email(message = "E-mail deve ser válido no put")
        var email: String
)