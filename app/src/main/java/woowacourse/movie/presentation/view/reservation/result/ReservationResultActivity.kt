package woowacourse.movie.presentation.view.reservation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.extension.getParcelableCompat
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.util.DialogInfo
import woowacourse.movie.presentation.view.movies.MoviesActivity

class ReservationResultActivity :
    BaseActivity(R.layout.activity_reservation_result),
    ReservationResultContract.View {
    private val views: ReservationResultViews by lazy { ReservationResultViews(this) }
    private val presenter: ReservationResultPresenter by lazy { ReservationResultPresenter(this) }

    private val invalidReservationDialogInfo: DialogInfo by lazy {
        DialogInfo(
            title = getString(R.string.invalid_reservation_dialog_title),
            message = getString(R.string.invalid_reservation_datetime_message),
            positiveButtonText = getString(R.string.invalid_reservation_dialog_positive),
            onClickPositiveButton = {
                onBackPressedDispatcher.onBackPressed()
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
        setBackPressedDispatcher()

        presenter.fetchDate {
            intent?.getParcelableCompat<ReservationInfoUiModel>(BUNDLE_KEY_RESERVATION_INFO)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navigateToMoviesScreen()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun setScreen(info: ReservationInfoUiModel) {
        views.bindReservationResult(info, presenter.cancellationTime)
    }

    override fun showInvalidReservationInfoDialog() {
        views.dialog.show(invalidReservationDialogInfo)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateToMoviesScreen()
                }
            },
        )
    }

    private fun navigateToMoviesScreen() {
        val intent = Intent(this@ReservationResultActivity, MoviesActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    companion object {
        private const val BUNDLE_KEY_RESERVATION_INFO = "reservation_info"

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfoUiModel,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).putExtra(
                BUNDLE_KEY_RESERVATION_INFO,
                reservationInfo,
            )
    }
}
