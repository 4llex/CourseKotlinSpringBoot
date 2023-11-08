package com.marketbook.extension

import com.marketbook.controller.request.PostBookRequest
import com.marketbook.controller.request.PostCustomerRequest
import com.marketbook.controller.request.PutBookRequest
import com.marketbook.controller.request.PutCustomerRequest
import com.marketbook.controller.response.BookResponse
import com.marketbook.controller.response.CustomerResponse
import com.marketbook.enums.BookStatus
import com.marketbook.enums.CustomerStatus
import com.marketbook.model.BookModel
import com.marketbook.model.CustomerModel


fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
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

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer= this.customer,
        status = this.status
    )
}