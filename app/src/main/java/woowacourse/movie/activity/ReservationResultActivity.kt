package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.getSerializable
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.PriceViewData
import woowacourse.movie.view.data.ReservationDetailViewData
import woowacourse.movie.view.data.SeatsViewData
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
                ?: return finish()
        val seats = intent.extras?.getSerializable<SeatsViewData>(SeatsViewData.SEATS_EXTRA_NAME)
            ?: return finish()
        val price = intent.extras?.getSerializable<PriceViewData>(PriceViewData.PRICE_EXTRA_NAME)
            ?: return finish()
        renderReservationDetail(reservationDetail, price)

        val movie = intent.extras?.getSerializable<MovieViewData>(MovieViewData.MOVIE_EXTRA_NAME)
            ?: return finish()
        renderMovie(movie)
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
        reservationDetail: ReservationDetailViewData,
        price: PriceViewData
    ) {
        val date = findViewById<TextView>(R.id.movie_reservation_result_date)
        val peopleCount = findViewById<TextView>(R.id.movie_reservation_result_people_count)
        val priceText = findViewById<TextView>(R.id.movie_reservation_result_price)
        val dateFormat =
            DateTimeFormatter.ofPattern(date.context.getString(R.string.reservation_datetime_format))
        date.text = dateFormat.format(reservationDetail.date)

        peopleCount.text = peopleCount.context.getString(R.string.reservation_people_count)
            .format(reservationDetail.peopleCount)

        val formattedPrice = NumberFormat.getNumberInstance(Locale.US).format(price.value)

        priceText.text =
            priceText.context.getString(R.string.reservation_price).format(formattedPrice)
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
            seats: SeatsViewData,
            price: PriceViewData
        ): Intent {
            return Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(MovieViewData.MOVIE_EXTRA_NAME, movie)
                putExtra(ReservationDetailViewData.RESERVATION_DETAIL_EXTRA_NAME, reservationDetail)
                putExtra(SeatsViewData.SEATS_EXTRA_NAME, seats)
                putExtra(PriceViewData.PRICE_EXTRA_NAME, price)
            }
        }
    }
}
