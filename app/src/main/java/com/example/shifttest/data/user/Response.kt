package com.example.shifttest.data.user

import com.google.gson.annotations.SerializedName

data class Response(
    val info: Info,
    @SerializedName("results")
    val users: List<User>
)