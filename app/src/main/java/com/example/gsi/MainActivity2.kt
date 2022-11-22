package com.example.gsi

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

       val btn =findViewById<Button>(R.id.buttonSuccess)


       btn.setOnClickListener {
            val builder= AlertDialog.Builder(this@MainActivity2)
           val view = layoutInflater.inflate(R.layout.layout_warning_dailog,null)
           builder.setView(view)
           val dialog = builder.create()
           dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
           dialog.setCancelable(false)
           dialog.show()
        }


    }


}