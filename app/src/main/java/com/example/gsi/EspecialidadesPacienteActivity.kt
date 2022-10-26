package com.example.gsi


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityDashboardPacienteBinding
import com.example.gsi.databinding.ActivityEspecialidadesPacienteBinding


class EspecialidadesPacienteActivity : AppCompatActivity() {
    private lateinit var binding2: ActivityDashboardPacienteBinding
    private lateinit var binding: ActivityEspecialidadesPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadesPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getEspecilidadesPaciente(this@EspecialidadesPacienteActivity, binding)


        //tolbar
        val tolbar :Toolbar= findViewById(R.id.customPrinciapl)
        setSupportActionBar(tolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }





    //tolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //tolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.iBack_icon -> {
                //Toast.makeText(this,"buscar", Toast.LENGTH_SHORT).show();
                val intent = Intent(this@EspecialidadesPacienteActivity, DashboardPacienteActivity::class.java)
                binding2 = ActivityDashboardPacienteBinding.inflate(layoutInflater)
                setContentView(binding.root)
                val nombre = intent.getStringExtra("nombre")
                binding2.txtNombre.text = "Hola, $nombre";
                startActivity(intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }


}