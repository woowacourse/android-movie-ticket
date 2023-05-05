package woowacourse.movie.view

import woowacourse.movie.R
import woowacourse.movie.domain.screening.Screening

object PosterResourceProvider {

    fun getPosterResourceId(screening: Screening): Int =
        when (screening.movie.title) {
            "해리 포터와 마법사의 돌", -> R.drawable.harry_porter1_poster
            "해리 포터와 비밀의 방" -> R.drawable.harry_porter2_poster
            "해리 포터와 아즈카반의 죄수" -> R.drawable.harry_porter3_poster
            "해리 포터와 불의 잔" -> R.drawable.harry_porter4_poster
            "해리 포터와 불사조 기사단" -> R.drawable.harry_porter5_poster
            "해리 포터와 혼혈 왕자" -> R.drawable.harry_porter6_poster
            "해리 포터와 죽음의 성물 - 1부" -> R.drawable.harry_porter7_poster
            else -> R.drawable.harry_porter8_poster
        }
}
