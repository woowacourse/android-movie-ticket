package woowacourse.movie.ui.view.booking.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.presenter.booking.seat.BookingSeatContract
import woowacourse.movie.presenter.booking.seat.BookingSeatPresenter
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.util.DialogUtil

class BookingSeatActivity : AppCompatActivity(), BookingSeatContract.View {
    private lateinit var presenter: BookingSeatContract.Presenter
    private lateinit var seatViewCached: Map<String, TextView>
    private val seatsTableLayout: TableLayout by lazy { findViewById(R.id.tl_seat) }
    private val nonClickedBackGroundResource: Int by lazy {
        resources.getColor(R.color.white, null)
    }
    private val clickedBackGroundResource: Int by lazy {
        resources.getColor(R.color.booking_seat_click_bg, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_seat)
        applySystemBarInsets()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = BookingSeatPresenter(this@BookingSeatActivity, bookingResultUiOrNull())
        seatViewCached = initialSeatTextView()
        presenter.loadInfos()
    }

    override fun showSeatView(
        seatPosition: String,
        isSelected: Boolean,
    ) {
        if (isSelected) {
            seatViewCached[seatPosition]?.setBackgroundColor(clickedBackGroundResource)
            presenter.updatePrice()
        } else {
            seatViewCached[seatPosition]?.setBackgroundColor(nonClickedBackGroundResource)
            presenter.updatePrice()
        }
    }

    override fun showErrorMessage(message: String) {
        DialogUtil.showError(
            activity = this@BookingSeatActivity,
            message = message,
        )
    }

    override fun showFullScreen(
        movieTitle: String,
        totalPrice: String,
    ) {
        val movieTitleView: TextView = findViewById(R.id.tv_booking_seat_movie_title)
        movieTitleView.text = movieTitle
        showTotalPrice(totalPrice)
    }

    override fun showTotalPrice(totalPrice: String) {
        val totalPriceView: TextView = findViewById(R.id.tv_booking_seat_movie_price)
        totalPriceView.text = getString(R.string.seat_total_price, totalPrice)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun bookingResultUiOrNull() =
        IntentCompat.getParcelableExtra(
            intent,
            EXTRA_BOOKING_RESULT,
            BookingResultUiModel::class.java,
        )

    private fun initialSeatTextView(): Map<String, TextView> {
        return seatsTableLayout.children.filterIsInstance<TableRow>().flatMap { tableRow ->
            tableRow.children.filterIsInstance<TextView>().map { textView ->
                val seatPosition = textView.text.toString()
                textView.setOnClickListener {
                    presenter.toggleBackGroundColor(seatPosition)
                }
                seatPosition to textView
            }
        }.toMap()
    }

    private fun applySystemBarInsets() {
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
