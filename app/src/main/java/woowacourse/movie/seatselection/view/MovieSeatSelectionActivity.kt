package woowacourse.movie.seatselection.view

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.MovieGrade.Companion.judgeGradeByRow
import woowacourse.movie.result.view.MovieResultActivity
import woowacourse.movie.util.MovieIntentConstant.INVALID_VALUE_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_COUNT
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_DATE
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_SEATS
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TIME
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TITLE
import java.text.DecimalFormat

class MovieSeatSelectionActivity : AppCompatActivity() {
    private lateinit var table: TableLayout
    private lateinit var seatTitle: TextView
    private lateinit var seatPrice: TextView
    private lateinit var completeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_selection)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        seatTitle.text = intent?.getStringExtra(KEY_MOVIE_TITLE)
        val count = intent?.getIntExtra(KEY_MOVIE_COUNT, INVALID_VALUE_MOVIE_COUNT)
        val seats = mutableListOf<String>()
        var totalPrice = 0
        var selectedCount = 0

        table.children.forEachIndexed { rowIndex, row ->
            row as TableRow
            row.children.forEachIndexed { seatIndex, seat ->
                seat as TextView
                val seatRow = ('A'.code + rowIndex).toChar()
                val seatColumn = seatIndex + 1
                seat.text = getString(R.string.seat, seatRow, seatColumn)
                seat.setOnClickListener {
                    val yellowColor = ContextCompat.getColor(this, R.color.yellow)
                    if ((seat.background as? ColorDrawable)?.color == yellowColor) {
                        seat.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                        seats.remove(seat.text.toString())
                        totalPrice -= judgeGradeByRow(seatRow).price
                        selectedCount -= 1
                    } else {
                        if (selectedCount != count) {
                            seat.setBackgroundColor(yellowColor)
                            seats.add(seat.text.toString())
                            totalPrice += judgeGradeByRow(seatRow).price
                            selectedCount += 1
                        }
                    }
                    seatPrice.text = DecimalFormat("#,###").format(totalPrice)
                    completeButton.isEnabled = selectedCount == count
                }
            }
        }

        completeButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    Intent(this, MovieResultActivity::class.java).apply {
                        putExtra(KEY_MOVIE_TITLE, intent?.getStringExtra(KEY_MOVIE_TITLE))
                        putExtra(KEY_MOVIE_DATE, intent?.getStringExtra(KEY_MOVIE_DATE))
                        putExtra(KEY_MOVIE_TIME, intent?.getStringExtra(KEY_MOVIE_TIME))
                        putExtra(
                            KEY_MOVIE_COUNT,
                            count,
                        )
                        putExtra(KEY_MOVIE_SEATS, seats.sorted().joinToString(", "))
                        startActivity(this)
                    }
                }
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpViewById() {
        table = findViewById(R.id.table)
        seatTitle = findViewById(R.id.seatTitle)
        seatPrice = findViewById(R.id.seatPrice)
        completeButton = findViewById(R.id.completeBtn)
    }
}
