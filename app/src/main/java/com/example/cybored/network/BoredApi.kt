package com.example.myapplication.network

import com.example.myapplication.network.dto.CategoriesDto
import com.example.myapplication.network.dto.CategoryDto
import com.example.myapplication.network.dto.EventsDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BoredApi {

    @GET("/categories")
    fun getCategories(): Single<Response<CategoriesDto>>


    @GET("/events")
    fun getEvents(): Single<Response<EventsDto>>


    @GET("/events")
    fun getPath(): Single<Response<EventsDto>>

    companion object Factory {
        fun create(): BoredApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://unbored.mm77707.now.sh")
                    .build()

            return retrofit.create(BoredApi::class.java);
        }
    }
}