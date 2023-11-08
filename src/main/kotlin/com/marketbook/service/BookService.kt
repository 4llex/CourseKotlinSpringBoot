package com.marketbook.service

import com.marketbook.enums.BookStatus
import com.marketbook.model.BookModel
import com.marketbook.model.CustomerModel
import com.marketbook.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
        val bookRepository: BookRepository
) {

    fun findAll(): List<BookModel> {
        return bookRepository.findAll().toList()
    }

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun delete(id: Int) {
        val book: BookModel = bookRepository.findById(id).orElseThrow()
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for(book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

}
