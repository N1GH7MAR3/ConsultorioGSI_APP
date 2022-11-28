package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gsi.databinding.ActivityContactoEmergenciaPacienteBinding
import com.example.gsi.databinding.ActivityDashboardInvitadoBinding
import com.example.gsi.databinding.ActivityUbicacionBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UbicacionActivity : AppCompatActivity() ,OnMapReadyCallback{
    private lateinit var binding: ActivityUbicacionBinding
    private lateinit var mapa:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUbicacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createFragment()
        binding.btnRegresar.setOnClickListener {
            val intent= Intent(this,DashboardInvitadoActivity::class.java)
            startActivity(intent)

        }
    }



    private fun createFragment() {
        val mapFragment:SupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapa=googleMap
        createMarket()
    }

    private fun createMarket() {
        val cordinate = LatLng(-12.053140917452163, -77.05596636735508)
        val marker =MarkerOptions().position(cordinate).title("Policlinico GSI")
        mapa.addMarker(marker)
        mapa.animateCamera(
            CameraUpdateFactory.newLatLngZoom(cordinate,18f),4000,null
        )
    }


}