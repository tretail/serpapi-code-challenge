package model

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Worth noting:
 * - immutable data thanks to keyword `val`
 * - Question mark means type is nullable
 */
data class Artwork(
    val name: String,
    @JsonInclude(JsonInclude.Include.NON_NULL) // FIXME: null serialization not consistent between extensions and image
    val extensions: List<String>?,
    val link: String, // TODO: use proper type instead of String
    val image: String? // TODO: use proper type instead of String

)

/**
 * Worth noting: in Kotlin, the List is also immutable (method `add` unavailable, you got to make a copy of it)
 */
data class Carousel(
    val artworks: List<Artwork>
)
