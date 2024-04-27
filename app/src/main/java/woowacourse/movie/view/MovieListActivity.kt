package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.adapter.ScreeningRecyclerViewAdapter
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.presenter.MovieListPresenter
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListActivity :
    AppCompatActivity(),
    MovieListContract.View,
    ScreeningRecyclerViewAdapter.TicketingButtonClickListener {
    private lateinit var presenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        presenter = MovieListPresenter(this)
    }

    override fun initializeScreeningList(screenings: List<Screening>) {
        val movies = findViewById<RecyclerView>(R.id.rv_movies)
        movies.adapter = ScreeningRecyclerViewAdapter(screenings, this)
        movies.layoutManager = LinearLayoutManager(this)
    }

    override fun navigateToTicketing(screeningId: Long) {
        Intent(this, TicketingActivity::class.java).apply {
            putExtra(EXTRA_SCREENING_ID, screeningId)
            startActivity(this)
        }
    }

    override fun onTicketingButtonClick(screeningId: Long) {
        presenter.startReservation(screeningId)
    }

    companion object {
        const val EXTRA_SCREENING_ID = "screening_id"
    }
}
