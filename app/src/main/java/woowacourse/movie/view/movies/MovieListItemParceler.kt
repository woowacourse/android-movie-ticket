package woowacourse.movie.view.movies

import android.os.Build
import android.os.Parcel
import kotlinx.parcelize.Parceler
import woowacourse.movie.domain.model.Advertisement
import woowacourse.movie.domain.model.Movie

object MovieListItemParceler : Parceler<MovieListItem> {
    private const val TYPE_MOVIE = 0
    private const val TYPE_AD = 1

    override fun create(parcel: Parcel): MovieListItem =
        when (parcel.readInt()) {
            TYPE_MOVIE -> {
                val movie =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        parcel.readParcelable(Movie::class.java.classLoader, Movie::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        parcel.readParcelable(Movie::class.java.classLoader)
                    }
                movie ?: throw IllegalArgumentException()
                MovieListItem.MovieItem(movie)
            }

            TYPE_AD -> {
                val ad =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        parcel.readParcelable(
                            Advertisement::class.java.classLoader,
                            Advertisement::class.java,
                        )
                    } else {
                        @Suppress("DEPRECATION")
                        parcel.readParcelable(Advertisement::class.java.classLoader)
                    }
                ad ?: throw IllegalArgumentException()
                MovieListItem.AdItem(ad)
            }

            else -> throw IllegalArgumentException()
        }

    override fun MovieListItem.write(
        parcel: Parcel,
        flags: Int,
    ) {
        when (this) {
            is MovieListItem.MovieItem -> {
                parcel.writeInt(TYPE_MOVIE)
                parcel.writeParcelable(movie, flags)
            }

            is MovieListItem.AdItem -> {
                parcel.writeInt(TYPE_AD)
                parcel.writeParcelable(ad, flags)
            }
        }
    }
}
