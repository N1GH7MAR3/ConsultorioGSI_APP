package com.example.gsi

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.databinding.ActivityAcercaNosotrosBinding


class AcercaNosotrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaNosotrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAcercaNosotrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val facebookId   ="https://www.facebook.com/gruposaludintegral";
        val facebookUrl  ="https://www.facebook.com/gruposaludintegral";

        val paginaurl  ="https://majosua.wixsite.com/gruposaludintegral?fbclid=IwAR3IRs-HwTnyunByFBfMeZAcddz-DhmIJLzxeCmEmu0PPe1U0ORzsoXe4zY";
        val paginaid  ="https://majosua.wixsite.com/gruposaludintegral?fbclid=IwAR3IRs-HwTnyunByFBfMeZAcddz-DhmIJLzxeCmEmu0PPe1U0ORzsoXe4zY";


        //AbrirWhatsApp()
        binding.imageBtnWhatsapp.setOnClickListener {

            AbrirWhatsApp()
        }
        binding.imageBtnFacebook.setOnClickListener {

            cargarPaginas(facebookId,facebookUrl)
        }
        binding.imageBtnPagina.setOnClickListener {
            cargarPaginas(paginaurl,paginaid)
        }
        binding.btnRegresar.setOnClickListener {
            finish()
        }

       // Toast.makeText(this@AcercaNosotrosActivity, "hola"+ usuario, Toast.LENGTH_SHORT).show()

    }

    private fun AbrirWhatsApp() {
        val _intencion = Intent(Intent.ACTION_VIEW)
        val uri = "whatsapp://send?phone=" + "+51989706376" + "&text="+ "Hola , tengo una duda"
        _intencion.data=Uri.parse(uri)
        try {
            startActivity(_intencion)
        } catch (ex:ActivityNotFoundException) {
            Toast.makeText(this,ex.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarPaginas(urlId: String, urlUrl: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlId)))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlUrl)))
        }
    }
}