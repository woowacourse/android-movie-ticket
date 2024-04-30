package woowacourse.movie.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.IntentKeys.SEAT_PLAN
import woowacourse.movie.R
import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.model.Theater
import woowacourse.movie.model.pricing.Tier
import woowacourse.movie.model.seat.Position
import woowacourse.movie.presenter.SeatSelectionPresenter
import woowacourse.movie.uiModels.SeatPlan
import woowacourse.movie.uiModels.reservation.format

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private lateinit var presenter: SeatSelectionPresenter
    private lateinit var gridLayout: GridLayout
    private lateinit var ticketPrice: TextView
    private lateinit var movieTitle: TextView
    private lateinit var confirmButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seat_selection)
        confirmButton = findViewById<TextView>(R.id.purchase_confirm)
        movieTitle = findViewById(R.id.seat_selection_movie_title)
        ticketPrice = findViewById(R.id.seat_selection_ticket_price)
        presenter =
            SeatSelectionPresenter(
                view = this,
            )
        val seatPlan =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(SEAT_PLAN, SeatPlan::class.java)
            } else {
                intent.getParcelableExtra(Intent.EXTRA_STREAM) as? SeatPlan
            }
        if (seatPlan != null) {
            presenter.loadData(
                seatPlan.movieId,
                seatPlan.ticketNum,
                seatPlan.reservedDateTime,
            )
        } else {
            finish()
        }
        confirmButton.setOnClickListener {
            presenter.askConfirm()
        }
    }

    override fun displayTheater(theater: Theater) {
        gridLayout =
            findViewById<GridLayout>(R.id.seat_selection_grid_layout).apply {
                columnCount = theater.cols
            }
        theater.tiers.forEach { (position, tier) ->
            val color =
                when (tier) {
                    Tier.B -> getColor(R.color.purple)
                    Tier.S -> getColor(R.color.green)
                    Tier.A -> getColor(R.color.blue)
                }
            val seatView =
                LayoutInflater.from(this).inflate(R.layout.seat_button, null, false)
            val button = seatView.findViewById<Button>(R.id.seat_button)
            button.apply {
                setTextColor(color)
                text = position.format()
                id = R.id.seat_button * 100 + position.x * 10 + position.y
            }
            button.setOnClickListener {
                presenter.toggleSeatSelection(position)
            }

            gridLayout.addView(seatView)
        }
    }

    override fun displaySelectedSeat(position: Position) {
        val id = R.id.seat_button * 100 + position.x * 10 + position.y
        val button = findViewById<Button>(id)
        button.setBackgroundColor(getColor(R.color.yellow))
    }

    override fun displayDeSelectedSeat(position: Position) {
        val id = R.id.seat_button * 100 + position.x * 10 + position.y
        val button = findViewById<Button>(id)
        button.setBackgroundColor(getColor(R.color.white))
    }

    override fun activateConfirm() {
        confirmButton.isClickable = true
        confirmButton.setBackgroundColor(getColor(R.color.purple_500))
    }

    override fun deActivateConfirm() {
        confirmButton.isClickable = false
        confirmButton.setBackgroundColor(getColor(R.color.gray))
    }

    override fun displayTicketPrice(price: Int) {
        ticketPrice.text = price.toString()
    }

    override fun displayMovieTitle(title: String) {
        movieTitle.text = title
    }

    override fun displayConfirmDialog() {
        // val dialog = CustomDialog()
        val dialog =
            AlertDialog.Builder(this)
                .setTitle("결제 확인")
                .setMessage("결제하시겠습니까?")
                .setPositiveButton("예") { _, _ -> presenter.purchase() }
                .setNegativeButton("아니요") { dialog, _ ->
                    dialog.dismiss()
                }.setCancelable(false)
                .create()
        dialog.window?.setDimAmount(0.5f) // 0.5는 50% 투명도를 나타냅니다.
        dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.show()
    }

    override fun navigateToPurchaseConfirmation() {
        val intent = Intent(this, PurchaseConfirmationActivity::class.java)
        startActivity(intent)
    }
}
