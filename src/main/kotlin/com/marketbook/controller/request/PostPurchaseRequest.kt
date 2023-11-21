package com.marketbook.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PostPurchaseRequest(

    @field:NotNull
    @field:Positive
    @JsonAlias("customer_id")
    val customerId: Int,

    @JsonAlias("book_ids")
    @field:NotNull
    val bookIds: Set<Int>
)
