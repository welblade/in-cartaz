package com.github.welblade.incartaz.data.model

import org.junit.Assert.*

import org.junit.Test

class GenreTest {
    val genre = Genre(18,"Drama")
    @Test
    fun getId() {
        assertEquals(18, genre.id)
    }

    @Test
    fun getName() {
        assertEquals("Drama", genre.name)
    }
}