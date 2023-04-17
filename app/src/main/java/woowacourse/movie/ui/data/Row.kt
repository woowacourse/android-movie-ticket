package woowacourse.movie.ui.data

import androidx.annotation.ColorRes
import woowacourse.movie.R

enum class Row(@ColorRes val color: Int) {
    A(R.color.b_rank),
    B(R.color.b_rank),
    C(R.color.s_rank),
    D(R.color.s_rank),
    E(R.color.a_rank),
}
