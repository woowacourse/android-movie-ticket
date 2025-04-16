package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val movieTitle = intent.getStringExtra("movieTitle")
        val movieDate = intent.getStringExtra("movieDate")

        val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)

        movieTitleTextView.text = movieTitle
        movieDateTextView.text = movieDate
    }
}
