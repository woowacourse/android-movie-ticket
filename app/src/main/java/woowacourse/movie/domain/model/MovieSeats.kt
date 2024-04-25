package woowacourse.movie.domain.model

class MovieSeats(
    private val ticketCount : Int,
) {
    private val userSeats = arrayListOf<MovieSeat>()

    fun getSeatSelectType(newSeat: MovieSeat): SeatSelectType{
        return if (isExitsSeat(newSeat)) {
            SeatSelectType.REMOVE
        } else{
            if (isValidTicketCounter()){
                SeatSelectType.ADD
            }else {
                SeatSelectType.PREVENT
            }
        }
    }

    fun addSeat(newSeat: MovieSeat){
        userSeats.add(newSeat)
    }

    fun deleteSeat(movieSeat: MovieSeat){
        userSeats.remove(movieSeat)
    }

    private fun isValidTicketCounter(): Boolean{
        return ticketCount > userSeats.size
    }

    private fun isExitsSeat(newSeat: MovieSeat): Boolean {
        return userSeats.contains(newSeat)
    }
}
