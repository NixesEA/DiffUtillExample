package com.nixesea.diffutillexample.model

data class Response(
    val id: Int,
    val name: String,
    val address: String,
    val stars: Double,
    val distance: Double,
    val suites_availability: String
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass)
            return false

        other as Response
        if (id != other.id)
            return false
        if (name != other.name)
            return false
        if (address != other.address)
            return false
        if (distance != other.distance)
            return false

        return true
    }
}