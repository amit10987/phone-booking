package booking.phone

import booking.phone.model.Phone
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.CrudRepository
import java.util.*
import org.springframework.web.client.RestTemplate
import java.time.Duration


@SpringBootApplication
@EnableCaching
class PhoneBookingApplication {

	@Bean
	fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
		return restTemplateBuilder
			.setConnectTimeout(Duration.ofSeconds(10))
			.setReadTimeout(Duration.ofSeconds(10))
			.build();
	}
}

fun main(args: Array<String>) {
	runApplication<PhoneBookingApplication>(*args)
}

interface PhoneRepository : CrudRepository<Phone, Long>

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PhoneView(
	var name: String,
	var isAvailable: Boolean,
	var bookedBy: String?,
	var bookedOn: Date?,
	var foneInfo: FoneInfo
)

data class PhoneBookingRequest(var name: String)

class PhoneIsAlreadyBookedException(var id: Long ?, var bookedBy: String ?) : RuntimeException()

