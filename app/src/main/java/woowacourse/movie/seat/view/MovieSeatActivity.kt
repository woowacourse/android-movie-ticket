package woowacourse.movie.seat.view

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
import woowacourse.movie.result.view.MovieResultActivity
import woowacourse.movie.util.MovieIntentConstant.KEY_MOVIE_TITLE

class MovieSeatActivity : AppCompatActivity() {
    private lateinit var table: TableLayout
    private lateinit var seatTitle: TextView
    private lateinit var seatPrice: TextView
    private lateinit var completeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)
        setUpViewById()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        seatTitle.text = intent?.getStringExtra(KEY_MOVIE_TITLE)

        table.children.forEachIndexed { rowIndex, row ->
            row as TableRow
            row.children.forEachIndexed { seatIndex, seat ->
                seat as TextView
                seat.text = getString(R.string.seat, ('A'.code + rowIndex).toChar(), seatIndex + 1)
                seat.setOnClickListener {
                    val yellowColor = ContextCompat.getColor(this, R.color.yellow)
                    if ((seat.background as? ColorDrawable)?.color == yellowColor) {
                        seat.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                    } else {
                        seat.setBackgroundColor(yellowColor)
                    }
                }
            }
        }

        completeButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    Intent(this, MovieResultActivity::class.java).apply {
//                        putExtra(KEY_MOVIE_TITLE, title)
//                        putExtra(KEY_MOVIE_DATE, date)
//                        putExtra(KEY_MOVIE_TIME, time)
//                        putExtra(KEY_MOVIE_COUNT, count)
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
