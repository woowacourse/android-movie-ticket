package woowacourse.movie.model

data class ReservationInfoModel(
    val title: String,
    val playingDate: String,
    val playingTime: String,
    val count: Int,
    val payment: String
) : java.io.Serializable
