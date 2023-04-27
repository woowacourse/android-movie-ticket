package woowacourse.movie.view.model

import domain.Price
import woowacourse.movie.R
import java.io.Serializable

enum class SeatRankUiModel(val price: Price, val color: Int) : Serializable,UiModel {
    S(Price(15_000), R.color.seat_rank_s_color),
    A(Price(12_000), R.color.seat_rank_a_color),
    B(Price(10_000), R.color.seat_rank_b_color)
}
