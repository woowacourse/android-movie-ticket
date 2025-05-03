package woowacourse.movie.domain.model

enum class ViewType(val typeNumber: Int) {
    MOVIE(1),
    ADS(2),
    ;

    companion object {
        fun from(typeNumber: Int): ViewType {
            return entries.first { it.typeNumber == typeNumber }
        }
    }
}
