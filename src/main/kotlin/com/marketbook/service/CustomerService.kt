package com.marketbook.service

import com.marketbook.model.CustomerModel
import com.marketbook.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
        val customerRepository: CustomerRepository
) {

    val customersList = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customersList.filter { it.name.contains(name, false) }
        }
        return customersList
    }

    fun getCustomer(id: Int): CustomerModel {
        return customersList.filter { it.id == id }.first()
    }

    fun create(customer: CustomerModel) {

        val id = if (customersList.isEmpty()) {
            1
        } else {
            customersList.last().id!!.toInt() + 1
        }

        customer.id = id
        customersList.add(customer)
    }

    fun update(customer: CustomerModel) {
        customersList.filter { it.id == customer.id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: Int) {
        customersList.removeIf { it.id == id }
    }

    fun patchCustomer(): String {
        return "patch: Atualiza parcialmente um customer, ex: email"
    }

}