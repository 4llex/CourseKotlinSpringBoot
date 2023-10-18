package com.marketbook.controller

import com.marketbook.controller.request.PostBookRequest
import com.marketbook.extension.toBookModel
import com.marketbook.service.BookService
import com.marketbook.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("books")
class BookController(
        val bookService: BookService,
        val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest): PostBookRequest {
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
        return request
    }


}