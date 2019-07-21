package com.example.myapplication.network

import android.util.Log
import com.example.myapplication.model.CategoryModel
import com.example.myapplication.model.EventModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class NetworkManager() { // need this class to have one point for all network reqouests

    private val boredApi = BoredApi.create()

    fun getCategories(): Single<List<CategoryModel>> {
        return boredApi.getCategories()
                .subscribeOn(Schedulers.io())
                .map {
                    it.body()
                            ?.result
                            ?.map {
                                CategoryModel(it)
                            }
                            ?:ArrayList()
                }

    }

    fun getEvents(): Single<List<EventModel>> {
        return boredApi.getEvents()
                .subscribeOn(Schedulers.io())
                .map {
                    it.body()
                            ?.result
                            ?.map {
                                EventModel(it)
                            }
                            ?:ArrayList()
                }

    }

    fun getPath(id: String, coords: Array<Double>): Single<List<EventModel>> {
        return boredApi.getEvents()
                .subscribeOn(Schedulers.io())
                .map {
                    it.body()
                            ?.result
                            ?.map {
                                EventModel(it)
                            }
                            ?:ArrayList()
                }

    }

}