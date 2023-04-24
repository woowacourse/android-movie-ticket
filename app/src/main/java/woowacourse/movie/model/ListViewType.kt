package woowacourse.movie.model

enum class ListViewType {
    AD_VIEWTYPE, NORMAL_VIEWTYPE;

    companion object {
        fun getViewType(adInterval: Int, position: Int): ListViewType {
            if ((position + 1) % (adInterval + 1) == 0) return AD_VIEWTYPE
            return NORMAL_VIEWTYPE
        }
    }
}
