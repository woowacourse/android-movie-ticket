package woowacourse.movie.sample

import woowacourse.movie.R
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.Poster

class SampleMovies {
    val movieUiModels: List<MovieUiModel> by lazy {
        generateSampleMovieUiModels()
    }

    private val posterDrawables =
        listOf(
            Poster.Resource(R.drawable.harry_potter),
            Poster.Resource(R.drawable.about_time),
            Poster.Resource(R.drawable.eternal_sunshine),
            Poster.Resource(R.drawable.titanic),
            Poster.Resource(R.drawable.life_of_pi),
        )

    private val titles =
        listOf(
            "해리 포터와 마법사의 돌",
            "어바웃 타임",
            "이터널 션샤인",
            "타이타닉",
            "라이프 오브 파이",
        )

    private val screeningStartDates =
        listOf(
            "2025.4.21",
            "2025.4.23",
            "2025.4.21",
            "2025.4.25",
            "2025.4.24",
        )

    private val screeningEndDates =
        listOf(
            "2025.5.25",
            "2025.5.23",
            "2025.5.21",
            "2025.5.25",
            "2025.5.24",
        )

    private val runningTimes =
        listOf(
            "120",
            "142",
            "132",
            "118",
            "123",
        )

    private fun generateSampleMovieUiModels(): List<MovieUiModel> {
        return (1..100).map { index ->
            val insertIndex = (index + 4) % 5
            MovieUiModel(
                id = index.toLong(),
                poster = posterDrawables[insertIndex],
                title = titles[insertIndex],
                screeningStartDate = screeningStartDates[insertIndex],
                screeningEndDate = screeningEndDates[insertIndex],
                runningTime = runningTimes[insertIndex],
            )
        }
    }
}
