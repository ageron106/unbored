package com.example.myapplication.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.Repository
import com.example.myapplication.categories.CategoriesItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_event_list.*
import java.util.ArrayList


class EventListFragment: Fragment() {

    private lateinit var categoryId: String

    fun withCategory(categoryId: String):EventListFragment {
        this.categoryId = categoryId
        return this
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val itemAdapter = ItemAdapter<EventItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        event_recycler.layoutManager = LinearLayoutManager(context)
        event_recycler.setAdapter(fastAdapter)
        Repository.eventsSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ events->
                    val categoryEvents = events.filter { it.categoryId == categoryId }

                    val list = categoryEvents?.map {
                        val item = EventItem()
                        item.model = it
                        item
                    }?: ArrayList()
                    itemAdapter.add(list)
                }, {
                    Log.d("", "")
                })
        //TODO
    }
}