package com.example.lab4jose

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    //lateinit var btnToNextActivity: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToNextActivity: Button = findViewById(R.id.button_next)

        //val mapsButton : Button = findViewById(R.id.buttonMaps)
        val secondButton: Button = findViewById(R.id.button_detalles)
        // val intentButton = (Intent(this, DetailsActivity::class.java))
        val direccionButton: Button = findViewById(R.id.buttonMaps)
        val descargarAppButton: Button = findViewById(R.id.buttonDescarga)
        initListeners(btnToNextActivity)
        detallesListener(secondButton)
        descargarApp(descargarAppButton)
        setLocation(direccionButton)
        //  dirreccionListener(direccionButton)
    }

    private fun initListeners(button: Button) {
        button.setOnClickListener {
            startActivity(Intent(this, WidgetActivity::class.java))

        }
    }

    private fun detallesListener(button: Button) {
        button.setOnClickListener {
            startActivity(Intent(this, DetailsActivity::class.java))
        }
    }

    private fun descargarApp(button: Button) {
        val intentDescargar = Intent(Intent.ACTION_VIEW)
        intentDescargar.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=es" + packageName))
        button.setOnClickListener {
            startActivity(intentDescargar)
        }
    }

    fun setLocation(button: Button) {
        val gmmIntentUri =
            Uri.parse("https://www.google.com/maps/place/Tony+Roma's/@14.6212713,-90.5579625,17z/data=!4m5!3m4!1s0x8589a1afbbd2ca15:0xdbbd1f3a1225c906!8m2!3d14.6212713!4d-90.5557738")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        button.setOnClickListener {
            startActivity(mapIntent)
        }
    }
}



