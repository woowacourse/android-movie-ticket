package woowacourse.movie.selectseat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.moviereservation.uimodel.BookingInfoUiModel
import woowacourse.movie.util.intentParcelable

class SelectSeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Log.d("좌석 선택", "${intent.intentParcelable(EXTRA_BOOKING_ID, BookingInfoUiModel::class.java)}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val EXTRA_BOOKING_ID: String = "bookingId"

        fun getIntent(
            context: Context,
            bookingInfoUiModel: BookingInfoUiModel,
        ): Intent {
            return Intent(context, SelectSeatActivity::class.java).apply {
                putExtra(EXTRA_BOOKING_ID, bookingInfoUiModel)
            }
        }
    }
}
