package com.marketbook.model

import com.marketbook.enums.CustomerStatus
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column(name = "name")
        var name: String,

        @Column(name = "email", unique = true)
        var email: String,

        @Column
        @Enumerated(EnumType.STRING)
        var status: CustomerStatus

)