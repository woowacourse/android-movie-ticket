package woowacourse.movie.ui.seat

import androidx.annotation.ColorRes
import woowacourse.movie.R

enum class Rank(@ColorRes val color: Int) {
    A(R.color.a_rank),
    B(R.color.b_rank),
    S(R.color.s_rank),
}
