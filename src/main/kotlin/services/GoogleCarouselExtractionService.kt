package services

import model.Carousel
import java.io.File


class GoogleCarouselExtractionService {

    /**
     * Real case scenario: use api instead of html
     */
    fun extractCarousel(htmlFile: File): Carousel {
        return Carousel(emptyList())
    }
}