package data

import java.time.LocalDateTime

data class PricePolicyInfo(
    val price: Int = 0,
    val reservationDateTime: LocalDateTime = LocalDateTime.MIN,
)
