import com.fasterxml.jackson.module.kotlin.readValue
import model.Carousel
import org.junit.jupiter.api.Test
import utils.Serializer
import java.io.File
import kotlin.test.assertEquals

/**
 * Checking that serialization and deserialization gives the same output to avoid any unattended result
 */
class CarouselIdempotenceTest {

    @Test
    fun `serialization and deserialization of Carousel should be idempotent`() {
        // Given
        val initialJson = File("src/test/resources/files/expected-array.json")
        val carousel: Carousel = Serializer.json.readValue(initialJson)

        // When
        val serializedCarousel = Serializer.json.writerWithDefaultPrettyPrinter().writeValueAsString(carousel)

        // Then
        assertEquals(initialJson.readText(), serializedCarousel)
    }
}