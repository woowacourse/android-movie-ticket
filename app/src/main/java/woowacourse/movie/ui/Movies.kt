package woowacourse.movie.ui

import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieModel
import java.time.LocalDate

class Movies {
    private val items: List<MovieModel> = List(7500) {
        MovieModel(
            R.drawable.fifty_shades_freed,
            "그레이의 50가지 그림자 $it",
            LocalDate.of(2023, 11, 11),
            LocalDate.of(2023, 11, 19),
            105,
            "모든 과거를 잊고 서로에게 더 깊게 빠져든 ‘크리스찬 그레이’와 ‘아나스타샤’. 그레이의 독특한 취향으로 시작된 이 비밀스러운 관계는 더 큰 자극을 원하는 아나스타샤로 인해 역전되고, 마침내 그녀의 본능이 지배하는 마지막 절정의 순간을 맞이하게 되는데…"
        )
    }

    fun getAll(): List<MovieModel> = items.toList()
}
