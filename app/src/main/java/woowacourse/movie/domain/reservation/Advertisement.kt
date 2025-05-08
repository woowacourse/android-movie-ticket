package woowacourse.movie.domain.reservation

data class Advertisement(
    val id: Int,
    val description: String? = null,
) : ScreeningContent
