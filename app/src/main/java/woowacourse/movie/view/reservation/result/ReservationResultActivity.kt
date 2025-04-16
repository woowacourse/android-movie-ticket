package woowacourse.movie.view.reservation.result

import android.content.Intent
import android.view.MenuItem
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.movies.MoviesActivity

class ReservationResultActivity : BaseActivity() {
    override fun initView() {
        displayReservationResult()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> startActivity(Intent(this, MoviesActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun displayReservationResult() {
        val title = intent.getStringExtra(getString(R.string.bundle_key_movie_title))
        val date = intent.getStringExtra(getString(R.string.bundle_key_movie_date))

        val tvCancelDescription = findViewById<TextView>(R.id.tv_cancel_description)
        tvCancelDescription.text =
            getString(R.string.reservation_result_cancel_time_description, CANCELLATION_TIME)

        val tvMovieTitle = findViewById<TextView>(R.id.tv_movie_title)
        tvMovieTitle.text = title

        val tvMovieDate = findViewById<TextView>(R.id.tv_movie_date)
        tvMovieDate.text = date
    }

    companion object {
        private const val CANCELLATION_TIME = 15
    }
}
