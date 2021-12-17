package com.github.welblade.incartaz.data.model

import org.junit.Assert.*

import org.junit.Test

class ProductionCompanyTest {
    val productionCompany = ProductionCompany(
        508,
        "/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png",
        "Regency Enterprises",
        "US"
    )
    @Test
    fun getId() {
        assertEquals(508, productionCompany.id)
    }

    @Test
    fun getLogoPath() {
        assertEquals("/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png", productionCompany.logoPath)
    }

    @Test
    fun getName() {
        assertEquals("Regency Enterprises", productionCompany.name)
    }

    @Test
    fun getOriginCountry() {
        assertEquals("US", productionCompany.originCountry)
    }
}