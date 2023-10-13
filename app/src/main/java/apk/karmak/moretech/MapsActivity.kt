package apk.karmak.moretech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class MapsActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    private val placemarkTapListener = MapObjectTapListener { _, point ->
        Toast.makeText(
            this,
            "Tapped the point (${point.longitude}, ${point.latitude})",
            Toast.LENGTH_SHORT
        ).show()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("API")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_maps)
        mapView = findViewById(R.id.mapview)

        val imageProvider = ImageProvider.fromResource(this, R.drawable.ic_pin)

        data class MyData(val salePointName: String)


        val gson = Gson()
        val jsonFileName = "offices.json"
        val jsonString = application.assets.open(jsonFileName).bufferedReader().use {
            it.readText()
        }

        val user = gson.fromJson(jsonString, mData::class.java)

        for (one in user) {

            mapView.map.mapObjects.addPlacemark().apply {
                geometry = Point(one.latitude, one.longitude)
                setIcon(imageProvider)
            }
        }






        val placemark = mapView.map.mapObjects.addPlacemark().apply {
            geometry = Point(59.935493, 30.327392)
            setIcon(imageProvider)
        }
        placemark.addTapListener(placemarkTapListener)


    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}