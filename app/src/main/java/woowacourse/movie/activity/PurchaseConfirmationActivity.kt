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
        val theater = intent.getSerializableExtra("theater", Theater::class.java)
        val charge= if (theater!=null) theater.charge else 13000
        val ticketNum = intent.getIntExtra("ticketNum", 0)
        findViewById<TextView>(R.id.ticket_charge).text = "price: ${ticketNum*charge}"
    }
}