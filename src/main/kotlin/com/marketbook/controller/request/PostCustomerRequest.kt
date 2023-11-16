package com.marketbook.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(

    @field:NotEmpty(message = "Nome deve ser informado!")
    var name: String,

    @field:Email(message = "E-mail devev ser válido")
    var email: String
)