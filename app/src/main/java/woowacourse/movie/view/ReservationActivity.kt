package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.presenter.ReservationPresenter

class ReservationActivity : AppCompatActivity(), ReservationContract.View {
    private val reservationPresenter = ReservationPresenter(this)
    private lateinit var addButton: Button
    private lateinit var subButton: Button
    private lateinit var countTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var screenDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setUpView()
        setUpCount()
        bindReservationButton()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt("count")
            countTextView.text = count.toString()
        }
    }

    private fun setUpView() {
        initImage()
        initTitle()
        initScreenDate()
        initRunningTime()
        initDescription()
    }

    private fun initImage() {
        val imageView: ImageView = findViewById(R.id.reservation_imageview)
        imageView.setImageResource(intent.getIntExtra("image", 0))
    }

    private fun initTitle() {
        titleTextView = findViewById(R.id.reservation_title_textview)
        titleTextView.text = intent.getStringExtra("title")
    }

    private fun initScreenDate() {
        screenDateTextView = findViewById(R.id.reservation_screen_date_textview)
        screenDateTextView.text = intent.getStringExtra("screenDate")
    }

    private fun initRunningTime() {
        val runningTimeTextView: TextView = findViewById(R.id.reservation_running_time_textview)
        runningTimeTextView.text = intent.getStringExtra("runningTime")
    }

    override fun updateTicketCount() {
        countTextView.text = reservationPresenter.ticket.count.toString()
    }

    private fun initDescription() {
        val description: TextView = findViewById(R.id.reservation_description)
        description.text = intent.getStringExtra("description")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun setUpCount() {
        countTextView = findViewById(R.id.reservation_count_textview)
        countTextView.text = reservationPresenter.ticket.count.toString()
        bindCountButtons()
    }

    private fun bindCountButtons() {
        addButton = findViewById(R.id.add_button)
        subButton = findViewById(R.id.sub_button)

        addButton.setOnClickListener {
            reservationPresenter.addTicketCount()
        }
        subButton.setOnClickListener {
            reservationPresenter.subTicketCount()
        }
    }

    private fun bindReservationButton() {
        val reservationButton: Button = findViewById(R.id.reservation_complete_button)

        reservationButton.setOnClickListener {
            val intent = Intent(this, ReservationResultActivity::class.java)

            intent.putExtra("title", titleTextView.text.toString())
            intent.putExtra("screenDate", screenDateTextView.text.toString())
            intent.putExtra("count", countTextView.text.toString())
            // is this right..?
            intent.putExtra("price", reservationPresenter.ticket.price())

            this.startActivity(intent)
        }
    }
}
