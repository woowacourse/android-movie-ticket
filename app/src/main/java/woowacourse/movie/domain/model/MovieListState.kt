package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import woowacourse.movie.view.movies.MovieListItem
import woowacourse.movie.view.movies.MovieListItemParceler

@Parcelize
@TypeParceler<MovieListItem, MovieListItemParceler>
data class MovieListState(
    val items: List<MovieListItem>,
) : Parcelable
