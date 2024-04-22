package woowacourse.movie.presentation.reservation.result

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.db.MovieDao
import java.text.DecimalFormat

class ReservationResultActivity : AppCompatActivity(), ReservationResultContract.View {
    private val presenter: ReservationResultContract.Presenter = ReservationResultPresenter(
        this,
        MovieDao(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        presenter.fetchReservationDetail(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun setUpView(
        title: String,
        screenDate: String,
        count: Int,
        price: Int,
    ) {
        initTitle(title)
        initScreenDate(screenDate)
        initCount(count)
        initPrice(price)
    }

    private fun initTitle(title: String) {
        val titleTextView: TextView = findViewById(R.id.result_title_textview)
        titleTextView.text = title
    }

    private fun initScreenDate(screenDate: String) {
        val screenDataTextView: TextView = findViewById(R.id.result_screen_date_textview)
        screenDataTextView.text = screenDate
    }

    private fun initCount(count: Int) {
        val countTextView: TextView = findViewById(R.id.result_count_textview)
        countTextView.text = count.toString()
    }

    private fun initPrice(price: Int) {
        val priceTextView: TextView = findViewById(R.id.result_price_textview)
        priceTextView.text = DecimalFormat("#,###").format(price)
    }
}
