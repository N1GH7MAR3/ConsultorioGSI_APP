package com.example.gsi

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.databinding.ActivityAcercaNosotrosBinding


class AcercaNosotrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaNosotrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAcercaNosotrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val telefono= "989706376"
        val facebookId   ="https://www.facebook.com/gruposaludintegral";
        val facebookUrl  ="https://www.facebook.com/gruposaludintegral";

        val paginaurl  ="https://majosua.wixsite.com/gruposaludintegral?fbclid=IwAR3IRs-HwTnyunByFBfMeZAcddz-DhmIJLzxeCmEmu0PPe1U0ORzsoXe4zY";
        val paginaid  ="https://majosua.wixsite.com/gruposaludintegral?fbclid=IwAR3IRs-HwTnyunByFBfMeZAcddz-DhmIJLzxeCmEmu0PPe1U0ORzsoXe4zY";

        val usuario = intent.getStringExtra("usuario")
        //AbrirWhatsApp()
        binding.imageBtnWhatsapp.setOnClickListener {

            AbrirWhatsApp(telefono)
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

    private fun AbrirWhatsApp(telefono: String) {
        val _intencion = Intent("android.intent.action.MAIN")
        _intencion.component = ComponentName("com.whatsapp", "com.whatsapp.Conversation")
        _intencion.putExtra(
            "jid",
            PhoneNumberUtils.stripSeparators("51$telefono") + "@s.whatsapp.net"
        )
        startActivity(_intencion)
    }

    private fun cargarPaginas(urlId: String, urlUrl: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlId)))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlUrl)))
        }
    }
}