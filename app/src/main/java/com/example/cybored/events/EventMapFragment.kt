package com.example.myapplication.events

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.Repository
import com.example.myapplication.Router
import com.example.myapplication.model.EventModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_map_list.*


class EventMapFragment: Fragment(), OnMapReadyCallback, LocationListener {

    val markers = HashMap<Marker, String>()

    override fun onLocationChanged(location: Location?) {
        location ?: return
        val coordinate = LatLng(location.latitude, location.longitude)
        val location = CameraUpdateFactory.newLatLngZoom(coordinate, 15f)
        map?.animateCamera(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    private lateinit var categoryId: String
    private var map: GoogleMap? = null


    fun withCategory(categoryId: String):EventMapFragment {
        this.categoryId = categoryId
        return this
    }

    var categoryEvents: List<EventModel> = ArrayList()

    override fun onMapReady(newMap: GoogleMap?) {
        this.map = newMap
        Repository.eventsSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { events ->
                    categoryEvents = events.filter { it.categoryId == categoryId }
                    categoryEvents.forEach {model ->
                        var point = LatLng(model.point[0], model.point[1])
                        val markerOptions = MarkerOptions()
                                .position(point)
                                .title(model.title)

                        map?.addMarker(markerOptions)?.let {
                            markers.put(it, model.id)
                        }

                    }
                }
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map?.setMyLocationEnabled(true)
            map?.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker ->
                val id = markers.get(marker)
                showEvent(id)
                id==null
            })
            val locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
            locationManager?.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null)
        }
    }

    private fun showEvent(id: String?) {
        categoryEvents.forEach {
            if (it.id == id) {
                val itemAdapter = ItemAdapter<EventItem>()
                val fastAdapter = FastAdapter.with(itemAdapter)
                map_recycler.layoutManager = LinearLayoutManager(context)
                map_recycler.setAdapter(fastAdapter)
                val item = EventItem()
                item.model = it
                itemAdapter.add(item)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_map_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val map = SupportMapFragment()

        childFragmentManager.beginTransaction()
                .replace(com.example.myapplication.R.id.map_container, map)
                .commitNow()

        map.getMapAsync(this);

    }
}