package woowacourse.movie.domain.screening

data class Advertisement(
    val id: Int,
    val description: String? = null,
) : ScreeningContent
