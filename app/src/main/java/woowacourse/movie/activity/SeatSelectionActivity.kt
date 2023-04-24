package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.discountPolicy.Discount
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.PriceViewData
import woowacourse.movie.view.data.ReservationDetailViewData
import woowacourse.movie.view.data.SeatsViewData
import woowacourse.movie.view.error.ActivityError.finishWithError
import woowacourse.movie.view.error.ViewError
import woowacourse.movie.view.getSerializable
import woowacourse.movie.view.mapper.MovieSeatMapper.toDomain
import woowacourse.movie.view.mapper.ReservationDetailMapper.toDomain
import woowacourse.movie.view.widget.SeatTableLayout
import java.text.NumberFormat
import java.util.Locale

class SeatSelectionActivity : AppCompatActivity() {
    private val priceText: TextView by lazy {
        findViewById(R.id.seat_selection_movie_price)
    }

    private val reservationButton: Button by lazy {
        findViewById(R.id.seat_selection_reserve_button)
    }

    private val seatTableLayout: SeatTableLayout by lazy {
        SeatTableLayout.from(
            findViewById(R.id.seat_selection_table),
            SEAT_ROW_COUNT,
            SEAT_COLUMN_COUNT,
            SEAT_TABLE_LAYOUT_STATE_KEY
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        initSeatSelectionView(savedInstanceState)
    }

    private fun initSeatSelectionView(savedInstanceState: Bundle?) {
        val movie = intent.extras?.getSerializable<MovieViewData>(MovieViewData.MOVIE_EXTRA_NAME)
            ?: return finishWithError(ViewError.ActivityMissingExtras(MovieViewData.MOVIE_EXTRA_NAME))
        val reservationDetail =
            intent.extras?.getSerializable<ReservationDetailViewData>(ReservationDetailViewData.RESERVATION_DETAIL_EXTRA_NAME)
                ?: return finishWithError(ViewError.ActivityMissingExtras(ReservationDetailViewData.RESERVATION_DETAIL_EXTRA_NAME))

        initMovieView(movie)
        setPriceView(PriceViewData())
        initSeatTableLayout(movie, reservationDetail, savedInstanceState)
    }

    private fun initSeatTableLayout(
        movie: MovieViewData,
        reservationDetail: ReservationDetailViewData,
        savedInstanceState: Bundle?
    ) {
        initReserveButton(seatTableLayout, movie, reservationDetail)

        makeBackButton()

        seatTableLayout.onSelectSeat = {
            onSelectSeat(it, reservationDetail)
        }

        seatTableLayout.seatSelectCondition = { seatsSize ->
            seatsSize < reservationDetail.peopleCount
        }

        seatTableLayout.load(savedInstanceState)
    }

    private fun makeBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onSelectSeat(seats: SeatsViewData, reservationDetail: ReservationDetailViewData) {
        setPriceView(calculateDiscountedPrice(seats, reservationDetail))
        setReservationButtonState(seats.seats.size, reservationDetail.peopleCount)
    }

    private fun setPriceView(price: PriceViewData) {
        val formattedPrice = NumberFormat.getNumberInstance(Locale.US).format(price.value)
        priceText.text = getString(R.string.seat_price, formattedPrice)
    }

    private fun setReservationButtonState(
        seatsSize: Int,
        peopleCount: Int
    ) {
        reservationButton.isEnabled = seatsSize == peopleCount
    }

    private fun calculateDiscountedPrice(
        seats: SeatsViewData,
        reservationDetail: ReservationDetailViewData
    ): PriceViewData {
        val discount = Discount(listOf(MovieDay, OffTime))
        return seats.seats.sumOf { seat ->
            discount.calculate(
                reservationDetail.toDomain(), seat.toDomain().row.seatRankByRow().price
            ).value
        }.let {
            PriceViewData(it)
        }
    }

    private fun initReserveButton(
        seatTableLayout: SeatTableLayout,
        movie: MovieViewData,
        reservationDetail: ReservationDetailViewData
    ) {
        reservationButton.setOnClickListener {
            onClickReserveButton(seatTableLayout, movie, reservationDetail)
        }
        setReservationButtonState(DEFAULT_SEAT_SIZE, reservationDetail.peopleCount)
    }

    private fun onClickReserveButton(
        seatTableLayout: SeatTableLayout,
        movie: MovieViewData,
        reservationDetail: ReservationDetailViewData
    ) {
        AlertDialog.Builder(this).setTitle(getString(R.string.seat_selection_alert_title))
            .setMessage(getString(R.string.seat_selection_alert_message))
            .setPositiveButton(getString(R.string.seat_selection_alert_positive)) { _, _ ->
                reserveMovie(seatTableLayout, movie, reservationDetail)
            }.setNegativeButton(getString(R.string.seat_selection_alert_negative)) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false).show()
    }

    private fun reserveMovie(
        seatTableLayout: SeatTableLayout,
        movie: MovieViewData,
        reservationDetail: ReservationDetailViewData
    ) {
        ReservationResultActivity.from(
            this,
            movie,
            reservationDetail,
            seatTableLayout.selectedSeats(),
            calculateDiscountedPrice(
                seatTableLayout.selectedSeats(), reservationDetail
            )
        ).run {
            startActivity(this)
        }
    }

    private fun initMovieView(movie: MovieViewData) {
        findViewById<TextView>(R.id.seat_selection_movie_title).text = movie.title
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        seatTableLayout.save(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val SEAT_ROW_COUNT = 5
        private const val SEAT_COLUMN_COUNT = 4
        private const val DEFAULT_SEAT_SIZE = 0
        private const val SEAT_TABLE_LAYOUT_STATE_KEY = "seatTable"

        fun from(
            context: Context,
            movie: MovieViewData,
            reservationDetailViewData: ReservationDetailViewData
        ): Intent {
            return Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(MovieViewData.MOVIE_EXTRA_NAME, movie)
                putExtra(
                    ReservationDetailViewData.RESERVATION_DETAIL_EXTRA_NAME,
                    reservationDetailViewData
                )
            }
        }
    }
}
