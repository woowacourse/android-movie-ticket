package woowacourse.movie.dto

import woowacourse.movie.domain.Movie

data class MovieDto(
    val id: Long,
    val title: String,
    val screenings: List<ScreeningDto>,
    val runningTime: Int,
    val summary: String
) {
    companion object {
        fun from(movie: Movie): MovieDto {
            return MovieDto(
                movie.id,
                movie.title,
                movie.screenings.screenings.keys.map(ScreeningDto.Companion::from),
                movie.runningTime.value,
                movie.summary
            )
        }
    }
}
