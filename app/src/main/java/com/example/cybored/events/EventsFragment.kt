package com.example.myapplication.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.android.synthetic.main.fragment_events.view.*


class EventsFragment: Fragment() {

    lateinit var categoryId: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentManager?.let {
            viewpager.adapter = PagerAdapter(it, categoryId)
        }

        tab_layout.setupWithViewPager(viewpager)
    }

    fun withCategory(categoryId: String):EventsFragment {
        this.categoryId = categoryId
        return this
    }

    class PagerAdapter(fm: FragmentManager, val categoryId:String) : FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            val fragment = EventListFragment()
            return when (position) {
                0 -> EventMapFragment().withCategory(categoryId)
                1 -> EventListFragment().withCategory(categoryId)
                else -> EventListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Map"
                1 -> "List"
                else -> ""
            }
        }

    }
}