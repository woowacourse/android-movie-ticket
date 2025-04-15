package woowacourse.movie.ui.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.ReservedMovie
import java.io.Serializable

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val reservedMovie = intent.intentSerializable("Reservation", ReservedMovie::class.java)
        val title = findViewById<TextView>(R.id.title)
        val screeningDate = findViewById<TextView>(R.id.screeningDate)
        title.text = reservedMovie.title
        screeningDate.text = reservedMovie.screeningDate.toString()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T: Serializable> Intent.intentSerializable(key: String, customClass: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, customClass)!!
        } else {
            this.getSerializableExtra(key) as T
        }
    }
}