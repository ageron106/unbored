package com.example.myapplication.network.dto

data class PathDto(
        val startAt:Long?,
        val endAt:Long?,
        val points:ArrayList<PointDto>
)