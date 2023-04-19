package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.getSerializable
import woowacourse.movie.view.data.MovieViewData
import woowacourse.movie.view.data.ReservationDetailViewData
import woowacourse.movie.view.widget.SeatTableLayout

class SeatSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        initSeatSelectionView()
    }

    private fun initSeatSelectionView() {
        makeBackButton()

        val seatTableLayout = SeatTableLayout.from(
            findViewById(R.id.seat_selection_table), SEAT_ROW_COUNT, SEAT_COLUMN_COUNT, 3
        )

        findViewById<Button>(R.id.seat_selection_reserve_button).setOnClickListener {
            onClickReserveButton(seatTableLayout)
        }
    }

    private fun makeBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun onClickReserveButton(seatTableLayout: SeatTableLayout) {
        AlertDialog.Builder(this).setTitle(getString(R.string.seat_selection_alert_title))
            .setMessage(getString(R.string.seat_selection_alert_message))
            .setPositiveButton(getString(R.string.seat_selection_alert_positive)) { _, _ ->
                reserveMovie(seatTableLayout)
            }.setNegativeButton(getString(R.string.seat_selection_alert_negative)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun reserveMovie(seatTableLayout: SeatTableLayout) {
        ReservationResultActivity.from(
            this,
            intent.extras?.getSerializable<MovieViewData>(MovieViewData.MOVIE_EXTRA_NAME) ?: return finish(),
            intent.extras?.getSerializable<ReservationDetailViewData>(ReservationDetailViewData.RESERVATION_DETAIL_EXTRA_NAME) ?: return finish(),
        ).run {
            startActivity(this)
        }
    }

    companion object {
        private const val SEAT_ROW_COUNT = 5
        private const val SEAT_COLUMN_COUNT = 4

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
