package woowacourse.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.ui.screen.repository.DummyScreens

class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by lazy { DetailViewModel(DummyScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(PUT_EXTRA_KEY_ID, DEFAULT_ID)
        val state = detailViewModel.findById(id)
        handleState(state)
    }

    private fun handleState(state: DetailEventState) {
        when (state) {
            is DetailEventState.Success -> {
                when (state) {
                    is DetailEventState.Success.ScreenLoading -> bindScreen(state)
                }
            }

            is DetailEventState.Failure -> {
                when (state) {
                    is DetailEventState.Failure.GoToBack -> {
                        Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    is DetailEventState.Failure.UnexpectedFinish -> {
                        Snackbar.make(findViewById(android.R.id.content), state.message, Snackbar.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    private fun bindScreen(state: DetailEventState.Success.ScreenLoading) {
        val title = findViewById<TextView>(R.id.tv_title)
        val date = findViewById<TextView>(R.id.tv_screen_date)
        val runningTime = findViewById<TextView>(R.id.tv_screen_running_time)
        val description = findViewById<TextView>(R.id.tv_description)
        val poster = findViewById<ImageView>(R.id.iv_poster)

        with(state.screen) {
            title.text = movie.title
            date.text = this.date
            runningTime.text = movie.runningTime.toString()
            description.text = movie.description
            poster.setImageResource(movie.imageSrc)
        }
    }

    companion object {
        private const val DEFAULT_ID = -1
        private const val PUT_EXTRA_KEY_ID = "screenId"

        fun startActivity(
            context: Context,
            id: Int,
        ) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_ID, id)
            context.startActivity(intent)
        }
    }
}
