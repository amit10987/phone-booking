package booking.phone

import booking.phone.model.Phone
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class PhoneBookingService(
	@Autowired private val phoneRepository: PhoneRepository,
	@Autowired private val fonoApiClient: FonoApiClient
) {

	fun getAllPhones(): List<PhoneView> {
		return runBlocking {
			phoneRepository.findAll().map {
				async {	getPhoneView(it) }
			}.toList().awaitAll()
		}
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	fun bookThePhone(phoneId: Long, bookedBy: String): PhoneView {
		val phone = phoneRepository.findById(phoneId).orElseThrow()
		val phoneBooked = phone.bookThePhone(bookedBy)
		return getPhoneView(phoneBooked)
	}

	@Transactional
	fun returnThePhone(phoneId: Long): PhoneView {
		val phone = phoneRepository.findById(phoneId).orElseThrow()
		val phoneReturned = phone.returnThePhone()
		return getPhoneView(phoneReturned)
	}

	private fun getPhoneView(phone: Phone) : PhoneView {
		val nameSplit = phone.name.split(" ")
		val brandName = nameSplit[0]
		val modelName = nameSplit.drop(1).joinToString(" ")
		return PhoneView(
			phone.name,
			phone.isAvailable(),
			phone.getBookedBy(),
			phone.getBookedOn(),
			fonoApiClient.getPhoneInfo("aXedj56yuiFgjnmnUi", brandName, modelName)
		)
	}
}