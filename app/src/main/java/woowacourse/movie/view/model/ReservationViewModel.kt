package woowacourse.movie.view.model

import java.io.Serializable

data class ReservationViewModel(val movie: MovieViewModel, val detail: ReservationDetailViewModel) :
    ViewModel,
    Serializable
