package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Movie(
    val title: String,
    val screenDate: List<LocalDate>,
    val runningTime: Int,
    val description: String,
    val img: Int,
) : Serializable {
    fun screenDateToString(): String {
        if (screenDate.size == 1) return screenDate[0].format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        return screenDate.joinToString("~") { it.format(DateTimeFormatter.ofPattern("yyyy.M.d")) }
    }
}
