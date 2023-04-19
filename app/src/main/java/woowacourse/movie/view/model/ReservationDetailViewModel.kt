package woowacourse.movie.view.model

import java.io.Serializable
import java.time.LocalDateTime

data class ReservationDetailViewModel(
    val date: LocalDateTime,
    val peopleCount: Int,
    val price: Int
) : ViewModel, Serializable
