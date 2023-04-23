package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationCompletedBinding
import woowacourse.movie.util.DATE_TIME_FORMATTER
import woowacourse.movie.util.getParcelableCompat
import woowacourse.movie.view.model.ReservationUiModel
import java.text.DecimalFormat

class ReservationCompletedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationCompletedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationCompletedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reservation =
            intent.getParcelableCompat<ReservationUiModel>(RESERVATION)
        reservation?.let { initViewData(it) }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent =
                        Intent(this@ReservationCompletedActivity, MovieListActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                }
            }
        )
    }

    private fun initViewData(reservation: ReservationUiModel) {
        binding.apply {
            movieTitle.text = reservation.title
            movieScreeningDate.text = reservation.screeningDateTime.format(DATE_TIME_FORMATTER)
            peopleCount.text = getString(R.string.reservation_people_count_format)
                .format(
                    getString(R.string.general_person),
                    reservation.peopleCount,
                    reservation.seats.joinToString()
                )
            totalPrice.text =
                getString(R.string.total_price_format).format(DECIMAL_FORMAT.format(reservation.finalReservationFee))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MovieListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val RESERVATION = "RESERVATION"
        private val DECIMAL_FORMAT = DecimalFormat("#,###")

        fun newIntent(context: Context, reservation: ReservationUiModel): Intent {
            val intent = Intent(context, ReservationCompletedActivity::class.java)
            intent.putExtra(RESERVATION, reservation)
            return intent
        }
    }
}
