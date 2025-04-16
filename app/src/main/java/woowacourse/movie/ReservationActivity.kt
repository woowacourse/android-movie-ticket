package woowacourse.movie

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val memberPlusButton = findViewById<Button>(R.id.plus_button)
        val memberMinusButton = findViewById<Button>(R.id.minus_button)
        val memberCount = findViewById<TextView>(R.id.count)

        memberPlusButton.setOnClickListener {
            memberCount.text = memberCount.text.toString()
                .toIntOrNull()
                ?.plus(1)
                ?.toString()
                ?: "1"
        }

        memberMinusButton.setOnClickListener {
            memberCount.text = memberCount.text.toString()
                .toIntOrNull()
                ?.minus(1)
                ?.toString()
                ?: "1"
        }


    }

}