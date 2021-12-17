package com.github.welblade.incartaz.data.model

import io.mockk.mockk
import org.junit.Assert.*

import org.junit.Test

class MovieTest {
    private var movie: Movie = createMovieObject()

    private fun createMovieObject(): Movie{
        val genres = listOf(mockk<Genre>())
        val productionCompanies = listOf(mockk<ProductionCompany>())
        val productionCountries = listOf(mockk<ProductionCountry>())
        val spokenLanguages = listOf(mockk<SpokenLanguage>())
        return Movie(
            false,
            "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
            null,
            63000000,
            genres,
            "",
            550,
            "tt0137523",
            "en",
            "Fight Club",
            "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
            0.5,
            "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
            productionCompanies,
            productionCountries,
            "1999-10-12",
            100853753,
            139,
            spokenLanguages,
            "Released",
            "How much can you know about yourself if you've never been in a fight?",
            "Fight Club",
            false,
            7.8,
            3439
        )
    }

    @Test
    fun getAdult() = assertFalse(movie.adult)

    @Test
    fun getBackdropPath() =
        assertEquals("/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg", movie.backdropPath)

    @Test
    fun getBelongsToCollection() = assertNull(movie.belongsToCollection)

    @Test
    fun getBudget() = assertEquals(63000000, movie.budget)

    @Test
    fun getGenres() = assertNotNull(movie.genres)

    @Test
    fun getHomepage() = assertEquals("", movie.homepage)

    @Test
    fun getId() = assertEquals(550, movie.id)

    @Test
    fun getImdbID() = assertEquals("tt0137523", movie.imdbID)

    @Test
    fun getOriginalLanguage() = assertEquals("en", movie.originalLanguage)

    @Test
    fun getOriginalTitle() = assertEquals("Fight Club", movie.originalTitle)

    @Test
    fun getOverview() {
        val overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."
        assertEquals(overview, movie.overview)
    }

    @Test
    fun getPopularity() = assertEquals(0.5, movie.popularity, 0.0)

    @Test
    fun getPosterPath() =
        assertEquals("/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg", movie.posterPath)

    @Test
    fun getProductionCompanies() = assertNotNull(movie.productionCompanies)

    @Test
    fun getProductionCountries() = assertNotNull(movie.productionCountries)

    @Test
    fun getReleaseDate() = assertEquals("1999-10-12", movie.releaseDate)

    @Test
    fun getRevenue() = assertEquals(100853753, movie.revenue)

    @Test
    fun getRuntime() = assertEquals(139, movie.runtime)

    @Test
    fun getSpokenLanguages() = assertNotNull(movie.spokenLanguages)

    @Test
    fun getStatus() = assertEquals("Released", movie.status)

    @Test
    fun getTagline() {
        val tagline = "How much can you know about yourself if you've never been in a fight?"
        assertEquals(tagline, movie.tagline)
    }

    @Test
    fun getTitle() = assertEquals("Fight Club", movie.title)

    @Test
    fun getVideo() = assertFalse(movie.video)

    @Test
    fun getVoteAverage()  = assertEquals(7.8, movie.voteAverage, 0.0)

    @Test
    fun getVoteCount() = assertEquals(3439, movie.voteCount)
}