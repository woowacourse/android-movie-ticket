package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListView()
    }

    private fun setupListView() {
        val listView = findViewById<ListView>(R.id.movie_list)
        val dummy = (0..10000).map {
            Movie(
                poster = R.drawable.poster,
                title = "해리 포터 $it",
                date = "2024-04-12",
                runTime = "152분"
            )
        }
        listView.adapter = MovieAdapter(dummy)
        listView.setOnItemClickListener { _, _, position, _ ->
            startActivity(
                Intent(
                    this,
                    DetailActivity::class.java
                ).apply {
                    putExtra("movie", dummy[position])
                }
            )
        }
    }
}
