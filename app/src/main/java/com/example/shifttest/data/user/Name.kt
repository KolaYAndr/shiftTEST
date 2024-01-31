package com.example.shifttest.data.user

class Name(
    val first: String,
    val last: String,
    val title: String
) {
    override fun toString(): String {
        return "$title $first $last"
    }
}