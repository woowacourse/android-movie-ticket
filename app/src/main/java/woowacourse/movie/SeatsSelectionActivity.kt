package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.presenter.SeatsSelectionPresenter
import woowacourse.movie.ui.view.BaseActivity
import woowacourse.movie.ui.view.booking.BookingSummaryActivity
import woowacourse.movie.ui.view.booking.SeatsSelectionContract

class SeatsSelectionActivity :
    BaseActivity(),
    SeatsSelectionContract.View {
    private lateinit var presenter: SeatsSelectionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_seat_selection)

        presenter = SeatsSelectionPresenter(this)

        setupConfirmButtonListener()
    }

    override fun showBookingConfirmDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.complete)) { _, _ ->
                presenter.onConfirm()
            }.setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    override fun navigateToBookingSummary() {
        val movieTicket =
            intent.getSerializableExtra(getString(R.string.ticket_info_key)) as? MovieTicket

        val intent = Intent(this, BookingSummaryActivity::class.java)
        intent.putExtra(getString(R.string.ticket_info_key), movieTicket)
        startActivity(intent)
    }

    private fun setupConfirmButtonListener() {
        val confirmBtn = findViewById<Button>(R.id.confirm_button)

        confirmBtn.setOnClickListener {
            showBookingConfirmDialog()
        }
    }
}
