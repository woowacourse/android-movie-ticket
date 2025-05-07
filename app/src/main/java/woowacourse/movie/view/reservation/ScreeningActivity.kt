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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rv_screening_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.fetchScreeningContents()
    }

    override fun setScreeningContents(screeningContents: List<ScreeningContent>) {
        initListView(screeningContents)
    }

    override fun navigateToReservationScreen(screening: Screening) {
        val intent = ScreeningDetailActivity.newIntent(this, screening)
        startActivity(intent)
    }

    private fun initListView(screeningContents: List<ScreeningContent>) {
        val screenings: List<ScreeningContent> =
            intent?.getScreeningsExtra()?.toList() ?: screeningContents
        val moviesView = findViewById<RecyclerView>(R.id.rv_screening_movies)
        val movieAdapter = ScreeningAdapter(presenter::selectScreening)
        moviesView.adapter = movieAdapter
    }

    @Suppress("DEPRECATION")
    private fun Intent.getScreeningsExtra(): Array<ScreeningContent>? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(EXTRA_SCREENING_CONTENTS, Array<ScreeningContent>::class.java)

            else -> getSerializableExtra(EXTRA_SCREENING_CONTENTS) as? Array<ScreeningContent>
        }

    companion object {
        private const val EXTRA_SCREENING_CONTENTS = "woowacourse.movie.EXTRA_SCREENING_CONTENTS"

        fun testIntent(
            context: Context,
            screeningContents: Array<ScreeningContent>,
        ): Intent =
            Intent(context, ScreeningActivity::class.java).putExtra(
                EXTRA_SCREENING_CONTENTS,
                screeningContents,
            )
    }
}
