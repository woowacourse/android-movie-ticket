package woowacourse.movie.activity

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.domain.model.model.Seat
import woowacourse.movie.R
import woowacourse.movie.mapper.toSeatModel
import woowacourse.movie.model.ReservationInfoModel
import woowacourse.movie.model.SeatModel
import woowacourse.movie.util.customGetSerializable

const val ROW_COUNT = 5
const val COLUMN_COUNT = 4

class ReserveSeatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_seat)

        val seatViews: List<Button> = findViewById<TableLayout>(R.id.table_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<Button>().toList()

        val reservationInfoModel: ReservationInfoModel = intent.customGetSerializable(INFO_KEY)

        val title = findViewById<TextView>(R.id.text_title)
        title.text = reservationInfoModel.title

        val price = findViewById<TextView>(R.id.text_price)
        price.text = "0"

        var selectCount = 0

        val reserveButton = findViewById<Button>(R.id.btn_reserve)
        seatViews.forEach { button ->
            button.setOnClickListener {
                if (button.isSelected) {
                    button.setBackgroundColor(Color.WHITE)
                    button.isSelected = false
                    selectCount--
                    // 가격을 계산해줘!! 라고 도메인에게 시킴
                    // 등급과 가격을 매핑해주는 도메인
                } else {
                    button.setBackgroundColor(Color.parseColor("#FAFF00"))
                    button.isSelected = true
                    selectCount++
                }
//                price.text = (ticketModel.price * selectCount).toString()
                reserveButton.isEnabled = selectCount == reservationInfoModel.count
            }
        }

        reserveButton.setOnClickListener {
            // 선택된 버튼 + ticketingInfo intent로 보내기
            val selectedButton = mutableListOf<SeatModel>()
            seatViews.forEachIndexed { index, button ->
                if (button.isSelected) {
                    val row = index / COLUMN_COUNT
                    val column = index % COLUMN_COUNT
                    selectedButton.add(Seat(row, column).toSeatModel())
                }
            }
            selectedButton.forEach {
                println(it.row + it.column)
            }
//            val intent = Intent(this, TicketResultActivity::class.java)
//            val ticketModel = getTicketModel(movieTitle)
//            intent.putExtra(MovieDetailActivity.INFO_KEY, ticketModel)
//            startActivity(intent)
        }
        setActionBar()
    }

    private fun setActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        private const val INFO_KEY = "ticketingInfo"
    }
}
