package woowacourse.movie.seat

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.model.movieInfo.MovieInfo

class TheaterSeatActivity: AppCompatActivity(), TheaterSeatContract.View {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
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
