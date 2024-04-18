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
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.reservation.ReservationActivity

class DetailActivity : AppCompatActivity(), DetailContract.View {
    private val presenter: DetailContract.Presenter by lazy { DetailPresenter(this, DummyScreens(), DummyReservation) }

    private lateinit var title: TextView
    private lateinit var date: TextView
    private lateinit var runningTime: TextView
    private lateinit var description: TextView
    private lateinit var poster: ImageView
    private lateinit var ticketCount: TextView
    private lateinit var plusBtn: Button
    private lateinit var minusBtn: Button
    private lateinit var reserveDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initBinding()
        initView()
        initClickListener()
    }

    private fun initBinding() {
        title = findViewById(R.id.tv_title)
        date = findViewById(R.id.tv_screen_date)
        runningTime = findViewById(R.id.tv_screen_running_time)
        description = findViewById(R.id.tv_description)
        poster = findViewById(R.id.iv_poster)
        ticketCount = findViewById(R.id.tv_count)
        plusBtn = findViewById(R.id.btn_plus)
        minusBtn = findViewById(R.id.btn_minus)
        reserveDone = findViewById(R.id.btn_reserve_done)
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(PUT_EXTRA_KEY_ID, DEFAULT_ID)
        presenter.loadScreen(id)
    }

    private fun initClickListener() {
        plusBtn.setOnClickListener {
            presenter.plusTicket()
        }

        minusBtn.setOnClickListener {
            presenter.minusTicket()
        }

        reserveDone.setOnClickListener {
            presenter.reserve()
        }
    }

    override fun showScreen(screen: Screen) {
        with(screen) {
            title.text = movie.title
            this@DetailActivity.date.text = date
            runningTime.text = movie.runningTime.toString()
            description.text = movie.description
            poster.setImageResource(movie.imageSrc)
        }
    }

    override fun showTicket(count: Int) {
        ticketCount.text = count.toString()
    }

    override fun navigateToReservation(id: Int) {
        ReservationActivity.startActivity(this, id)
        finish()
    }

    override fun goToBack(message: String) {
        showToastMessage(message)
        finish()
    }

    override fun unexpectedFinish(message: String) {
        showSnackBar(message)
        finish()
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
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
