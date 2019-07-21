package com.example.myapplication.categories

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.Router
import com.example.myapplication.model.CategoryModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class CategoriesItem : AbstractItem<CategoriesItem.ViewHolder>() {
    var model: CategoryModel? = null

    override val type: Int
        get() = R.id.category_item_id

    override val layoutRes: Int
        get() = R.layout.item_category

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<CategoriesItem>(view) {
        var title: TextView = view.findViewById(R.id.category_item_title)
        var container: FrameLayout = view.findViewById(R.id.container)

        override fun bindView(item: CategoriesItem, payloads: MutableList<Any>) {
            title.text = item.model?.title
            item.model?.let{ model ->
                container.setOnClickListener{Router.openEvents(model)}
            }
        }

        override fun unbindView(item: CategoriesItem) {
            title.text = null
        }
    }
}