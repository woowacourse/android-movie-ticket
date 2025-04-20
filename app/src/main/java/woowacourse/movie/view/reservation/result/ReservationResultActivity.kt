package woowacourse.movie.view.reservation.result

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.movies.MoviesActivity

class ReservationResultActivity : BaseActivity(R.layout.activity_reservation_result) {
    private lateinit var views: ReservationResultViews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToMovies()
                }
            },
        )
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        setupActionBar()
        views = ReservationResultViews(this)

        val reservationInfo =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getSerializableExtra(
                    BUNDLE_KEY_RESERVATION_INFO,
                    ReservationInfo::class.java,
                )
            } else {
                intent?.getSerializableExtra(BUNDLE_KEY_RESERVATION_INFO) as? ReservationInfo
            }

        reservationInfo?.let { info -> views.bindReservationResult(info) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, MoviesActivity::class.java))
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun navigateToMovies() {
        val intent = Intent(this@ReservationResultActivity, MoviesActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val BUNDLE_KEY_RESERVATION_INFO = "reservation_info"

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfo,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).putExtra(
                BUNDLE_KEY_RESERVATION_INFO,
                reservationInfo,
            )
    }
}
