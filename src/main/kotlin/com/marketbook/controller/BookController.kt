package com.marketbook.controller

import com.marketbook.controller.request.PostBookRequest
import com.marketbook.controller.request.PutBookRequest
import com.marketbook.controller.response.BookResponse
import com.marketbook.extension.toBookModel
import com.marketbook.extension.toResponse
import com.marketbook.service.BookService
import com.marketbook.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> =
            bookService.findAll(pageable).map { it.toResponse() }


    @GetMapping("/actives")
    @ResponseStatus(HttpStatus.OK)
    fun findActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> =
            bookService.findActives(pageable).map { it.toResponse() }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest): PostBookRequest {
        val customer = customerService.findById(request.customerId)
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