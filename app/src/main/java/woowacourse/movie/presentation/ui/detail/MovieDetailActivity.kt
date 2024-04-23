package woowacourse.movie.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieRepositoryImpl
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.dto.MovieUiModel
import woowacourse.movie.presentation.ui.reservation.ReservationResultActivity

class MovieDetailActivity : BaseActivity(), MovieDetailContract.View {
    private var movieDetailPresenter: MovieDetailContract.Presenter? = null
    private lateinit var viewHolder: MovieDetailsViewHolder

    override fun getLayoutResId(): Int = R.layout.activity_movie_detail

    override fun onCreateSetup() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewHolder = MovieDetailsViewHolder(findViewById(android.R.id.content))

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)

        movieDetailPresenter =
            MovieDetailPresenterImpl(this, MovieRepositoryImpl, MovieTicketRepositoryImpl, movieId)

        initClickListener()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = viewHolder.reservationCountTextView.text.toString().toInt()
        outState.putInt(EXTRA_RESERVATION_COUNT, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val count = savedInstanceState.getInt(EXTRA_RESERVATION_COUNT)
        movieDetailPresenter?.updateReservationCount(count)
    }

    private fun initClickListener() {
        viewHolder.minusButton.setOnClickListener {
            movieDetailPresenter?.minusReservationCount()
        }
        viewHolder.plusButton.setOnClickListener {
            movieDetailPresenter?.plusReservationCount()
        }
        viewHolder.reserveButton.setOnClickListener {
            movieDetailPresenter?.requestReservationResult()
        }
    }

    override fun showMovieDetail(movieUiModel: MovieUiModel) {
        viewHolder.bindDetails(movieUiModel)
    }

    override fun showReservationCount(count: Int) {
        viewHolder.updateReservationCount(count)
    }

    override fun moveToReservationPage(movieTicketId: Int) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_TICKET_ID, movieTicketId)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movieId"
        const val EXTRA_MOVIE_TICKET_ID = "movieTicketId"
        const val EXTRA_RESERVATION_COUNT = "reservationCount"
    }
}
