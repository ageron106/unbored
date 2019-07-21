package com.example.myapplication.model

import com.example.myapplication.network.dto.CategoryDto
import java.util.*

class CategoryModel(val dto: CategoryDto){
    val title = dto.title
    val id = dto.id
}