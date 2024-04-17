package woowacourse.movie.ui.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ReservationActivity::class.java)
            context.startActivity(intent)
        }
    }
}
