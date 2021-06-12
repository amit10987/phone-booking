package booking.phone.model

import booking.phone.PhoneIsAlreadyBookedException
import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Phone(val name: String) {

	@Id
	private val id: Long? = null
	private var isAvailable: Boolean = true
	private var bookedBy: String ? = null
	private var bookedOn: Timestamp? = null

	fun bookThePhone(bookedBy: String) : Phone {
		if (!isAvailable) {
			throw PhoneIsAlreadyBookedException(this.id, this.bookedBy)
		}
		this.bookedBy = bookedBy
		this.bookedOn = Timestamp(System.currentTimeMillis())
		this.isAvailable = false

		return this
	}

	fun isAvailable(): Boolean {
		return this.isAvailable
	}

	fun getBookedBy(): String ? = this.bookedBy
	fun getBookedOn() : Timestamp ? = this.bookedOn

	fun returnThePhone() : Phone{
		this.bookedBy = null
		this.isAvailable = true
		this.bookedOn = null
		return this
	}
}