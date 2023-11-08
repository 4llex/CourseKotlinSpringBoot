package com.marketbook.repository

import com.marketbook.enums.BookStatus
import com.marketbook.model.BookModel
import com.marketbook.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository: JpaRepository<BookModel, Int> {
    fun findByStatus(pageable: Pageable, status: BookStatus): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>

//    fun findAll(pageable: Pageable): Page<BookModel>


}