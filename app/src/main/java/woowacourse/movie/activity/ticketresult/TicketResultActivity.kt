package woowacourse.movie.activity.ticketresult

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.TicketModel
import woowacourse.movie.util.getSerializableExtraCompat

class TicketResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ticket: TicketModel? = intent.getSerializableExtraCompat(INFO_KEY)

        if (ticket == null) {
            Toast.makeText(this, DATA_LOADING_ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            finish()
            return
        }
        TicketView(findViewById(R.id.layout_ticket)).set(ticket)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        private const val DATA_LOADING_ERROR_MESSAGE = "데이터가 로딩되지 않았습니다. 다시 시도해주세요."
        const val INFO_KEY = "TICKET_INFO"
    }
}
