package com.example.myapplication.model

import com.example.myapplication.network.dto.EventDto

class EventModel(val dto: EventDto){
    val title = dto.title
    val id = dto.id
    val categoryId = dto.categoryId
    val point = dto.coords
    val desription = dto.description
    val imageUrl=dto.image
    val videoUrl=dto.video
}