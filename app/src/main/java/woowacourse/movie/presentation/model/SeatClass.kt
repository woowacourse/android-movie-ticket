package woowacourse.movie.presentation.model

import androidx.annotation.ColorRes
import woowacourse.movie.R

enum class SeatClass(@ColorRes val colorResId: Int) {
    S(R.color.s_class_color),
    A(R.color.a_class_color),
    B(R.color.b_class_color);
}
