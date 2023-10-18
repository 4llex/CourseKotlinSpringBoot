package com.marketbook.service

import com.marketbook.model.BookModel
import com.marketbook.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
        val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

}
