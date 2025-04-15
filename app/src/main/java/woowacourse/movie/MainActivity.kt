package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initListView()
    }

    private fun initListView() {
        val movies =
            listOf(
                Movie(
                    "해리 포터와 마법사의 돌",
                    LocalDate.of(2025, 4, 1),
                    150,
                    R.drawable.poster_harry_potter_and_the_philosophers_stone,
                ),
            )

        val movieListView = findViewById<ListView>(R.id.main)

        val movieAdapter = MovieAdapter(movies, navigateToReservationResultActivity)
        movieListView.adapter = movieAdapter
    }

    private val navigateToReservationResultActivity = {
        val intent = Intent(this, ReservationResultActivity::class.java)
        startActivity(intent)
    }
}
