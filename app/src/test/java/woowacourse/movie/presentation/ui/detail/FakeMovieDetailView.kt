package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.presentation.uimodel.MovieUiModel
import woowacourse.movie.presentation.uimodel.ReservationCount
import java.time.LocalDate
import java.time.LocalTime

class FakeMovieDetailView: MovieDetailContract.View  {
    lateinit var fakeMovieUiModel: MovieUiModel
    lateinit var fakeDates: List<LocalDate>
    lateinit var fakeTimes: List<LocalTime>
    var fakeReservationCount: Int = 1
    
    
    override fun updateSpinnerAdapter(list: List<LocalDate>) {
        fakeDates = list
    }
    
    override fun updateTimeSpinnerAdapter(list: List<LocalTime>) {
        fakeTimes = list
    }
    
    override fun showMovieDetail(movieUiModel: MovieUiModel) {
        fakeMovieUiModel = movieUiModel
    }
    
    override fun showReservationCount(count: Int) {
        fakeReservationCount = count
    }
    
    override fun moveToReservationPage(movieTicketId: Int) {
        TODO("Not yet implemented")
    }
    
    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
    
}
