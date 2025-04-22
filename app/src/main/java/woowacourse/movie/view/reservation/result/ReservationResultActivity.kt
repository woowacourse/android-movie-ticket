package woowacourse.movie.view.reservation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import woowacourse.movie.R
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.getParcelableCompat
import woowacourse.movie.view.model.ReservationInfoUiModel
import woowacourse.movie.view.model.toModel
import woowacourse.movie.view.model.toUiModel
import woowacourse.movie.view.movies.MoviesActivity

class ReservationResultActivity : BaseActivity(R.layout.activity_reservation_result) {
    private lateinit var views: ReservationResultViews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()

        views = ReservationResultViews(this)
        val reservationInfo = intent?.getParcelableCompat<ReservationInfoUiModel>(BUNDLE_KEY_RESERVATION_INFO)?.toModel()
        reservationInfo?.let { info -> views.bindReservationResult(info) }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToMovies()
                }
            },
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navigateToMovies()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun navigateToMovies() {
        val intent = Intent(this@ReservationResultActivity, MoviesActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        private const val BUNDLE_KEY_RESERVATION_INFO = "reservation_info"

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfo,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).putExtra(
                BUNDLE_KEY_RESERVATION_INFO,
                reservationInfo.toUiModel(),
            )
    }
}
