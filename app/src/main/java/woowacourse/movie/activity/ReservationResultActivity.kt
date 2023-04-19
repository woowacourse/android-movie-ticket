package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.getSerializable
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.ReservationDetailViewData
import woowacourse.movie.view.mapper.ReservationDetailMapper.toDomain
import woowacourse.movie.view.widget.MovieController
import woowacourse.movie.view.widget.MovieView
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        initReservationResultView()
    }

    private fun initReservationResultView() {
        makeBackButton()
        val reservationDetail =
            intent.extras?.getSerializable<ReservationDetailViewData>(ReservationDetailViewData.RESERVATION_DETAIL_EXTRA_NAME)
        if (reservationDetail != null) {
            renderReservationDetail(reservationDetail.toDomain())
        }
        val movie = intent.extras?.getSerializable<MovieViewData>(MovieViewData.MOVIE_EXTRA_NAME)
        if (movie != null) {
            renderMovie(movie)
        }
    }

    private fun makeBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun renderMovie(
        movie: MovieViewData
    ) {
        MovieController(
            movie = movie,
            MovieView(
                title = findViewById(R.id.movie_reservation_result_title),
            )
        ).render()
    }

    private fun renderReservationDetail(
        reservationDetail: ReservationDetail
    ) {
        val date = findViewById<TextView>(R.id.movie_reservation_result_date)
        val peopleCount = findViewById<TextView>(R.id.movie_reservation_result_people_count)
        val price = findViewById<TextView>(R.id.movie_reservation_result_price)
        val dateFormat =
            DateTimeFormatter.ofPattern(date.context.getString(R.string.reservation_datetime_format))
        date.text = dateFormat.format(reservationDetail.date)

        peopleCount.text = peopleCount.context.getString(R.string.reservation_people_count)
            .format(reservationDetail.peopleCount)

        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(reservationDetail.getTotalPrice())

        price.text = price.context.getString(R.string.reservation_price).format(formattedPrice)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun from(
            context: Context,
            movie: MovieViewData,
            reservationDetail: ReservationDetailViewData,
            // seats: Seats
        ): Intent {
            return Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(MovieViewData.MOVIE_EXTRA_NAME, movie)
                putExtra(ReservationDetailViewData.RESERVATION_DETAIL_EXTRA_NAME, reservationDetail)
                // putExtra()
            }
        }
    }
}
