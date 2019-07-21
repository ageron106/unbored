package com.example.myapplication.events

import android.view.View
import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.Router
import com.example.myapplication.model.EventModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.squareup.picasso.Picasso


open class EventItem : AbstractItem<EventItem.ViewHolder>() {
    var model: EventModel? = null

    override val type: Int
        get() = R.id.event_item_id

    override val layoutRes: Int
        get() = R.layout.item_event

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<EventItem>(view) {
        var title: TextView = view.findViewById(R.id.event_item_title)
        var description: TextView = view.findViewById(R.id.event_item_description)
        var time_by_taxi: TextView = view.findViewById(R.id.event_item_taxi_time)
        var time_by_bus: TextView = view.findViewById(R.id.event_item_bus_time)
        var busImg: ImageView = view.findViewById(R.id.event_item_bus)
        var taxiImg: ImageView = view.findViewById(R.id.event_item_taxi)
        var videoPreview: ImageView = view.findViewById(R.id.event_item_image)
        var videoPreviewPlay: ImageView = view.findViewById(R.id.event_item_play_img)
        var videoPlay: FrameLayout = view.findViewById(R.id.event_item_play)
        var openRouteButton: Button = view.findViewById(R.id.event_item_route_button)
        var openBolt: Button = view.findViewById(R.id.event_item_call_taxi)
        var container: LinearLayout = view.findViewById(R.id.container)

        override fun bindView(item: EventItem, payloads: MutableList<Any>) {
            title.text = item.model?.title
            description.text = item.model?.desription

            openBolt.setOnClickListener {
               Router.openBolt()
            }

            videoPlay.setOnClickListener {
               Router.openLink(item.model?.videoUrl)
            }

            videoPreviewPlay.setOnClickListener {
               Router.openLink(item.model?.videoUrl)
            }

            openRouteButton.setOnClickListener {_->
                item.model?.point?.let {
                    Router.openRoute(it)
                }
            }

            Picasso.get().load(item.model?.imageUrl).into(videoPreview)
        }

        override fun unbindView(item: EventItem) {
            title.text = null
            description.text = null
        }
    }
}