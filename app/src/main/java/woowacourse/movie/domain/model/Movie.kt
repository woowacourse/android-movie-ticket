package woowacourse.movie.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val runningTime: Int,
    val description: String,
) {
    companion object {
        val NULL =
            Movie(
                id = -1,
                title = "",
                runningTime = -1,
                description = "",
            )
    }
}
