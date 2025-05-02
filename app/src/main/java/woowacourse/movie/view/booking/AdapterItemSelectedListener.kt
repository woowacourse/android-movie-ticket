import android.view.View
import android.widget.AdapterView

class AdapterItemSelectedListener(
    private val action: (Int) -> Unit,
) : AdapterView.OnItemSelectedListener {
    private var isFirstSelection = true

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        if (isFirstSelection) {
            isFirstSelection = false
            return
        }
        action(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
