package woowacourse.movie.model

import com.example.domain.model.price.Price

data class TicketModel(
    val title: String,
    val playingDate: String,
    val playingTime: String,
    val count: Int,
    val price: Price,
    val payment: String
) : java.io.Serializable
