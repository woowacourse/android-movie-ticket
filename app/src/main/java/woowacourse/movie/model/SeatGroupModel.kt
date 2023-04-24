package woowacourse.movie.model

import java.io.Serializable

class SeatGroupModel(
    val seats: List<SeatModel> = emptyList()
) : Serializable
