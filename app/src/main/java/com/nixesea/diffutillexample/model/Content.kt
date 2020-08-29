package com.nixesea.diffutillexample.model

data class Content(
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

        other as Content
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

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + stars.hashCode()
        result = 31 * result + distance.hashCode()
        result = 31 * result + suites_availability.hashCode()
        return result
    }
}