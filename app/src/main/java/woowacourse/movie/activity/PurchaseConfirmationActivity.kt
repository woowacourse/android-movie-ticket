package woowacourse.movie.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class PurchaseConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_confirmation)

        val ticketNum = intent.getIntExtra("ticketNum", 0)
        // Here you might display the ticketNum or handle the purchase
    }
}