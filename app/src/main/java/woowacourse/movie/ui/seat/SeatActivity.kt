package woowacourse.movie.ui.seat

import android.content.Context
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
import woowacourse.movie.domain.model.PurchaseCount
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Tickets
import woowacourse.movie.ui.extensions.serializableData
import java.text.DecimalFormat

class SeatActivity : AppCompatActivity(), SeatContract.View {
    private lateinit var presenter: SeatContract.Presenter
    private lateinit var table: TableLayout
    private lateinit var movieTitle: TextView
    private lateinit var selectTotalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat)
        initSystemUI()
        initViewId()
        presenter = SeatPresenter(this)
        initData()
    }

    private fun initSystemUI() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.seat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViewId() {
        table = findViewById(R.id.tl_seat)
        movieTitle = findViewById(R.id.tv_seat_select_movie_title)
        selectTotalPrice = findViewById(R.id.tv_seat_select_total_price)
    }

    private fun initData() {
        val rowsRate =
            table.children.map { view ->
                val tableRow = view as TableRow
                tableRow.tag.toString()
            }.toList()
        val columns =
            table.children.map {
                val tableRow = it as TableRow
                tableRow.childCount
            }.toList()

        val reservation = reservation()
        val purchaseCount = purchaseCount()
        if (reservation != null && purchaseCount >= 1) {
            presenter.initData(rowsRate, columns, reservation, PurchaseCount(purchaseCount))
        }
    }

    private fun reservation() = intent.serializableData(KEY_SEAT_ACTIVITY_RESERVATION, Reservation::class.java)

    private fun purchaseCount() = intent.getIntExtra(KEY_SEAT_ACTIVITY_PURCHASE_COUNT, 0)

    override fun initView(
        title: String,
        tickets: Tickets?,
    ) {
        val totalPrice = tickets?.totalPrice() ?: 0
        movieTitle.text = title
        selectTotalPrice.text = wonFormat(this).format(totalPrice)
    }

    companion object {
        private fun wonFormat(context: Context) = DecimalFormat(context.getString(R.string.won_format))

        const val KEY_SEAT_ACTIVITY_RESERVATION = "key_seat_activity_reservation"
        const val KEY_SEAT_ACTIVITY_PURCHASE_COUNT = "key_seat_activity_purchase_count"
    }
}
