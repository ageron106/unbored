package com.example.myapplication.network.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class CategoryDto(

        @SerializedName("__v")
        val version: Int,// not used yet

        @SerializedName("title")
        val title: String,

        @SerializedName("_id")
        val id: String
)