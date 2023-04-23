package woowacourse.movie.utils

import woowacourse.movie.R
import woowacourse.movie.model.MovieListModel
import java.time.LocalDate

object MockData {
    val movies = List(300) {
        MovieListModel.MovieModel(
            R.drawable.about_time,
            "About Time",
            LocalDate.of(2023, 4, 10),
            LocalDate.of(2023, 4, 24),
            123,
            "아버지에게 가문 대대로 시간을 돌리는 능력을 타고났다는 사실을 들은 팀. 우연히 만난 메리에게 반한 팀은 완벽한 사랑을 위해 능력을 마음껏 사용하고, 그럴 때마다 주변 상황들이 점점 어긋나기 시작한다."
        )
    }

    val ad = MovieListModel.AdModel(
        R.drawable.ad,
        "https://github.com/woowacourse"
    )

    fun getMoviesWithAds(): List<MovieListModel> {
        val items = mutableListOf<MovieListModel>()

        movies.forEachIndexed { index, movieModel ->
            items.add(movieModel)

            if (isAdPosition(index)) {
                items.add(ad)
            }
        }

        return items
    }

    private fun isAdPosition(index: Int) = index % 3 == 2
}
