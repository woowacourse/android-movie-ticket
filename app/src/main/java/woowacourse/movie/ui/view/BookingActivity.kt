package woowacourse.movie.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    private var ticketQuantity: Int = 0
    private lateinit var date: LocalDate
    private lateinit var time: LocalTime
    private lateinit var quantityView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        displayMovieInfo()
        bindTicketQuantityButtonListeners()
        bindSelectButtonListener()
    }

    private fun setupScreen() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun displayMovieInfo() {
        val movie = intent.intentSerializable("Movie", Movie::class.java)
        val poster = findViewById<ImageView>(R.id.poster)
        poster.setImageResource(movie.posterRes)

        val title = findViewById<TextView>(R.id.title)
        title.text = movie.title

        val screeningDate = findViewById<TextView>(R.id.screeningDate)
        screeningDate.text = getString(R.string.date_text, movie.startScreeningDate, movie.endScreeningDate)

        val runningTime = findViewById<TextView>(R.id.runningTime)
        runningTime.text = getString(R.string.runningTime_text, movie.runningTime.toString())
    }

    private fun bindTicketQuantityButtonListeners() {
        quantityView = findViewById(R.id.quantity)
        val increaseBtn = findViewById<Button>(R.id.increase)
        increaseBtn.setOnClickListener {
            ticketQuantity++
            updateQuantity()
        }
        val decreaseBtn = findViewById<Button>(R.id.decrease)
        decreaseBtn.setOnClickListener {
            if (ticketQuantity > 0) {
                ticketQuantity--
                updateQuantity()
            }
        }
    }

    private fun bindSelectButtonListener() {
        val selectBtn = findViewById<Button>(R.id.select)
        selectBtn.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
//        AlertDialog.Builder(this)
//            .setTitle(getString(R.string.dialog_title))
//            .setPositiveButton(getString(R.string.complete)) {  }
//            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
//            .setCancelable(false)
//            .show()
    }

    private fun updateQuantity()  {
        quantityView.text = ticketQuantity.toString()
    }
}