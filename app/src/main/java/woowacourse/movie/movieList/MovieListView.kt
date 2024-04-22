import android.content.Context
import android.content.Intent

interface MovieListView {
    fun getContext(): Context
    fun startActivity(intent: Intent)
    fun showToast(message: String)
}
