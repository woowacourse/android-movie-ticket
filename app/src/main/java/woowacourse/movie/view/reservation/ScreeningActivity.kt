package woowacourse.movie.view.reservation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.contract.reservation.ScreeningContract
import woowacourse.movie.domain.reservation.Screening
import woowacourse.movie.domain.reservation.ScreeningContent
import woowacourse.movie.presenter.reservation.ScreeningPresenter
import woowacourse.movie.view.reservation.adapter.ScreeningAdapter

class ScreeningActivity :
    AppCompatActivity(),
    ScreeningContract.View {
    private val presenter: ScreeningContract.Presenter = ScreeningPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screening)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lv_screening_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.presentScreenings()
    }

    override fun setScreenings(screeningContents: List<ScreeningContent>) {
        initListView(screeningContents)
    }

    override fun navigateToReservationScreen(screening: Screening) {
        val intent = ReservationActivity.newIntent(this, screening)
        startActivity(intent)
    }

    private fun initListView(screeningContents: List<ScreeningContent>) {
        val screenings: List<ScreeningContent> =
            intent?.getScreeningsExtra()?.toList() ?: screeningContents
        val moviesView = findViewById<RecyclerView>(R.id.lv_screening_movies)
        val movieAdapter = ScreeningAdapter(screenings, presenter::selectScreening)
        moviesView.adapter = movieAdapter
    }

    @Suppress("DEPRECATION")
    private fun Intent.getScreeningsExtra(): Array<Screening>? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(EXTRA_SCREENINGS, Array<Screening>::class.java)

            else -> getSerializableExtra(EXTRA_SCREENINGS) as? Array<Screening>
        }

    companion object {
        private const val EXTRA_SCREENINGS = "woowacourse.movie.EXTRA_SCREENINGS"

        fun testIntent(
            context: Context,
            screenings: Array<Screening>,
        ): Intent =
            Intent(context, ScreeningActivity::class.java).putExtra(
                EXTRA_SCREENINGS,
                screenings,
            )
    }
}
