package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initView()
        setListView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setListView() {
        val itemList =
            Array(1) {
                Movie(
                    "해리 포터와 마법사의 돌",
                    R.drawable.harry_potter_one,
                    "2025.4.1",
                    "152분",
                )
            }

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter =
            MovieAdapter(
                items = itemList.toList(),
                onClickBooking = { idx ->
                    val target = itemList[idx]
                    moveToBookingComplete(target.title, target.releaseDate)
                },
            )

        listView.adapter = adapter
    }

    private fun moveToBookingComplete(
        movieTitle: String,
        movieBookingTime: String,
    ) {
        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra("title", movieTitle)
                putExtra("bookingTime", movieBookingTime)
            }
        startActivity(intent)
    }
}
