package com.marketbook.extension

import com.marketbook.controller.request.PostBookRequest
import com.marketbook.controller.request.PostCustomerRequest
import com.marketbook.controller.request.PutBookRequest
import com.marketbook.controller.request.PutCustomerRequest
import com.marketbook.enums.BookStatus
import com.marketbook.model.BookModel
import com.marketbook.model.CustomerModel


fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(name = this.name, price = this.price, status = BookStatus.ATIVO, customer = customer)
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
            id = previousValue.id,
            name = this.name ?: previousValue.name,
            price = this.price ?: previousValue.price,
            status = previousValue.status,
            customer = previousValue.customer
    )
}