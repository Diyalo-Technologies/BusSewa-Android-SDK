package com.diyalotech.bussewasdk.network.dto

internal data class RouteListDTO(
    val popular_routes: List<String>,
    val routes: List<String>,
    val status: Int
)