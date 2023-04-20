package woowacourse.movie.activity.seats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.uimodel.MovieModel

class SeatSelectionActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieModel: MovieModel = intent.getSerializableExtra(MovieModel.MOVIE_INTENT_KEY) as MovieModel
        binding.movieNameTextView.text = movieModel.name.value

        SeatsView(binding, intent).set()
    }
}
