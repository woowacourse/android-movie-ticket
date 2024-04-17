package woowacourse.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.ui.reservation.ReservationActivity
import woowacourse.movie.ui.screen.repository.DummyScreens

class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by lazy { DetailViewModel(DummyScreens()) }
    private lateinit var ticketCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initBinding()
        initClickListener()
    }

    private fun initBinding() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(PUT_EXTRA_KEY_ID, DEFAULT_ID)
        handleState(detailViewModel.loadScreen(id))
    }

    private fun initClickListener() {
        val plusBtn = findViewById<Button>(R.id.btn_plus)
        plusBtn.setOnClickListener {
            handleState(detailViewModel.plusTicket())
        }

        val minusBtn = findViewById<Button>(R.id.btn_minus)
        minusBtn.setOnClickListener {
            handleState(detailViewModel.minusTicket())
        }

        val reserveDone = findViewById<Button>(R.id.btn_reserve_done)
        reserveDone.setOnClickListener {
            handleState(detailViewModel.clickReservationDone())
        }
    }

    private fun handleState(state: DetailEventState) {
        when (state) {
            is DetailEventState.Success -> {
                when (state) {
                    is DetailEventState.Success.ScreenLoading -> bindScreen(state)
                    is DetailEventState.Success.UpdateTicket -> ticketCount.text = state.count.toString()
                    is DetailEventState.Success.NavigateToReservation -> ReservationActivity.startActivity(this)
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

                    is DetailEventState.Failure.ShowToastMessage -> {
                        Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
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
        ticketCount = findViewById(R.id.tv_count)

        with(state.screen) {
            title.text = movie.title
            date.text = this.date
            runningTime.text = movie.runningTime.toString()
            description.text = movie.description
            poster.setImageResource(movie.imageSrc)
            ticketCount.text = 1.toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
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
