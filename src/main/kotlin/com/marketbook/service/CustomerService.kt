package com.marketbook.service

import com.marketbook.model.CustomerModel
import com.marketbook.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
        val customerRepository: CustomerRepository,
        val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
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
        customerRepository.deleteById(id)
    }

    fun patchCustomer(): String {
        return "patch: Atualiza parcialmente um customer, ex: email"
    }

}