package woowacourse.movie.seat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.movieInfo.MovieInfo

class TheaterSeatActivity : AppCompatActivity(), TheaterSeatContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seat)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun initializeViews(movieInfo: MovieInfo) {
        TODO("Not yet implemented")
    }

    override fun navigateToPurchaseConfirmation(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun showToPurchaseConfirmation(intent: Intent) {
        TODO("Not yet implemented")
    }

    override fun showDialog() {
        TODO("Not yet implemented")
    }

}
