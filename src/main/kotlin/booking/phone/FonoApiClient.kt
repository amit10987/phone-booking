package booking.phone

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class FonoApiClient(@Autowired val restTemplate: RestTemplate) {

	fun getPhoneInfo(token: String, brandName: String, modelName: String): FoneInfo {
		return try {
			getPhoneInfoCache(token, brandName, modelName)
		} catch (ex: Exception) {
			FoneInfo()
		}
	}

	@Cacheable(value = ["phoneInfo"], key = "{#token, #brandName, #modelName}")
	fun getPhoneInfoCache(token: String, brandName: String, modelName: String) : FoneInfo {
		return restTemplate.getForObject("https://fonoapi.freshpixl.com/v1/getdevice", FoneInfo::class.java) ?: throw Exception()
	}
}

data class FoneInfo(
	val technology: String = "NA",
	val bands2g: String = "NA",
	val bands3g: String = "NA",
	val bands4g: String = "NA"
)
