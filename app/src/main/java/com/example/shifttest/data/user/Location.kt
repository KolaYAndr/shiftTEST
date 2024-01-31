package com.example.shifttest.data.user

class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: Int,
    val state: String,
    val street: Street,
    val timezone: Timezone
) {
    override fun toString(): String {
        return "$country. $state, $city, ${street.name} ${street.number}"
    }
}