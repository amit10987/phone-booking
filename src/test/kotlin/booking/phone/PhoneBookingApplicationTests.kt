package booking.phone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.lang.StringBuilder

@SpringBootTest
class PhoneBookingApplicationTests {

	@Autowired
	lateinit var phoneBookingService: PhoneBookingService

	@Test
	fun testWhenPhoneIsAvailableShouldBookSuccessfully() {
		val phoneBooked = phoneBookingService.bookThePhone(1, "john")
		assertThat(phoneBooked.bookedBy.equals("john")).isTrue
		assertThat(phoneBooked.isAvailable).isFalse
	}

	@Test
	fun testWhenPhoneIsAlreadyBookedThenThrowAlreadyBookedException() {
		phoneBookingService.bookThePhone(2, "john")
		assertThrows<PhoneIsAlreadyBookedException> { phoneBookingService.bookThePhone(2, "test") }
	}

	@Test
	fun testWhenPhoneIsReturnedThenShouldAvailable(){
		val phoneBooked = phoneBookingService.bookThePhone(3, "test")
		assertThat(phoneBooked.isAvailable).isFalse
		val phoneReturned = phoneBookingService.returnThePhone(3)
		assertThat(phoneReturned.isAvailable).isTrue
	}
}
