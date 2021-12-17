package com.github.welblade.incartaz.data.model

import com.google.gson.annotations.SerializedName

data class ProductionCompany (
    val id: Long,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)