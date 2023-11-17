package com.marketbook.service

import com.marketbook.enums.CustomerStatus
import com.marketbook.enums.Errors
import com.marketbook.exception.NotFoundException
import com.marketbook.model.CustomerModel
import com.marketbook.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
        val customerRepository: CustomerRepository,
        val bookService: BookService
) {

    fun getAll(pageable: Pageable, name: String?): Page<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(pageable, name)
        }
        return customerRepository.findAll(pageable)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow {
            NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code)
        }
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun patchCustomer(): String {
        return "patch: Atualiza parcialmente um customer, ex: email"
    }

  fun emailAvailable(email: String): Boolean {
       return !customerRepository.existsByEmail(email)
  }

}