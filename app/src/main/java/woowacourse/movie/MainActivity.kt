package woowacourse.movie

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieInfo = MovieInfo(Title("MovieTitle"), RunningTime(230), Synopsis("wow!"))
        val theater = Theater(movieInfo)

        setContentView(R.layout.movie_list_item)
        val detailsButton = findViewById<Button>(R.id.movie_details_button)
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("Theater",theater)
        detailsButton.setOnClickListener {
            startActivity(intent)
        }
    }
}

class MovieDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        val mainActivityIntent = getIntent()

        val ticketPlusButton = findViewById<Button>(R.id.plus_button)
        val ticketMinusButton = findViewById<Button>(R.id.minus_button)
        val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)

        var ticketNum = 0
        ticketMinusButton.setOnClickListener {
            ticketNum -= 1
        }
        ticketPlusButton.setOnClickListener {
            ticketNum += 1
        }
        val intent = Intent(this, PurchaseConfirmationActivity::class.java)
        intent.putExtra("ticketNum", ticketNum)
        ticketBuyButton.setOnClickListener {
            startActivity()
        }
    }
}

class PurchaseConfirmationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_confirmation)
        val intent = getIntent()
        val ticketNum = intent.getIntExtra("ticketNum")
        val charge
    }
}