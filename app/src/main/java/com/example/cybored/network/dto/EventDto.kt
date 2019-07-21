package com.example.myapplication.network.dto

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class EventDto (
        @SerializedName("title")
        val title: String?,

        @SerializedName("_id")
        val id: String,

        @SerializedName("category")
        val categoryId: String?,

        @SerializedName("description")
        val description: String?,

        val opensAt: Long ?= -1,

        val closesAt: Long ?= -1,

        @SerializedName("link")
        val website: String ?= "",

        @SerializedName("phone")
        val phone: String ?= "",

        val pricing: String ?= "",

        @SerializedName("contactInfo")
        val contactInfo: String,

        @SerializedName("coords")
        val coords: List<Double>,

        @SerializedName("video_preview")
        val image: String,

        @SerializedName("video")
        val video: String
)