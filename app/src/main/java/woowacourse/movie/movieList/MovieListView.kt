import android.content.Context
import android.content.Intent
import woowacourse.movie.model.MovieDisplayData

interface MovieListView {
    fun getContext(): Context
    fun startActivity(intent: Intent)
    fun showToast(message: String)
    fun updateAdapter(displayData: List<MovieDisplayData>)
}
