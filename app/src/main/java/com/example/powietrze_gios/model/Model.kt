package com.example.powietrze_gios.model

import com.google.gson.annotations.SerializedName

object Model {
        data class StationName(@SerializedName("stationName") val name: String)
        data class Air(var name: String)
}