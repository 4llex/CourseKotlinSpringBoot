package com.marketbook.repository

import com.marketbook.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: JpaRepository<CustomerModel, Int> {
    fun findByNameContaining(pageable: Pageable, name: String): Page<CustomerModel>

}