package woowacourse.movie.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieRepositoryImpl
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.seat.SeatActivity
import woowacourse.movie.presentation.uimodel.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {
    private lateinit var movieDetailPresenter: MovieDetailContract.Presenter
    private lateinit var reservationView: ReservationView
    private lateinit var movieDetailView: MovieDetailView

    override fun getLayoutResId(): Int = R.layout.activity_movie_detail

    override fun onCreateSetup() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieDetailView = MovieDetailView(findViewById(R.id.movie_detail_information_layout))
        reservationView = ReservationView(findViewById(R.id.reservationLayout))

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, INVALID_MOVIE_ID)

        movieDetailPresenter =
            MovieDetailPresenterImpl(this, MovieRepositoryImpl, MovieTicketRepositoryImpl, movieId)

        initClickListener()
    }

    override fun updateSpinnerAdapter(list: List<LocalDate>) {
        val adapter =
            ArrayAdapter(this, R.layout.spinner_item, list)
        reservationView.dateSpinner.adapter = adapter
        reservationView.dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = reservationView.dateSpinner.selectedItem
                    movieDetailPresenter.updateMovieScheduleDate(selectedDate as LocalDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("MovieDetailActivity", "Nothing Selected")
                }
            }
    }

    override fun updateTimeSpinnerAdapter(list: List<LocalTime>) {
        val adapter =
            ArrayAdapter(this, R.layout.spinner_item, list)
        reservationView.timeSpinner.adapter = adapter
        reservationView.timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedTime = reservationView.timeSpinner.selectedItem
                    movieDetailPresenter.updateMovieScheduleTime(selectedTime as LocalTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("MovieDetailActivity", "Nothing Selected")
                }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = movieDetailPresenter.reservationCount()
        outState.putInt(EXTRA_RESERVATION_COUNT, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val count = savedInstanceState.getInt(EXTRA_RESERVATION_COUNT)
        movieDetailPresenter.updateReservationCount(count)
    }

    private fun initClickListener() {
        reservationView.onClickMinusButton {
            movieDetailPresenter.minusReservationCount()
        }
        reservationView.onClickPlusButton {
            movieDetailPresenter.plusReservationCount()
        }
        reservationView.onClickReserveButton {
            movieDetailPresenter.requestReservationResult()
        }
    }

    override fun showMovieDetail(movieUiModel: MovieUiModel) {
        movieDetailView.bindDetails(movieUiModel)
    }

    override fun showReservationCount(count: Int) {
        reservationView.updateReservationCount(count)
    }

    override fun moveToReservationPage(movieTicketId: Int) {
        val intent = Intent(this, SeatActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_TICKET_ID, movieTicketId)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    companion object {
        const val INVALID_MOVIE_ID = -1
        const val EXTRA_MOVIE_ID = "movieId"
        const val EXTRA_MOVIE_TICKET_ID = "movieTicketId"
        const val EXTRA_RESERVATION_COUNT = "count"
    }
}
