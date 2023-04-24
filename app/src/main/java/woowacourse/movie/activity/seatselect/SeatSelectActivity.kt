package woowacourse.movie.activity.seatselect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.ticketresult.TicketResultActivity
import woowacourse.movie.domain.price.PriceCalculator
import woowacourse.movie.domain.system.PriceSystem
import woowacourse.movie.domain.system.SeatSelectSystem
import woowacourse.movie.model.ReserveInfoModel
import woowacourse.movie.model.TicketModel
import woowacourse.movie.util.Theater
import woowacourse.movie.util.getSerializableExtraCompat

class SeatSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)

        val reserveInfo = intent.getSerializableExtraCompat<ReserveInfoModel>(INFO_KEY)
        if (reserveInfo == null) {
            Toast.makeText(this, DATA_LOADING_ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val title = reserveInfo.title
        val dateTime = reserveInfo.dateTime
        val count = reserveInfo.count

        val seatSelectSystem = SeatSelectSystem(Theater.info, count)
        val priceSystem = PriceSystem(PriceCalculator(Theater.policies), dateTime)
        val seatView =
            SeatSelectView(
                findViewById(R.id.layout_select_seat),
                seatSelectSystem,
                priceSystem,
                ::onClick,
            )

        seatView.set(title, dateTime)

        Log.d("hyem", seatSelectSystem.seats.toString())
    }

    private fun onClick(model: TicketModel) {
        val intent = Intent(this, TicketResultActivity::class.java)
        intent.putExtra(TicketResultActivity.INFO_KEY, model)
        startActivity(intent)
    }

    companion object {
        const val INFO_KEY = "INFO"
        private const val DATA_LOADING_ERROR_MESSAGE = "데이터가 로딩되지 않았습니다. 다시 시도해주세요."
        const val TITLE_KEY = "TITLE"
        const val DATETIME_KEY = "DATETIME"
        const val COUNT_KEY = "COUNT"
    }
}
