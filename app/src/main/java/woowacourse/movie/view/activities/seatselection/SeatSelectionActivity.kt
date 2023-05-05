package woowacourse.movie.view.activities.seatselection

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import woowacourse.movie.R
import woowacourse.movie.view.activities.reservationresult.ReservationResultActivity
import java.time.LocalDateTime
import kotlin.properties.Delegates

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {

    private lateinit var presenter: SeatSelectionContract.Presenter

    private var selectedSeatNames: Set<String> by Delegates.observable(setOf()) { _, _, new ->
        presenter.setSelectedSeats(new)
        findViewById<Button>(R.id.reservation_btn).isEnabled = new.isNotEmpty()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection2)
        presenter = SeatSelectionPresenter(
            this,
            intent.getLongExtra(SCREENING_ID, -1),
            getScreeningDateTimeFromIntent()
        )

        presenter.loadScreening()
        initReservationButtonOnClickListener()
    }

    private fun getScreeningDateTimeFromIntent(): LocalDateTime =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(SCREENING_DATE_TIME, LocalDateTime::class.java)
                ?: throw IllegalArgumentException("이 액티비티는 인텐트에 상영 시각이 저장되어 있을 때만 실행될 수 있습니다.")
        } else {
            intent.getSerializableExtra(SCREENING_DATE_TIME) as LocalDateTime
        }

    private fun initReservationButtonOnClickListener() {
        val reservationButton = findViewById<Button>(R.id.reservation_btn)
        reservationButton.setOnClickListener {
            presenter.reserve(selectedSeatNames)
        }
    }

    override fun setSeats(seatUIStates: List<List<SeatUIState>>) {
        val seatsView = findViewById<TableLayout>(R.id.seat_table)
        seatUIStates.forEach {
            val tableRow = TableRow(this).apply {
                layoutParams = TableLayout.LayoutParams(0, 0, 1f)
            }
            it.forEach { seatUIState ->
                tableRow.addView(createSeatUI(seatUIState.seatName, seatUIState.textColor))
            }
            seatsView.addView(tableRow)
        }
    }

    private fun createSeatUI(seatName: String, @ColorRes textColor: Int): AppCompatButton =
        AppCompatButton(this).apply {
            text = seatName
            setTextColor(getColor(textColor))
            setBackgroundColor(getColor(R.color.unselected_seat_color))
            setOnClickListener { onSeatButtonClick(this) }
            layoutParams = TableRow.LayoutParams(0, Toolbar.LayoutParams.MATCH_PARENT, 1f)
        }

    private fun onSeatButtonClick(button: AppCompatButton) {
        fun deselect(button: AppCompatButton) {
            button.isSelected = false
            button.setBackgroundColor(getColor(R.color.unselected_seat_color))
            selectedSeatNames = selectedSeatNames - button.text.toString()
        }

        fun select(button: AppCompatButton) {
            button.isSelected = true
            button.setBackgroundColor(getColor(R.color.selected_seat_color))
            selectedSeatNames = selectedSeatNames + button.text.toString()
        }

        if (button.isSelected) deselect(button)
        else select(button)
    }

    override fun setMovieTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.movie_title_tv)
        titleView.text = title
    }

    override fun setReservationFee(fee: Int) {
        val reservationFeeView = findViewById<TextView>(R.id.reservation_fee_tv)
        reservationFeeView.text = getString(R.string.fee_format).format(fee)
    }

    override fun startReservationResultActivity(reservationId: Long) {
        ReservationResultActivity.startActivity(this, reservationId)
    }

    companion object {
        const val SCREENING_ID = "SCREENING_ID"
        const val SCREENING_DATE_TIME = "SCREENING_DATE_TIME"

        fun startActivity(context: Context, screeningId: Long, screeningDateTime: LocalDateTime) {
            val intent = Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(SCREENING_ID, screeningId)
                putExtra(SCREENING_DATE_TIME, screeningDateTime)
            }
            context.startActivity(intent)
        }
    }
}
