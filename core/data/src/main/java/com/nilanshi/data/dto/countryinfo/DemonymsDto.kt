package com.nilanshi.data.dto.countryinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DemonymsDto(
    @SerializedName("eng")
    @Expose
    val eng: DemonymEnglishDto?,
)
