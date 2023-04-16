package woowacourse.movie.presentation.view.seat_pick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.databinding.ActivitySeatPickerBinding

class SeatPickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeatPickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
