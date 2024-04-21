package woowacourse.movie.purchaseConfirmation

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

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
        findViewById<TextView>(R.id.ticket_charge).text =
            getString(R.string.price_format, presenter.calculate())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
