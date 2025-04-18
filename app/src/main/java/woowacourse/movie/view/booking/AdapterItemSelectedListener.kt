import android.view.View
import android.widget.AdapterView

class AdapterItemSelectedListener(
    private val action: (Int) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        action(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}
