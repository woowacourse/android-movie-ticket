package woowacourse.movie.dto

import woowacourse.movie.domain.Movie

data class MovieDto(
    val id: Long,
    val title: String,
    val runningTime: Int,
    val summary: String,
    val screenings: List<ScreeningInfoDto>
) {

    companion object {
        fun from(movie: Movie): MovieDto {
            return MovieDto(
                movie.id,
                movie.title,
                movie.runningTime.value,
                movie.summary,
                movie.screeningInfos.sortedBy { it.screeningDateTime }.map(ScreeningInfoDto::from)
            )
        }
    }
}
