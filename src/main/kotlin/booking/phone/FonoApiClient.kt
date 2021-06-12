package booking.phone

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class FonoApiClient(@Autowired val restTemplate: RestTemplate) {

	@Cacheable(value = ["phoneInfo"], key = "{#token, #brandName, #modelName}")
	fun getPhoneInfo(token: String, brandName: String, modelName: String): FoneInfo {
		return try {
			restTemplate.getForObject("https://fonoapi.freshpixl.com/v1/getdevice", FoneInfo::class.java) ?: FoneInfo()
		} catch (ex: Exception) {
			FoneInfo()
		}
	}
}

data class FoneInfo(
	val technology: String = "NA",
	val bands2g: String = "NA",
	val bands3g: String = "NA",
	val bands4g: String = "NA"
)
