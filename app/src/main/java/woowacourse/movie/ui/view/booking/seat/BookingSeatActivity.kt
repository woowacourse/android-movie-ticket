package woowacourse.movie.ui.view.booking.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.ui.model.booking.BookingResultUiModel

class BookingSeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val EXTRA_BOOKING_RESULT = "extra_selected_movie_item"

        fun newIntent(
            context: Context,
            bookingResultUiModel: BookingResultUiModel,
        ): Intent {
            return Intent(
                context,
                BookingSeatActivity::class.java,
            ).apply { putExtra(EXTRA_BOOKING_RESULT, bookingResultUiModel) }
        }
    }
}
