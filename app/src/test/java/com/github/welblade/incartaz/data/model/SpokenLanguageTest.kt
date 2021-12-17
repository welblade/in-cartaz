package com.github.welblade.incartaz.data.model

import org.junit.Assert.*

import org.junit.Test

class SpokenLanguageTest {
    val spokenLanguage = SpokenLanguage("en","English")
    @Test
    fun getIso639_1() {
        assertEquals("en", spokenLanguage.iso639_1)
    }

    @Test
    fun getName() {
        assertEquals("English", spokenLanguage.name)
    }
}