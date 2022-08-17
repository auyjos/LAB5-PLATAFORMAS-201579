package com.example.lab4jose

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class DetailsActivity : AppCompatActivity() {
    val CAMERARQ = 102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val btnLlamar: Button = findViewById(R.id.buttonLlamar)
        val btnCamera : Button = findViewById(R.id.buttonVista)


        llamarCel(btnLlamar)
       // requestCamera(btnCamera)
        buttonTap(btnCamera)

    }
 fun llamarCel(button: Button){
     val intentLlamar: Intent = Intent(Intent.ACTION_DIAL)
     intentLlamar.setData(Uri.parse("tel: +502 2473 7702"))
     button.setOnClickListener{
         startActivity(intentLlamar)
     }
 }
    /*/
    fun requestCamera(button: Button){
        val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                Toast.makeText(applicationContext, "Permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
        button.setOnClickListener{
           requestCameraPermission.launch(Manifest.permission.CAMERA)
       }
    }*/
    fun requestPermissions (permission:String, name:String, requestCode: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED ->{
                    Toast.makeText(applicationContext, "Permission granted", Toast.LENGTH_SHORT).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(permission, name, requestCode)
                else -> ActivityCompat.requestPermissions(this, arrayOf(permission) , requestCode)
            }


            }
        }
        fun showDialog(permission:String, name:String, requestCode: Int){
            val builder = AlertDialog.Builder(this)
            builder.apply {
                setMessage("Permission to access your $name is required to use this app")
                setTitle("Permission required")
                setPositiveButton("OK") {dialog, which ->
                    ActivityCompat.requestPermissions(this@DetailsActivity, arrayOf(permission),requestCode)
                }
            }
            val dialog = builder.create()
            dialog.show()
        }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(applicationContext, "$name permission refused", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "$name permission granted", Toast.LENGTH_SHORT).show()
            }

        }
        when(requestCode){
            CAMERARQ -> innerCheck("Camera")
        }
     }
    private  fun buttonTap (button: Button){
        button.setOnClickListener{
           requestPermissions(android.Manifest.permission.CAMERA, "Camera", CAMERARQ)
        }
    }
}

