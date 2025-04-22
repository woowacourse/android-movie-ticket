package woowacourse.movie.ui.adapter

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import java.time.LocalDate

class ScreeningDateSpinnerAdapter(
    context: Context,
    items: List<LocalDate>,
) : ArrayAdapter<LocalDate>(context, R.layout.simple_spinner_item, items) {
    init {
        setDropDownViewResource(R.layout.simple_spinner_item)
    }
}
