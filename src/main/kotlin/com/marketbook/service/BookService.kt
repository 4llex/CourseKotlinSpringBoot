package com.marketbook.service

import com.marketbook.enums.BookStatus
import com.marketbook.exception.NotFoundException
import com.marketbook.model.BookModel
import com.marketbook.model.CustomerModel
import com.marketbook.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
        val bookRepository: BookRepository
) {

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(pageable, BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{ NotFoundException("Book [${id}] not exists", "ML-0001") }
    }

    fun create(book: BookModel) {
        bookRepository.save(book)
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
