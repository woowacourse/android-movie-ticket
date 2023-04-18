package woowacourse.movie.ui.moviebookingactivity

import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.util.customGetSerializableExtra

class MovieBookingActivity : AppCompatActivity() {

    lateinit var movieBookingInputView: MovieBookingInputView
    lateinit var timeSpinner: Spinner
    lateinit var movieData: MovieUIModel
    var timeSpinnerRecoverState: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        initMovieBookingInputView()
        initTimeSpinner()
        recoverState(savedInstanceState)
        initExtraData()
        initMovieInformationView()
        movieBookingInputView.initTicketCount()
        movieBookingInputView.initBookingCompleteButtonClickListener(movieData)
        movieBookingInputView.initSpinnerAdapter(movieData, timeSpinnerRecoverState)
    }

    private fun initTimeSpinner() {
        timeSpinner = movieBookingInputView.initTimeSpinner()
    }

    private fun initMovieBookingInputView() {
        movieBookingInputView = MovieBookingInputView(findViewById(R.id.layout_movie_booking_input))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(TICKET_COUNT, movieBookingInputView.ticketCount.value)
        outState.putInt(SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
        super.onSaveInstanceState(outState)
    }

    private fun recoverState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            movieBookingInputView.ticketCount.value = savedInstanceState.getInt(TICKET_COUNT)
            timeSpinnerRecoverState = savedInstanceState.getInt(SELECTED_TIME_POSITION)
        }
    }

    private fun initMovieInformationView() {
        MovieInformationView(
            findViewById<ConstraintLayout>(R.id.layout_movie_information),
            movieData
        )
    }

    private fun initExtraData() {
        movieData = intent.customGetSerializableExtra(MOVIE_DATA) ?: throw IllegalStateException(
            INTENT_EXTRA_INITIAL_ERROR
        )
    }

    companion object {
        // savedInstanceState
        const val TICKET_COUNT = "ticketCount"
        const val SELECTED_TIME_POSITION = "selectedTimePosition"

        // intent data
        const val MOVIE_DATA = "movieData"

        private const val INTENT_EXTRA_INITIAL_ERROR = "intent 의 데이터 이동시 data가 null으로 넘어오고 있습니다"
    }
}
