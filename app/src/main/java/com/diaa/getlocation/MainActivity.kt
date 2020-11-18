package com.diaa.getlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.diaa.getlocation.databinding.ActivityMainBinding
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Basemap
import com.esri.arcgisruntime.mapping.view.LocationDisplay

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initMap()
    }

    private fun initMap() {
        try {
            binding.mapView.locationDisplay.startAsync()
            binding.mapView.locationDisplay.autoPanMode = LocationDisplay.AutoPanMode.NAVIGATION
            binding.mapView.locationDisplay.addLocationChangedListener {

                binding.xValue.text = it.source.location.position.x.toString()
                binding.yValue.text = it.source.location.position.y.toString()
            }
            ArcGISMap(
                Basemap.Type.IMAGERY_WITH_LABELS_VECTOR,
                binding.mapView.locationDisplay?.mapLocation?.x!!,
                binding.mapView.locationDisplay?.mapLocation?.y!!,
                1
            )
                .let { map ->
                    // set map to be displayed in map view
                    binding.mapView.map = map

                }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}