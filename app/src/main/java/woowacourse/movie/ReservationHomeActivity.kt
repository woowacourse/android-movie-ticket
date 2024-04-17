package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import domain.Movie

class ReservationHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        val movies =
            listOf(
                Movie(
                    1,
                    R.drawable.img_sorcerers_stone,
                    getString(R.string.sorcerers_stone_title),
                    getString(R.string.sorcerers_stone_screening_date),
                    getString(R.string.sorcerers_stone_running_time),
                    getString(R.string.sorcerers_stone_summary),
                ),
                Movie(
                    2,
                    R.drawable.img_secret_room,
                    getString(R.string.secret_room_title),
                    getString(R.string.secret_room_screening_date),
                    getString(R.string.secret_room_running_time),
                    getString(R.string.secret_room_summary),
                ),
                Movie(
                    3,
                    R.drawable.img_prisoner_of_azkaban,
                    getString(R.string.prisoner_of_azkaban_title),
                    getString(R.string.prisoner_of_azkaban_screening_date),
                    getString(R.string.prisoner_of_azkaban_running_time),
                    getString(R.string.prisoner_of_azkaban_summary),
                ),
            )

        val movieCatalogAdapter =
            MovieCatalogAdapter(this, movies) {
                val intent = Intent(this, ReservationDetailActivity::class.java)
                intent.putExtra("movie", it)
                startActivity(intent)
            }
        findViewById<ListView>(R.id.list_view_reservation_home).apply { adapter = movieCatalogAdapter }
    }
}
