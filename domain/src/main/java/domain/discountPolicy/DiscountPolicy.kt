package domain.discountPolicy

import domain.Price
import domain.Ticket
import java.io.Serializable

interface DiscountPolicy : Serializable {
    fun discount(ticket: Ticket, price: Price): Price
}
