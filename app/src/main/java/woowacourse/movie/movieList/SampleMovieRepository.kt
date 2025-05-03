package woowacourse.movie.movieList

import woowacourse.movie.uiModel.MovieInfoUIModel

class SampleMovieRepository : MovieRepository {
    override fun getMovies(): List<MovieInfoUIModel> =
        listOf(
            MovieInfoUIModel(
                "harry_potter_poster_1",
                "해리 포터와 마법사의 돌",
                "2025.4.1",
                "2025.4.25",
                152,
            ),
            MovieInfoUIModel(
                "harry_potter_poster_2",
                "해리 포터와 비밀의 방",
                "2025.4.1",
                "2025.4.28",
                162,
            ),
            MovieInfoUIModel(
                "harry_potter_poster_3",
                "해리 포터와 아즈카반의 죄수",
                "2025.5.1",
                "2025.5.31",
                141,
            ),
            MovieInfoUIModel(
                "harry_potter_poster_4",
                "해리 포터와 불의 잔",
                "2025.6.1",
                "2025.6.30",
                157,
            ),
        )
}
