package com.marketbook.model

import com.marketbook.enums.BookStatus
import com.marketbook.enums.Errors
import com.marketbook.exception.BadRequestException
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

        @ManyToOne
        @JoinColumn(name = "customer_id")
        var customer: CustomerModel? = null
) {
        @Column(name = "status")
        @Enumerated(EnumType.STRING)
        var status: BookStatus? = null
                set(value) {
                        if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                                throw BadRequestException(Errors.ML102.message.format(field), Errors.ML201.code)
                        }
                        field = value
                }

        constructor(id: Int? = null,
                    name: String,
                    price: BigDecimal,
                    customer: CustomerModel?,
                    status: BookStatus?): this(id, name, price, customer) {
                            this.status = status
                    }
}
