package woowacourse.movie.domain.movie

import woowacourse.movie.domain.movie.ScreenView.Companion.currentId

class Ads(
    override val id: Long = currentId++,
    val imgRes: Int,
    val url: String = "https://www.woowacourse.io/",
) : ScreenView
