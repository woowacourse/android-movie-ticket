package woowacourse.movie.view.selectSeat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.extension.getParcelableExtraCompat
import woowacourse.movie.presenter.SelectSeatPresenter
import woowacourse.movie.view.model.TicketData
import woowacourse.movie.view.ticket.TicketActivity

class SelectSeatActivity :
    AppCompatActivity(),
    SelectSeatView {
    private val present: SelectSeatPresenter = SelectSeatPresenter(this)
    private var dialog: AlertDialog? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        outState.putInt(TICKET_COUNT, present.ticketCount.value)
//        outState.putInt(TIME_ITEM_POSITION, present.timeItemPosition)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_select_seat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//
//        val savedTicketCount = savedInstanceState?.getInt(TICKET_COUNT) ?: DEFAULT_TICKET_COUNT
//        val savedTimeItemPosition =
//            savedInstanceState?.getInt(TIME_ITEM_POSITION) ?: DEFAULT_TIME_ITEM_POSITION

//        present.initReservationData(savedTicketCount, savedTimeItemPosition)
//        present.initSelectSeatUI()
//        initTicketPlusBtnUi()
//        initTicketMinusBtnUi()
//        initCompleteButtonView()

        present.initSelectSeatUI()
        // TODO: 임시 버튼 연결
        findViewById<TextView>(R.id.tv_select_seat_confirm).setOnClickListener { present.navigateToTicketUI() }
    }

    override fun getTicketData(): TicketData =
        intent.getParcelableExtraCompat<TicketData>(EXTRA_TICKET_DATA)
            ?: throw IllegalArgumentException(ERROR_CANT_READ_TICKET_INFO)

    override fun initMovieTitleUI(ticketData: TicketData) {
        val titleView = findViewById<TextView>(R.id.tv_select_seat_movie_title)
        titleView.text = ticketData.screeningData.title
    }

    override fun printError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToTicketUI(ticketData: TicketData) {
        startActivity(TicketActivity.newIntent(this, ticketData))
    }

    override fun onDestroy() {
        dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        dialog = null
        super.onDestroy()
    }

    companion object {
        private const val ERROR_CANT_READ_TICKET_INFO = "티켓 정보가 전달되지 않았습니다"

        private const val EXTRA_TICKET_DATA = "woowacourse.movie.EXTRA_TICKET_DATA"

        fun newIntent(
            context: Context,
            ticketData: TicketData,
        ): Intent =
            Intent(context, SelectSeatActivity::class.java).apply {
                putExtra(EXTRA_TICKET_DATA, ticketData)
            }
    }
}
