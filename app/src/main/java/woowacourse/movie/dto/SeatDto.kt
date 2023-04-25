package woowacourse.movie.dto

import java.io.Serializable

data class SeatDto(val row: SeatRowDto, val col: SeatColDto) : Serializable {
    fun getString(): String {
        return "${row.row}${col.col}"
    }
}
