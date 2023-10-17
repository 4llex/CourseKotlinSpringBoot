package com.marketbook.extension

import com.marketbook.controller.request.PostCustomerRequest
import com.marketbook.controller.request.PutCustomerRequest
import com.marketbook.model.CustomerModel


fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}