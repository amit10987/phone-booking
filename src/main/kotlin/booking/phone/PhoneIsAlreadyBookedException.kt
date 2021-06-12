package booking.phone

class PhoneIsAlreadyBookedException(var id: Long ?, var bookedBy: String ?) : RuntimeException()