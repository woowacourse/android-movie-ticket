package woowacourse.movie.view.model

import java.io.Serializable

data class ReservationUiModel(val movie: MovieUiModel, val tickets: TicketsUiModel) :
    UiModel,
    Serializable
