package woowacourse.movie.activity

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presenter.PurchaseConfirmationActivityPresenter

class PurchaseConfirmationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_confirmation)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val presenter = PurchaseConfirmationActivityPresenter(intent)
        val movie = presenter.movie
        findViewById<TextView>(R.id.movie_title_confirmation).text = movie?.title.toString()
        findViewById<TextView>(R.id.purchase_movie_running_time).text =
            movie?.runningTime.toString()

        findViewById<TextView>(R.id.ticket_charge).text = PRICE.format(presenter.charge)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    companion object {
        const val PRICE = "price: %d"
    }
}
