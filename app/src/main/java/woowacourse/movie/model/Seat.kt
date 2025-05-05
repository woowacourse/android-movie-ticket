package woowacourse.movie.model

data class Seat(
    val row: Char,
    val column: Int,
) {
    val tag: String
        get() = "$row$column"

    companion object {
        fun fromTag(tag: String): Seat {
            require(tag.length >= 2) { "태그 형식이 올바르지 않습니다: $tag" }
            val row = tag[0]
            val column =
                tag.substring(1).toIntOrNull()
                    ?: throw IllegalArgumentException("숫자 형식이 아닙니다: $tag")
            return Seat(row, column)
        }
    }
}
