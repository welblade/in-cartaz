package com.github.welblade.incartaz.data.model

data class ProductionCompany (
    val id: Long,
    val logoPath: String? = null,
    val name: String,
    val originCountry: String
)