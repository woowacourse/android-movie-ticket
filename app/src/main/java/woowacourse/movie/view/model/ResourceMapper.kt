package woowacourse.movie.view.model

import woowacourse.movie.R
import woowacourse.movie.model.movie.screening.Screening.Companion.HARRY_POTTER_1_MOVIE_ID

object ResourceMapper {
    fun movieIdToPoster(movieId: String): Poster =
        when (movieId) {
            HARRY_POTTER_1_MOVIE_ID -> Poster.Resource(R.drawable.poster_harry_potter_and_the_philosophers_stone)
            else -> Poster.Url("dummyImageUrl") // 기본 이미지
        }
}
