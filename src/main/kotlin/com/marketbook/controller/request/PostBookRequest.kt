package com.marketbook.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PostBookRequest(
        @field:NotEmpty(message = "Nome nao pode ser vazio")
        var name: String,

        @field:NotNull(message = "Preço deve ser informado!")
        var price: BigDecimal,

        @JsonAlias("customer_id")
        var customerId: Int
)

