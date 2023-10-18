package com.marketbook.repository

import com.marketbook.enums.BookStatus
import com.marketbook.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus): List<BookModel>


}