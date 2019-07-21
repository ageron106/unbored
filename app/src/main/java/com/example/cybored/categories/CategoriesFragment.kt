package com.example.myapplication.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.Repository
import com.example.myapplication.Router
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_categories.*
import java.util.*

class CategoriesFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemAdapter = ItemAdapter<CategoriesItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        category_recycler.layoutManager = LinearLayoutManager(context)
        category_recycler.setAdapter(fastAdapter)
        Router.activity?.let {
            it.supportActionBar?.title = "UnBored"
        }
        Repository.categoriesSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ categories->
                    Log.d("", "")
                    val list = categories?.map {
                        val item = CategoriesItem()
                        item.model = it
                        item
                    }?:ArrayList()
                        itemAdapter.add(list)
                }, {
                    Log.d("", "")
                })
                //TODO



    }

}