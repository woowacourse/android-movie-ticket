package woowacourse.movie.activity

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.theater.Theater

class PurchaseConfirmationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_confirmation)
        val theater = intent.getSerializableExtra("Theater", Theater::class.java)
        val ticketNum = intent.getIntExtra("ticketNum", 0)

        if(theater != null) {
            val movie = theater.movie
            findViewById<TextView>(R.id.movie_title_confirmation).text = movie.title.toString()
            findViewById<TextView>(R.id.movie_screening_day).text = movie.releaseDate.toString()
        }

        val charge = if(theater != null) theater.charge else 13000
        findViewById<TextView>(R.id.ticket_charge).text = "price: ${ticketNum*charge}"
    }
}