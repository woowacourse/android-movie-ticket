package woowacourse.movie.presentation.view.reservation.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.extension.getParcelableCompat
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.ScreenUiModel
import woowacourse.movie.presentation.model.SeatUiModel
import woowacourse.movie.presentation.model.TicketBundleUiModel
import woowacourse.movie.presentation.util.DialogInfo
import woowacourse.movie.presentation.view.reservation.result.ReservationResultActivity

class ReservationSeatActivity :
    BaseActivity(R.layout.activity_reservation_seat),
    ReservationSeatContract.View {
    private val presenter: ReservationSeatPresenter by lazy { ReservationSeatPresenter(this) }
    private val views: ReservationSeatViews by lazy { ReservationSeatViews(this) }

    private val publishTicketConfirmationDialogInfo: DialogInfo by lazy {
        DialogInfo(
            title = getString(R.string.reservation_dialog_title),
            message = getString(R.string.reservation_dialog_message),
            positiveButtonText = getString(R.string.reservation_dialog_positive),
            negativeButtonText = getString(R.string.reservation_dialog_negative),
            onClickPositiveButton = { presenter.publishTickets() },
            onClickNegativeButton = { it.dismiss() },
        )
    }

    private val invalidReservationInfoDialogInfo: DialogInfo by lazy {
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

        val screen = intent?.getParcelableCompat<ScreenUiModel>(BUNDLE_KEY_SCREEN)
        val reservationInfo =
            if (savedInstanceState == null) {
                intent?.getParcelableCompat<ReservationInfoUiModel>(BUNDLE_KEY_RESERVATION_INFO)
            } else {
                savedInstanceState.getParcelableCompat<ReservationInfoUiModel>(
                    BUNDLE_KEY_RESERVATION_INFO,
                )
            }

        presenter.fetchData(reservationInfo, screen)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(BUNDLE_KEY_RESERVATION_INFO, presenter.reservationInfo)
    }

    override fun setScreen(
        reservationInfo: ReservationInfoUiModel,
        screen: ScreenUiModel,
        totalPrice: Int,
    ) {
        views.bind(
            reservationInfo,
            screen,
            { views.dialog.show(publishTicketConfirmationDialogInfo) },
            { seat -> presenter.updateSeat(seat) },
        )

        reservationInfo.seats.let {
            views.updateSeatsView(it)
            views.updateTotalPrice(totalPrice)
        }
    }

    override fun updateSeatStatus(
        seats: List<SeatUiModel>,
        totalPrice: Int,
        canPublish: Boolean,
    ) {
        views.updateSeatsView(seats)
        views.updateTotalPrice(totalPrice)
        views.updateConfirmButton(canPublish)
    }

    override fun notifyPublishedTickets(ticketBundle: TicketBundleUiModel) {
        val intent = ReservationResultActivity.newIntent(this, ticketBundle)
        startActivity(intent)
    }

    override fun notifyInvalidReservationInfo() {
        views.dialog.show(invalidReservationInfoDialogInfo)
    }

    override fun notifySeatUpdateFailed(message: String) {
        showToast(message.ifEmpty { getString(R.string.default_error_message) })
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val BUNDLE_KEY_RESERVATION_INFO = "reservation_info"
        private const val BUNDLE_KEY_SCREEN = "screen"

        fun newIntent(
            context: Context,
            reservationInfo: ReservationInfoUiModel,
            screen: ScreenUiModel,
        ): Intent =
            Intent(context, ReservationSeatActivity::class.java).apply {
                putExtra(BUNDLE_KEY_RESERVATION_INFO, reservationInfo)
                putExtra(BUNDLE_KEY_SCREEN, screen)
            }
    }
}
