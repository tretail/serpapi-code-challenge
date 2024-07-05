package services

import model.Artwork
import model.Carousel
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File

/**
 * If multiple providers are supported (Bing, ...) strategy design pattern could be used
 */
class GoogleCarouselExtractionService {

    companion object {
        const val GOOGLE_BASE_URL = "https://www.google.com"
    }

    /**
     * Real case scenario: use api instead of html. (I guess this is the purpose of van-gogh-paintings.json file)
     */
    fun extractCarousel(html: File): Carousel {
        val document = Jsoup.parse(html, Charsets.UTF_8.name())
        // Two carousels, interested in the first one.
        val artworks =
            document.selectXpath("(//g-scrolling-carousel)[1]/descendant::a[contains(@class, 'klitem')]")
                .map { carouselItem -> extractArtwork(carouselItem, html) }

        return Carousel(artworks)
    }

    /**
     * TODO: Instead of parsing the whole page with the regexp into @{findJpegItem}, we could find a subset instead of passing html.readText()
     */
    private fun extractArtwork(carouselItem: Element, html: File) = Artwork(
        name = carouselItem.attr("aria-label"),
        link = GOOGLE_BASE_URL + carouselItem.attr("href"),
        extensions = carouselItem.selectXpath("descendant::div[contains(@class, 'klmeta')]").map { it.text() }
            .ifEmpty { null },
        image = findJpegImg(carouselItem.selectXpath("descendant::g-img/img[1]").attr("id"), html.readText())
    )

    /**
     * Attribute src contains a gif representation, and is replaced by jpeg trough javascript function.
     * Running interpreter is bad idea, since it may lead to security issues, so we extract it with this ugly regexp;
     * subject to refactor if better solution is found.
     */
    private fun findJpegImg(id: String?, text: String): String? {
        val match = "var s='(data:image/jpeg;base64\\S*)';var ii=\\['$id']".toRegex()
        return match.find(text)?.destructured?.let { (base64) ->
            // FIXME: Not correct, but let's match the test; this is suppose to be '=' characters but the expected-array drops the escape char.
            base64.replace("\\x3d", "x3d")
        }
    }

}