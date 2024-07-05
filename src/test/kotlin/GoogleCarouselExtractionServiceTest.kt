import com.fasterxml.jackson.module.kotlin.readValue
import model.Carousel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import services.GoogleCarouselExtractionService
import utils.Serializer
import java.io.File

class GoogleCarouselExtractionServiceTest {

    private val googleCarouselExtractionService = GoogleCarouselExtractionService()

    @Test
    fun `should extract carousel from html page`() {
        // Given
        val expectedCarousel: Carousel = Serializer.json.readValue(File("src/test/resources/files/expected-array.json"))
        val html = File("src/test/resources/files/van-gogh-paintings.html")

        // When
        val extractedCarousel = googleCarouselExtractionService.extractCarousel(html)

        // Then
        assertEquals(expectedCarousel, extractedCarousel)
    }
}