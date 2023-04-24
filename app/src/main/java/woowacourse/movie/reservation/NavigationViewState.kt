package woowacourse.movie.reservation

import java.io.Serializable

data class NavigationViewState(
    val ticketCount: Int,
    val dateSpinnerPosition: Int,
    val timeSpinnerPosition: Int
) : Serializable
