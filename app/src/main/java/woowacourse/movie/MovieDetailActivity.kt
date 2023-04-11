package woowacourse.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getSerializableExtra(MainActivity.KEY_MOVIE_DATA)
        Log.d("mendel", "$movie")
    }
}
