package booking.phone

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/phones")
class PhoneBookingController(@Autowired val phoneBookingService: PhoneBookingService) {

	@ResponseBody
	@GetMapping
	fun getAllPhone(): ResponseEntity<List<PhoneView>> {
		return ResponseEntity(phoneBookingService.getAllPhones(), HttpStatus.OK)
	}

	@ResponseBody
	@PutMapping("{id}/bookThePhone")
	fun bookThePhone(
		@PathVariable id: Long,
		@RequestBody phoneBooingRequest: PhoneBookingRequest
	): ResponseEntity<PhoneView> {
		return ResponseEntity(phoneBookingService.bookThePhone(id, phoneBooingRequest.name), HttpStatus.ACCEPTED)
	}

	@ResponseBody
	@PutMapping("{id}/returnThePhone")
	fun returnThePhone(@PathVariable id: Long): ResponseEntity<PhoneView> {
		return ResponseEntity(phoneBookingService.returnThePhone(id), HttpStatus.ACCEPTED)
	}

	@ExceptionHandler(PhoneIsAlreadyBookedException::class)
	fun handlePhoneIsAlreadyBookedException(ex: PhoneIsAlreadyBookedException) : ResponseEntity<String> {
		return ResponseEntity("phone with id: ${ex.id} is already booked by ${ex.bookedBy}",  HttpStatus.NOT_FOUND)
	}

	@ExceptionHandler(NoSuchElementException::class)
	fun handleNoSuchElementException(ex: NoSuchElementException) : ResponseEntity<String> {
		return ResponseEntity("not found",  HttpStatus.NOT_FOUND)
	}
}


