package com.marketbook.controller

import com.marketbook.controller.request.PostBookRequest
import com.marketbook.controller.request.PutBookRequest
import com.marketbook.extension.toBookModel
import com.marketbook.model.BookModel
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<BookModel> =
            bookService.getAll()


    @GetMapping("/actives")
    @ResponseStatus(HttpStatus.OK)
    fun findActives(): List<BookModel> =
            bookService.findActives()


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Int): BookModel {
        return bookService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest): PostBookRequest {
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
        return request
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }


}