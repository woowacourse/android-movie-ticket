package woowacourse.movie.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.getSerializable
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.ReservationData
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
        initReservationResultView(savedInstanceState)
    }

    fun initReservationResultView(savedInstanceState: Bundle?) {
        makeBackButton()
        val reservationData =
            intent.extras?.getSerializable<ReservationData>(ReservationData.RESERVATION_EXTRA_NAME)
        if (reservationData != null) {
            renderMovie(reservationData.movie)
            renderReservationDetail(reservationData.detail.toDomain())
        }
    }

    fun makeBackButton() {
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
}
