package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class MovieDetailActivity : AppCompatActivity() {
    private var ticketNum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)

        val ticketPlusButton = findViewById<Button>(R.id.plus_button)
        val ticketMinusButton = findViewById<Button>(R.id.minus_button)
        val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)

        ticketPlusButton.setOnClickListener {
            ticketNum += 1
        }
        ticketMinusButton.setOnClickListener {
            if (ticketNum > 0) ticketNum -= 1
        }

        ticketBuyButton.setOnClickListener {
            val intent = Intent(this, PurchaseConfirmationActivity::class.java).apply {
                putExtra("ticketNum", ticketNum)
            }
            startActivity(intent)
        }
    }
}