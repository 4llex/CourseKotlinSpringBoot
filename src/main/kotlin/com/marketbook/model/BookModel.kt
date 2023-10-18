package com.marketbook.model

import com.marketbook.enums.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column(name = "name")
        var name: String,

        @Column(name = "price")
        var price: BigDecimal,

        @Column(name = "status")
        @Enumerated(EnumType.STRING)
        var status: BookStatus? = null,

        @ManyToOne
        @JoinColumn(name = "customer_id")
        var customer: CustomerModel? = null
)