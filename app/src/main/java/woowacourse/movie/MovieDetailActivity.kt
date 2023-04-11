package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val image = findViewById<ImageView>(R.id.detail_image)
        val title = findViewById<TextView>(R.id.detail_title)
        val date = findViewById<TextView>(R.id.detail_date)
        val time = findViewById<TextView>(R.id.detail_time)
        val description = findViewById<TextView>(R.id.description)

        val minus = findViewById<Button>(R.id.minus)
        val plus = findViewById<Button>(R.id.plus)
        val count = findViewById<TextView>(R.id.count)
        minus.setOnClickListener {
            var previous = count.text.toString().toInt()
            previous--
            count.text = previous.toString()
        }
        plus.setOnClickListener {
            var previous = count.text.toString().toInt()
            previous++
            count.text = previous.toString()
        }

        val movie = intent.getSerializableExtra(MainActivity.KEY_MOVIE_DATA) as Movie
        Log.d("mendel", "$movie")

        image.setImageDrawable(ResourcesCompat.getDrawable(image.resources, movie.imgResourceId, null))
        title.text = movie.title
        date.text = movie.date.toString()
        time.text = movie.runningTime.toString()
        description.text = movie.description
    }
}
