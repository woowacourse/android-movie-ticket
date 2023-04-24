package woowacourse.movie.ui.movielistactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUIModel

//class MovieListAdapter(private val movies: List<MovieUIModel>) : BaseAdapter() {
//    private lateinit var inflater: LayoutInflater
//    private var viewHolderPool: MutableMap<View, MovieViewHolder> = mutableMapOf()
//
//    override fun getCount(): Int = movies.size
//
//    override fun getItem(position: Int): Any = movies[position]
//
//    override fun getItemId(position: Int): Long = position.toLong()
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        var presentViewHolder: MovieViewHolder
//        var itemLayout: View? = convertView
//
//        if (convertView == null) {
//            if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent?.context)
//            itemLayout = inflater.inflate(R.layout.movie_list_item, null)
//
//            presentViewHolder = initMovieViewHolder(itemLayout)
//            viewHolderPool[itemLayout] = presentViewHolder
//        } else {
//            presentViewHolder = viewHolderPool[convertView] ?: throw IllegalStateException(
//                VIEW_HOLDER_POOL_VALUE_NULL_ERROR
//            )
//        }
//
//        presentViewHolder.setData(movies[position])
//
//        return itemLayout ?: throw IllegalStateException(NULL_ITEM_LAYOUT_ERROR)
//    }
//
//    private fun initMovieViewHolder(itemLayout: View): MovieViewHolder =
//        MovieViewHolder(itemLayout)
//
//    companion object {
//        private const val NULL_ITEM_LAYOUT_ERROR = "itemLayout이 null 값으로 반환되었습니다."
//        private const val VIEW_HOLDER_POOL_VALUE_NULL_ERROR = "리스트 뷰의 아이템의 뷰홀더가 null 입니다."
//    }
//}
