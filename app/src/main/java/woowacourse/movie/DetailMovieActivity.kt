package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DetailMovieActivity : AppCompatActivity() {
    // TODO 2) DetailMovieActivity -> MovieReservationActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val complete = findViewById<Button>(R.id.btn_detail_complete)
        complete.setOnClickListener {
            startActivity(Intent(this, ReservationResultActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
