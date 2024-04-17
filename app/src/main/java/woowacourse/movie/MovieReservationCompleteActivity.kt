package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MovieReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)
        val ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("ticket", Ticket::class.java)
            } else {
                intent.getSerializableExtra("ticket") as? Ticket
            }
        Log.d("ticketticketticketticket", ticket?.price.toString())
    }
}
