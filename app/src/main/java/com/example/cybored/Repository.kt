package com.example.myapplication

import android.util.Log
import android.widget.Toast
import com.example.myapplication.categories.CategoriesItem
import com.example.myapplication.model.CategoryModel
import com.example.myapplication.model.EventModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

class Repository {
    companion object {
        //TODO refactor
//        too tired to do it in normal way. Sorry, MeFromTomorrow
        var eventsSubject: BehaviorSubject<List<EventModel>> = BehaviorSubject.create()
        var categoriesSubject: BehaviorSubject<List<CategoryModel>> = BehaviorSubject.create()

        fun downloadCategories(activity: MainActivity) {
            MainActivity.networkManager.getCategories()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ categories ->
                        this.categoriesSubject.onNext(categories)
                    }, {
                        Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show();
                        Thread.sleep(500)
                        Repository.downloadCategories(activity)
                    })
        }

        fun downloadEvents(activity: MainActivity) {
            MainActivity.networkManager.getEvents()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ events ->
                        this.eventsSubject.onNext(events)
                    }, {
                        Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show();
                        Thread.sleep(500)
                        Repository.downloadEvents(activity)
                    })
        }

        fun getPath(id: String, coords: Array<Double>) {
            MainActivity.networkManager.getEvents()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ events ->
                        this.eventsSubject.onNext(events)
                    }, {

                    })
        }
    }
}