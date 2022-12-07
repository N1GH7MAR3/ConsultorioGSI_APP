package com.example.gsi.Service
import android.app.Activity

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsi.*
import com.example.gsi.Adapter.*
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.*
import com.example.gsi.databinding.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



open class ApiService {

    //Verificar Existencia del Usuario
    fun verifyUser(
        binding: ActivityLoginBinding,
        username: EditText,
        password: EditText
    ) {

        val user = ValidateUsuario(username.text.toString(), password.text.toString())
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.verifyUser(user).enqueue(
                object : Callback<Usuario> {
                    override fun onResponse(
                        call: Call<Usuario>,
                        response: Response<Usuario>
                    ) {
                        if (response.code() == 404) {
                            binding.txtInputUsuario.isErrorEnabled = true
                            binding.txtInputUsuario.error = "Usuario no Existe"
                            call.cancel()
                        } else if (response.body()?.contraseña.toString() != password.text.toString()) {
                            binding.txtInputPassword.isErrorEnabled = true
                            binding.txtInputPassword.error = "Contraseña Incorrecta"
                            call.cancel()
                        } else if (response.body()?.rol?.nombre.toString() == "admin") {
                            Toast.makeText(
                                binding.btnLogin.context,
                                "Ingreso satisfactorio con login",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(
                                binding.txtInputPassword.context,
                                DashboardAdminActivity::class.java
                            )
                            intent.putExtra("nombre", response.body()?.usuario)
                            binding.btnLogin.context.startActivity(intent)
                            (binding.btnLogin.context as Activity).finish()
                            call.cancel()
                        } else {
                            Toast.makeText(
                                binding.txtInputPassword.context,
                                "Ingreso satisfactorio con login",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(
                                binding.txtInputPassword.context,
                                DashboardPacienteActivity::class.java
                            )
                            intent.putExtra("usuario", response.body()?.usuario)
                            binding.btnLogin.context.startActivity(intent)
                            call.cancel()
                        }
                    }


                    override fun onFailure(call: Call<Usuario>, t: Throwable) {
                        Toast.makeText(
                            binding.txtInputPassword.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            )

        }
    }


    //Buscar Paciente para obtener nombre y dni
    fun searchPaciente(binding: ActivityDashboardPacienteBinding, usuario: String,nombreu:String) {
        val pac = SearchUsuario(usuario)
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.searchPaciente(pac).enqueue(
                object : Callback<Paciente> {
                    override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                        if (response.isSuccessful) {
                            binding.cardReservaCitas.isEnabled=true
                            if(nombreu == "null"){
                                binding.txtNombre.text = response.body()?.nombre
                            }else{
                                binding.txtNombre.text = nombreu
                            }

                            if (response.body()?.sexo?.nombre == "Femenino") {
                                binding.imagePerfil.setImageResource(R.drawable.img_girl2)
                            } else {
                                binding.imagePerfil.setImageResource(R.drawable.img_boy2)
                            }
                            binding.imagePerfil.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAcercaNosotros.context,
                                    PacientePerfilActivity::class.java
                                )

                                intent.putExtra("id", response.body()?.id.toString())
                                intent.putExtra("nombre", binding.txtNombre.text)
                                intent.putExtra("apePaterno", response.body()?.apellido_paterno)
                                intent.putExtra("apeMaterno", response.body()?.apellido_materno)
                                intent.putExtra("dni", response.body()?.dni.toString())
                                intent.putExtra("direccion", response.body()?.direccion)
                                intent.putExtra("telefono", response.body()?.telefono)
                                intent.putExtra("correo", response.body()?.correo)
                                intent.putExtra("paisid", response.body()?.pais?.id.toString())
                                intent.putExtra("paisnombre", response.body()?.pais?.nombre)
                                intent.putExtra(
                                    "estadocivilid",
                                    response.body()?.estadoCivil?.id.toString()
                                )
                                intent.putExtra(
                                    "estadocivilnombre",
                                    response.body()?.estadoCivil?.nombre
                                )
                                intent.putExtra("sexoid", response.body()?.sexo?.id.toString())
                                intent.putExtra("sexonombre", response.body()?.sexo?.nombre)
                                intent.putExtra(
                                    "usuarioid",
                                    response.body()?.usuario?.id.toString()
                                )
                                intent.putExtra("usuario", response.body()?.usuario?.usuario)
                                intent.putExtra("password", response.body()?.usuario?.contraseña)
                                binding.cardAcercaNosotros.context.startActivity(intent)
                                (binding.imagePerfil.context as Activity).finish()

                            }
                            binding.cardReservaCitas.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAcercaNosotros.context,
                                    ReservaCitaPacienteActivity::class.java
                                )
                                intent.putExtra("id", response.body()?.id.toString())
                                binding.cardAcercaNosotros.context.startActivity(intent)
                            }
                            binding.cardControlSalud.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAcercaNosotros.context,
                                    ControlSaludActivity::class.java
                                )
                                intent.putExtra("usuario", response.body()?.usuario?.usuario)
                                binding.cardAcercaNosotros.context.startActivity(intent)

                            }
                        }
                    }

                    override fun onFailure(call: Call<Paciente>, t: Throwable) {
                        Toast.makeText(
                            binding.cardAcercaNosotros.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }

    }

    fun searchPaciente(binding: ActivityControlSaludBinding, usuario: String) {
        val pac = SearchUsuario(usuario)
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.searchPaciente(pac).enqueue(
                object : Callback<Paciente> {
                    override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                        if (response.isSuccessful) {

                            binding.cardContactoEmergencia.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAlergias.context,
                                    ContactoEmergenciaPacienteActivity::class.java
                                )
                                intent.putExtra("usuario", usuario)
                                intent.putExtra(
                                    "contactoemergenciaid",
                                    response.body()?.contactoEmergencia?.id.toString()
                                )
                                intent.putExtra(
                                    "contactoemergencia",
                                    response.body()?.contactoEmergencia?.descripcion
                                )
                                binding.cardAlergias.context.startActivity(intent)
                            }
                            binding.cardContactoMedico.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAlergias.context,
                                    ContactoMedicoPacienteActivity::class.java
                                )
                                intent.putExtra("usuario", usuario)
                                intent.putExtra(
                                    "contactomedicoid",
                                    response.body()?.contactoMedico?.id.toString()
                                )
                                intent.putExtra(
                                    "contactomedico",
                                    response.body()?.contactoMedico?.descripcion
                                )
                                binding.cardAlergias.context.startActivity(intent)
                            }
                            binding.cardEnfermedades.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAlergias.context,
                                    EnfermedadPacienteActivity::class.java
                                )
                                intent.putExtra("usuario", usuario)
                                intent.putExtra(
                                    "enfermedadid",
                                    response.body()?.enfermedad?.id.toString()
                                )
                                intent.putExtra(
                                    "enfermedad",
                                    response.body()?.enfermedad?.descripcion
                                )
                                binding.cardAlergias.context.startActivity(intent)
                            }
                            binding.cardMedicina.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAlergias.context,
                                    MedicinaPacienteActivity::class.java
                                )
                                intent.putExtra("usuario", usuario)
                                intent.putExtra(
                                    "medicinaid",
                                    response.body()?.medicina?.id.toString()
                                )
                                intent.putExtra("medicina", response.body()?.medicina?.descripcion)
                                binding.cardAlergias.context.startActivity(intent)
                            }
                            binding.cardAlergias.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAlergias.context,
                                    AlergiaPacienteActivity::class.java
                                )
                                intent.putExtra("usuario", usuario)
                                intent.putExtra(
                                    "alergiaid",
                                    response.body()?.alergia?.id.toString()
                                )
                                binding.cardAlergias.context.startActivity(intent)
                            }
                            binding.cardCita.setOnClickListener {
                                val intent = Intent(
                                    binding.cardAlergias.context,
                                    CitaPacienteActivity::class.java
                                )
                                intent.putExtra(
                                    "dni",
                                    response.body()?.dni.toString()
                                )
                                binding.cardAlergias.context.startActivity(intent)
                            }

                        }
                    }

                    override fun onFailure(call: Call<Paciente>, t: Throwable) {
                        Toast.makeText(
                            binding.cardAlergias.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }

    }

    fun searchPaciente(binding: ActivityAlergiaPacienteBinding, usuario: String) {
        val pac = SearchUsuario(usuario)
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.searchPaciente(pac).enqueue(
                object : Callback<Paciente> {
                    override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                        if (response.isSuccessful) {
                            binding.txtAlergia.setText(response.body()?.alergia?.descripcion)
                        }
                    }

                    override fun onFailure(call: Call<Paciente>, t: Throwable) {
                        Toast.makeText(
                            binding.btnRegresar.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }

    }

    fun searchPaciente(binding: ActivityContactoEmergenciaPacienteBinding, usuario: String) {
        val pac = SearchUsuario(usuario)
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.searchPaciente(pac).enqueue(
                object : Callback<Paciente> {
                    override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                        if (response.isSuccessful) {
                            binding.txtContactoEmergencia.setText(response.body()?.contactoEmergencia?.descripcion)
                        }
                    }

                    override fun onFailure(call: Call<Paciente>, t: Throwable) {
                        Toast.makeText(
                            binding.btnRegresar.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    fun searchPaciente(binding: ActivityContactoMedicoPacienteBinding, usuario: String) {
        val pac = SearchUsuario(usuario)
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.searchPaciente(pac).enqueue(
                object : Callback<Paciente> {
                    override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                        if (response.isSuccessful) {
                            binding.txtContactoMedico.setText(response.body()?.contactoMedico?.descripcion)
                        }
                    }

                    override fun onFailure(call: Call<Paciente>, t: Throwable) {
                        Toast.makeText(
                            binding.btnRegresar.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    fun searchPaciente(binding: ActivityEnfermedadPacienteBinding, usuario: String) {
        val pac = SearchUsuario(usuario)
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.searchPaciente(pac).enqueue(
                object : Callback<Paciente> {
                    override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                        if (response.isSuccessful) {
                            binding.txtEnfermedad.setText(response.body()?.enfermedad?.descripcion)
                        }
                    }

                    override fun onFailure(call: Call<Paciente>, t: Throwable) {
                        Toast.makeText(
                            binding.btnRegresar.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    fun searchPaciente(binding: ActivityMedicinaPacienteBinding, usuario: String) {
        val pac = SearchUsuario(usuario)
        CoroutineScope(Dispatchers.Main).launch {
            Constant.retrofit.searchPaciente(pac).enqueue(
                object : Callback<Paciente> {
                    override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                        if (response.isSuccessful) {
                            binding.txtMedicina.setText(response.body()?.medicina?.descripcion)
                        }
                    }

                    override fun onFailure(call: Call<Paciente>, t: Throwable) {
                        Toast.makeText(
                            binding.btnRegresar.context,
                            Constant.NoInternet,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }


    //Obtener todas las especialidades
    fun getEspecilidadesPaciente(
        binding: ActivityEspecialidadesPacienteBinding
    ) {
        fun iniRecyclerView(list: List<Especialidad>) {

            binding.rvEspecialidades.layoutManager =
                LinearLayoutManager(binding.rvEspecialidades.context.applicationContext)
            binding.rvEspecialidades.adapter = EspecialidadAdapter(list)
        }
        binding.txtEspecialidad.text = "Cargando Especialidades..."
        Constant.retrofit.getAllEspecialidades()
            .enqueue(object : Callback<List<Especialidad>> {
                override fun onResponse(
                    call: Call<List<Especialidad>>,
                    response: Response<List<Especialidad>>
                ) {
                    (binding.rvEspecialidades.context as Activity).runOnUiThread {
                        val list: List<Especialidad> = response.body()!!
                        iniRecyclerView(list)
                        binding.txtEspecialidad.text = "Especialidades"
                        call.cancel()
                    }
                }

                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                    Toast.makeText(
                        binding.rvEspecialidades.context,
                        Constant.NoInternet,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    //RESERVAR CITAS
    fun getEspecilidadesPaciente(
        binding: ActivityReservaCitaPacienteBinding,id:Long
    ) {
        binding.btnReservarCita.isEnabled=false
        Constant.retrofit.getAllEspecialidades()
            .enqueue(object : Callback<List<Especialidad>> {
                override fun onResponse(
                    call: Call<List<Especialidad>>,
                    response: Response<List<Especialidad>>
                ) {
                    val listEmpty = mutableListOf<String>()
                    binding.spHorario.adapter = ArrayAdapter(
                        binding.spEspecialidad.context,
                        android.R.layout.simple_spinner_dropdown_item,
                        listEmpty
                    )
                    listEmpty.add(0, "Seleccionar")
                    binding.spProcedimiento.adapter = ArrayAdapter(
                        binding.spEspecialidad.context,
                        android.R.layout.simple_spinner_dropdown_item,
                        listEmpty
                    )
                    binding.spTurno.adapter = ArrayAdapter(
                        binding.spEspecialidad.context,
                        android.R.layout.simple_spinner_dropdown_item,
                        listEmpty
                    )
                    binding.spMedicos.adapter = ArrayAdapter(
                        binding.spEspecialidad.context,
                        android.R.layout.simple_spinner_dropdown_item,
                        listEmpty
                    )

                    val listEspecialidades = response.body()
                    val listIdEspecialidades = mutableListOf<Long>()
                    val list = mutableListOf<String>()
                    list.add(0, "Seleccionar")
                    listIdEspecialidades.add(0, 0)
                    for (i in listEspecialidades!!.indices){
                        list += listEspecialidades[i].nombre
                        listIdEspecialidades+=listEspecialidades[i].id
                    }
                    binding.spEspecialidad.adapter = ArrayAdapter(
                        binding.spEspecialidad.context,
                        android.R.layout.simple_spinner_dropdown_item,
                        list
                    )
                    binding.spEspecialidad.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                p0: AdapterView<*>?,
                                p1: View?,
                                p2: Int,
                                p3: Long
                            ) {
                                Constant.retrofit.getProcedimientoxEspecialidad(binding.spEspecialidad.selectedItem.toString())
                                    .enqueue(object : Callback<List<Procedimiento>> {
                                        override fun onResponse(
                                            call: Call<List<Procedimiento>>,
                                            response: Response<List<Procedimiento>>
                                        ) {
                                            val listProcedimiento = response.body()
                                            val listIdProcedimiento = mutableListOf<Long>()
                                            val listp = mutableListOf<String>()
                                            listp.add(0, "Seleccionar")
                                            listIdProcedimiento.add(0, 0)
                                            for (i in listProcedimiento!!.indices) {
                                                listp += listProcedimiento[i].nombre
                                                listIdProcedimiento += listProcedimiento[i].id
                                            }
                                            binding.spProcedimiento.adapter = ArrayAdapter(
                                                binding.spEspecialidad.context,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                listp
                                            )
                                            binding.spProcedimiento.onItemSelectedListener =
                                                object : AdapterView.OnItemSelectedListener {
                                                    override fun onItemSelected(
                                                        p0: AdapterView<*>?,
                                                        p1: View?,
                                                        p2: Int,
                                                        p3: Long
                                                    ) {
                                                        Constant.retrofit.getAllTurno()
                                                            .enqueue(object :
                                                                Callback<List<Turno>> {
                                                                override fun onResponse(
                                                                    call: Call<List<Turno>>,
                                                                    response: Response<List<Turno>>
                                                                ) {
                                                                    val listTurno = response.body()
                                                                    val listIdTurno =
                                                                        mutableListOf<Long>()
                                                                    val listt =
                                                                        mutableListOf<String>()
                                                                    listt.add(0, "Seleccionar")
                                                                    listIdTurno.add(0, 0)

                                                                    for (i in listTurno!!.indices) {
                                                                        listt += listTurno[i].turno
                                                                        listIdTurno += listTurno[i].id
                                                                    }
                                                                    binding.spTurno.adapter =
                                                                        ArrayAdapter(
                                                                            binding.spEspecialidad.context,
                                                                            android.R.layout.simple_spinner_dropdown_item,
                                                                            listt
                                                                        )
                                                                    binding.spTurno.onItemSelectedListener =
                                                                        object :
                                                                            AdapterView.OnItemSelectedListener {
                                                                            override fun onItemSelected(
                                                                                p0: AdapterView<*>?,
                                                                                p1: View?,
                                                                                p2: Int,
                                                                                p3: Long
                                                                            ) {
                                                                                Constant.retrofit.getAllMedicosxProcedimiento(
                                                                                    (listIdProcedimiento.elementAt(
                                                                                        binding.spProcedimiento.selectedItemPosition
                                                                                    )).toLong()
                                                                                ).enqueue(object :
                                                                                    Callback<List<Medico>> {
                                                                                    override fun onResponse(
                                                                                        call: Call<List<Medico>>,
                                                                                        response: Response<List<Medico>>
                                                                                    ) {

                                                                                        val listMedicos =
                                                                                            response.body()
                                                                                        val listm =
                                                                                            mutableListOf<String>()
                                                                                        val listhorario =
                                                                                            mutableListOf<String>()
                                                                                        val listIdMedicos =
                                                                                            mutableListOf<Long>()
                                                                                        listm.add(
                                                                                            0,
                                                                                            "Seleccionar"
                                                                                        )
                                                                                        listIdMedicos.add(
                                                                                            0,
                                                                                            0
                                                                                        )

                                                                                        if (listMedicos != null) {
                                                                                            for (i in listMedicos.indices) {
                                                                                                if (listMedicos[i].turno.turno == binding.spTurno.selectedItem.toString()) {
                                                                                                    listm += listMedicos[i].nombre+" "+listMedicos[i].apellido_paterno
                                                                                                    listIdMedicos += listMedicos[i].id
                                                                                                    val inicio=listMedicos[i].horario.horaingreso.trim().replace(":00:00","",false)
                                                                                                    val fin=listMedicos[i].horario.horasalida.replace(":00:00","",false)

                                                                                                    if(inicio.substring(1).toInt() == 2){
                                                                                                        for(i in inicio.substring(1).toInt()..fin.substring(1).toInt()){
                                                                                                            val h="PM"
                                                                                                            val zero="0"
                                                                                                            listhorario+= "$zero$i:00 $h"
                                                                                                        }
                                                                                                    }else{
                                                                                                        for(i in inicio.substring(1).toInt()..fin.substring(1).toInt()+12){
                                                                                                            var h="AM"
                                                                                                            val zero="0"
                                                                                                            if(i<10 ){
                                                                                                                listhorario+= "$zero$i:00 $h"
                                                                                                            }else if(i in 10..11){
                                                                                                                listhorario+= "$i:00 $h"
                                                                                                            }
                                                                                                            else{
                                                                                                                h="PM"
                                                                                                                listhorario+= "$i:00 $h"
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                    binding.spHorario.adapter=
                                                                                                        ArrayAdapter(
                                                                                                            binding.spEspecialidad.context,
                                                                                                            android.R.layout.simple_spinner_dropdown_item,
                                                                                                            listhorario
                                                                                                        )
                                                                                                    binding.spMedicos.onItemSelectedListener=object :
                                                                                                        AdapterView.OnItemSelectedListener {
                                                                                                        override fun onItemSelected(
                                                                                                            p0: AdapterView<*>?,
                                                                                                            p1: View?,
                                                                                                            p2: Int,
                                                                                                            p3: Long
                                                                                                        ) {
                                                                                                            if(binding.spMedicos.selectedItemPosition>=1
                                                                                                            ){
                                                                                                                binding.spHorario.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                                                                                                                    override fun onItemSelected(
                                                                                                                        p0: AdapterView<*>?,
                                                                                                                        p1: View?,
                                                                                                                        p2: Int,
                                                                                                                        p3: Long
                                                                                                                    ) {
                                                                                                                        if(binding.spHorario.selectedItemPosition>=1){
                                                                                                                            binding.btnReservarCita.isEnabled=false
                                                                                                                        }
                                                                                                                    }
                                                                                                                    override fun onNothingSelected(
                                                                                                                        p0: AdapterView<*>?
                                                                                                                    ) {
                                                                                                                    }
                                                                                                                }


                                                                                                                binding.btnDisponibilidad.setOnClickListener {
                                                                                                                    var horario=""
                                                                                                                    if(binding.spHorario.selectedItem.toString().substring(4,binding.spHorario.selectedItem.toString().length) == " AM"){
                                                                                                                        horario=binding.spHorario.selectedItem.toString().replace(" AM",":00")
                                                                                                                    }else if (binding.spHorario.selectedItem.toString().substring(5,binding.spHorario.selectedItem.toString().length) == " PM"){
                                                                                                                        horario=binding.spHorario.selectedItem.toString().replace(" PM",":00")
                                                                                                                    }
                                                                                                                    val fechacita=binding.inDate.text.toString()+" "+horario

                                                                                                                    Constant.retrofit.searchCitas(fechacita,(listIdEspecialidades.elementAt(
                                                                                                                        binding.spEspecialidad.selectedItemPosition
                                                                                                                    )).toLong(),(listIdMedicos.elementAt(
                                                                                                                        binding.spMedicos.selectedItemPosition
                                                                                                                    )).toLong(),(listIdProcedimiento.elementAt(
                                                                                                                        binding.spProcedimiento.selectedItemPosition
                                                                                                                    )).toLong()).enqueue(object:Callback<Cita>{
                                                                                                                        override fun onResponse(
                                                                                                                            call: Call<Cita>,
                                                                                                                            response: Response<Cita>
                                                                                                                        ) {

                                                                                                                            if(response.code()==200){
                                                                                                                                binding.btnReservarCita.isEnabled=false
                                                                                                                                val fechacita=response.body()?.fechacita
                                                                                                                                val listhorario2 =
                                                                                                                                    mutableListOf<String>()
                                                                                                                                if(inicio.substring(1).toInt() == 2){
                                                                                                                                    for(i in inicio.substring(1).toInt()..fin.substring(1).toInt()){
                                                                                                                                        val h="PM"
                                                                                                                                        val zero="0"

                                                                                                                                        if(fechacita?.substring(12,
                                                                                                                                                fechacita.length).toString().replace(":00:00","",false).toInt()!=i){
                                                                                                                                            listhorario2+= "$zero$i:00 $h"

                                                                                                                                        }
                                                                                                                                    }

                                                                                                                                }else{
                                                                                                                                    for(i in inicio.substring(1).toInt()..fin.substring(1).toInt()+12){
                                                                                                                                        var h="AM"
                                                                                                                                        val zero="0"
                                                                                                                                        if(fechacita?.substring(12,
                                                                                                                                                fechacita.length).toString().replace(":00:00","",false).toInt()!=i){
                                                                                                                                            if(i<10 ){
                                                                                                                                                listhorario2+= "$zero$i:00 $h"
                                                                                                                                            }else if(i in 10..11){
                                                                                                                                                listhorario2+= "$i:00 $h"
                                                                                                                                            }
                                                                                                                                            else{
                                                                                                                                                h="PM"
                                                                                                                                                listhorario2+= "$i:00 $h"
                                                                                                                                            }

                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    binding.btnReservarCita.setOnClickListener {
                                                                                                                                        var horario=""
                                                                                                                                        if(binding.spHorario.selectedItem.toString().substring(4,binding.spHorario.selectedItem.toString().length) == " AM"){
                                                                                                                                            horario=binding.spHorario.selectedItem.toString().replace(" AM",":00")
                                                                                                                                        }else if (binding.spHorario.selectedItem.toString().substring(5,binding.spHorario.selectedItem.toString().length) == " PM"){
                                                                                                                                            horario=binding.spHorario.selectedItem.toString().replace(" PM",":00")
                                                                                                                                        }
                                                                                                                                        val fechacita=binding.inDate.text.toString()+" "+horario
                                                                                                                                        val paciente=putPaciente(id)
                                                                                                                                        val especialidad=putEspecialidad((listIdEspecialidades.elementAt(
                                                                                                                                            binding.spEspecialidad.selectedItemPosition
                                                                                                                                        )).toLong())
                                                                                                                                        val medico=putMedico((listIdMedicos.elementAt(
                                                                                                                                            binding.spMedicos.selectedItemPosition
                                                                                                                                        )).toLong())
                                                                                                                                        val procedimiento=putProcedimiento((listIdProcedimiento.elementAt(
                                                                                                                                            binding.spProcedimiento.selectedItemPosition
                                                                                                                                        )).toLong())
                                                                                                                                        val cita=createCita(fechacita,medico,paciente,especialidad,procedimiento)
                                                                                                                                        Constant.retrofit.createCita(cita).enqueue(object :Callback<Cita>{
                                                                                                                                            override fun onResponse(
                                                                                                                                                call: Call<Cita>,
                                                                                                                                                response: Response<Cita>
                                                                                                                                            ) {
                                                                                                                                                Toast.makeText(binding.spEspecialidad.context,"Cita Registada, lo esperamos",Toast.LENGTH_SHORT).show()

                                                                                                                                            }
                                                                                                                                            override fun onFailure(
                                                                                                                                                call: Call<Cita>,
                                                                                                                                                t: Throwable
                                                                                                                                            ) {
                                                                                                                                            }
                                                                                                                                        })
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                binding.spHorario.adapter=
                                                                                                                                    ArrayAdapter(
                                                                                                                                        binding.spEspecialidad.context,
                                                                                                                                        android.R.layout.simple_spinner_dropdown_item,
                                                                                                                                        listhorario2
                                                                                                                                    )
                                                                                                                            }else{

                                                                                                                                binding.btnReservarCita.isEnabled=true

                                                                                                                            }

                                                                                                                        }
                                                                                                                        override fun onFailure(
                                                                                                                            call: Call<Cita>,
                                                                                                                            t: Throwable
                                                                                                                        ) {
                                                                                                                        }
                                                                                                                    })
                                                                                                                }




                                                                                                            }
                                                                                                        }
                                                                                                        override fun onNothingSelected(
                                                                                                            p0: AdapterView<*>?
                                                                                                        ) {

                                                                                                        }
                                                                                                    }

                                                                                                }

                                                                                            }


                                                                                            binding.spMedicos.adapter =
                                                                                                ArrayAdapter(
                                                                                                    binding.spEspecialidad.context,
                                                                                                    android.R.layout.simple_spinner_dropdown_item,
                                                                                                    listm
                                                                                                )


                                                                                        }

                                                                                    }

                                                                                    override fun onFailure(
                                                                                        call: Call<List<Medico>>,
                                                                                        t: Throwable
                                                                                    ) {

                                                                                    }

                                                                                })
                                                                            }

                                                                            override fun onNothingSelected(
                                                                                p0: AdapterView<*>?
                                                                            ) {

                                                                            }

                                                                        }
                                                                }

                                                                override fun onFailure(
                                                                    call: Call<List<Turno>>,
                                                                    t: Throwable
                                                                ) {

                                                                }

                                                            })
                                                    }

                                                    override fun onNothingSelected(p0: AdapterView<*>?) {

                                                    }

                                                }
                                        }

                                        override fun onFailure(
                                            call: Call<List<Procedimiento>>,
                                            t: Throwable
                                        ) {

                                        }

                                    })

                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {

                            }

                        }

                }

                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                    Toast.makeText(
                        binding.spEspecialidad.context,
                        Constant.NoInternet,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }


    fun getAllPacientes(
        binding: ActivityListaPacienteAdminBinding
    ) {
        fun iniRecyclerView(list: List<Paciente>) {
            binding.rvPaciente.layoutManager =
                LinearLayoutManager(binding.rvPaciente.context.applicationContext)
            binding.rvPaciente.adapter = PacienteAdapter(list)
        }
        binding.txtPaciente.text = "Cargando Pacientes..."
        Constant.retrofit.getAllPacientes()
            .enqueue(object : Callback<List<Paciente>> {
                override fun onResponse(
                    call: Call<List<Paciente>>,
                    response: Response<List<Paciente>>
                ) {
                    (binding.rvPaciente.context as Activity).runOnUiThread {
                        val list: List<Paciente> = response.body()!!
                        iniRecyclerView(list)
                        binding.txtPaciente.text = "PACIENTES"

                    }
                }

                override fun onFailure(call: Call<List<Paciente>>, t: Throwable) {
                    Toast.makeText(
                        binding.rvPaciente.context,
                        Constant.NoInternet,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }


    //Obtener todos los medicos
    fun getAllMedico(
        activity: MedicosAdminActivity,
        binding: ActivityMedicosAdminBinding,
        esp: String
    ) {
        fun iniRecyclerView(list: List<Medico>) {
            binding.rvMedico.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvMedico.adapter = MedicoAdminAdapter(list)
        }
        binding.txtMedicos.text = "Cargando Medicos..."
        Constant.retrofit.getAllMedico().enqueue(object : Callback<List<Medico>> {
            override fun onResponse(
                call: Call<List<Medico>>,
                response: Response<List<Medico>>
            ) {
                activity.runOnUiThread {
                    val list: List<Medico> = response.body()!!
                    val lesp: MutableList<Medico> = mutableListOf()

                    if (esp == "null") {
                        iniRecyclerView(list)
                        binding.txtMedicos.text = "Medicos"
                    } else {
                        for (i in list.indices) {
                            if (list[i].especialidad.nombre == esp) {
                                val listaesp: List<Medico> = mutableListOf(list[i])
                                lesp += listaesp
                                binding.txtMedicos.text = "Medicos"
                            }
                        }
                        iniRecyclerView(lesp)
                    }
                }
            }

            override fun onFailure(call: Call<List<Medico>>, t: Throwable) {

            }

        })
    }


    //Obtener las especialidades para la vista del Admin
    fun getEspecilidadesAdmin(
        activity: EspecialidadesAdminActivity,
        binding: ActivityEspecialidadesAdminBinding
    ) {
        fun iniRecyclerView(list: List<Especialidad>) {
            binding.rvEspecialidades.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvEspecialidades.adapter = EspecialidadAdminAdapter(list)
        }
        binding.txtEspecialidad.text = "Cargando Especialidades..."
        Constant.retrofit.getAllEspecialidades()
            .enqueue(object : Callback<List<Especialidad>> {
                override fun onResponse(
                    call: Call<List<Especialidad>>,
                    response: Response<List<Especialidad>>
                ) {
                    activity.runOnUiThread {
                        val list: List<Especialidad> = response.body()!!
                        iniRecyclerView(list)
                        binding.txtEspecialidad.text = "Especialidades"
                        call.cancel()

                    }
                }

                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            })

    }

    //Crear Especialidad
    fun createEspecialidad(
        especialidad: createEspecialidad,
        binding: ActivityEspecialidadEditarBinding
    ) {
        Constant.retrofit.createEspecialidad(especialidad)
            .enqueue(object : Callback<createEspecialidad> {
                override fun onResponse(
                    call: Call<createEspecialidad>,
                    response: Response<createEspecialidad>
                ) {
                    (binding.btnGuardar.context as Activity).runOnUiThread {
                        val intent =
                            Intent(
                                binding.btnGuardar.context,
                                EspecialidadesAdminActivity::class.java
                            )

                        binding.btnGuardar.context.startActivity(intent)
                        (binding.btnGuardar.context as Activity).finish()
                        call.cancel()
                    }
                }

                override fun onFailure(call: Call<createEspecialidad>, t: Throwable) {
                    Toast.makeText(
                        binding.btnGuardar.context,
                        Constant.NoInternet,
                        Toast.LENGTH_LONG
                    ).show()

                }

            })
    }


    //Editar Especialidad
    fun updateEspecialidad(
        id: Long,
        especialidad: Especialidad,
        binding: ActivityEspecialidadEditarBinding
    ) {
        Constant.retrofit.updateEspecialidad(id, especialidad)
            .enqueue(object : Callback<Especialidad> {
                override fun onResponse(
                    call: Call<Especialidad>,
                    response: Response<Especialidad>
                ) {
                    (binding.btnGuardar.context as Activity).runOnUiThread {
                        Toast.makeText(
                            binding.btnGuardar.context,
                            "Se ha Editado la Especialidad",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent =
                            Intent(
                                binding.btnGuardar.context,
                                EspecialidadesAdminActivity::class.java
                            )
                        binding.btnGuardar.context.startActivity(intent)
                        (binding.btnGuardar.context as Activity).finish()
                    }
                }

                override fun onFailure(call: Call<Especialidad>, t: Throwable) {
                    Toast.makeText(
                        binding.btnGuardar.context,
                        Constant.NoInternet,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }


    //Eliminar Especialidad
    fun deleteEspecialidad(id: Long, binding: ItemEspecialidadesAdminBinding, esp: String) {
        Constant.retrofit.deleteEspecialidad(id).enqueue(object : Callback<Especialidad> {
            override fun onResponse(
                call: Call<Especialidad>,
                response: Response<Especialidad>
            ) {
                    (binding.btnEditar.context as Activity).runOnUiThread {
                        Constant.retrofit.getMedicoxEspecialidad(esp).enqueue(object :
                            Callback<List<Medico>> {
                            override fun onResponse(
                                call: Call<List<Medico>>,
                                response: Response<List<Medico>>
                            ) {
                                Log.e("medicos",response.body()!!.size.toString())
                                if (response.body()!!.isNotEmpty()) {
                                val dialog = AlertDialog.Builder(binding.btnEliminar.context)
                                dialog.setTitle("Eliminar Especialidad")
                                dialog.setMessage("Primero debe eliminar medicos y procedimientos asoaciadas a esta especialidad")
                                dialog.setCancelable(false)
                                dialog.setPositiveButton("Confirmar"
                                ) { _, id ->
                                    val intent = Intent(
                                        binding.ivEspecialidad.context,
                                        MedicosAdminActivity::class.java
                                    )
                                    intent.putExtra("esp", esp)
                                    binding.tvNombreEspecialidad.context.startActivity(intent)
                                    (binding.btnEditar.context as Activity).finish()
                                }
                                dialog.show()
                                }else{
                                    Toast.makeText(
                                        binding.btnEditar.context,
                                        "Se ha eliminado la Especialidad",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent =
                                        Intent(
                                            binding.btnEditar.context.applicationContext,
                                            EspecialidadesAdminActivity::class.java
                                        )
                                    binding.btnEditar.context.startActivity(intent)
                                    (binding.btnEditar.context as Activity).finish()
                                }
                            }

                            override fun onFailure(call: Call<List<Medico>>, t: Throwable) {

                            }
                        })

                }
            }

            override fun onFailure(call: Call<Especialidad>, t: Throwable) {

            }

        })
    }

    fun deleteMedico(binding: ItemMedicoAdminBinding, id: Long) {
        Constant.retrofit.deleteMedico(id).enqueue(object : Callback<Medico> {
            override fun onResponse(call: Call<Medico>, response: Response<Medico>) {
                Toast.makeText(
                    binding.btnEditar.context,
                    "Se ha eliminado el Medico ",
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intent =
                    Intent(
                        binding.btnEditar.context.applicationContext,
                        MedicosAdminActivity::class.java
                    )

                binding.btnEditar.context.startActivity(intent)
                (binding.btnEditar.context as Activity).finish()
            }

            override fun onFailure(call: Call<Medico>, t: Throwable) {

            }

        })
    }

    //Obtener Citas del Paciente
    fun getCitasPaciente(
        dni: Int, activity: CitaPacienteActivity,
        binding: ActivityCitaPacienteBinding
    ) {
        fun iniRecyclerView(list: List<Cita>) {
            binding.rvCita.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvCita.adapter = CitaPacienteAdapter(list)
        }
        Constant.retrofit.getCitasPaciente(dni).enqueue(object : Callback<List<Cita>> {
            override fun onResponse(
                call: Call<List<Cita>>,
                response: Response<List<Cita>>
            ) {
                activity.runOnUiThread {
                    val list: List<Cita> = response.body()!!
                    iniRecyclerView(list)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<List<Cita>>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
            }

        })
    }


    //Crear Medico
    fun createMedico(medico: createMedico, binding: ActivityMedicoAgregarBinding) {
        Constant.retrofit.createMedico(medico).enqueue(object : Callback<createMedico> {
            override fun onResponse(
                call: Call<createMedico>,
                response: Response<createMedico>
            ) {
                Toast.makeText(
                    binding.btnGuardar.context,
                    "Medico Creado",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<createMedico>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


    //Obtener Medicos para la vista del Paciente
    fun getAllMedicoPaciente(
        nombre: String,
        binding: ActivityMedicoPacienteBinding
    ) {
        fun iniRecyclerView(list: List<Medico>) {
            binding.rvMedico.layoutManager =
                LinearLayoutManager(binding.rvMedico.context.applicationContext)
            binding.rvMedico.adapter = MedicoPacienteAdapter(list)
        }
        binding.txtMedicos.text = "Cargando Medicos..."
        Constant.retrofit.getMedicoxEspecialidad(nombre).enqueue(object :
            Callback<List<Medico>> {
            override fun onResponse(
                call: Call<List<Medico>>,
                response: Response<List<Medico>>
            ) {

                (binding.rvMedico.context as Activity).runOnUiThread {
                    val list: List<Medico> = response.body()!!
                    iniRecyclerView(list)
                    binding.txtMedicos.text = "Medicos"
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<List<Medico>>, t: Throwable) {
                Toast.makeText(binding.rvMedico.context, Constant.NoInternet, Toast.LENGTH_LONG)
                    .show()
            }
        })

    }


    fun getAllProcedimientosxMedico(binding: ActivityProcedimientoPacienteBinding, medicoid: Long) {
        fun iniRecyclerView(list: List<Procedimiento>) {
            binding.rvProcedimiento.layoutManager =
                LinearLayoutManager(binding.rvProcedimiento.context.applicationContext)
            binding.rvProcedimiento.adapter = ProdecimientoPacienteAdapter(list)
        }
        binding.txtProcedimiento.text = "Cargando Procedimientos..."
        Constant.retrofit.getAllProcedimientosxMedico(medicoid)
            .enqueue(object : Callback<List<Procedimiento>> {
                override fun onResponse(
                    call: Call<List<Procedimiento>>,
                    response: Response<List<Procedimiento>>
                ) {
                    binding.txtProcedimiento.text = "Procedimientos"
                    val list: List<Procedimiento> = response.body()!!
                    iniRecyclerView(list)
                    call.cancel()
                }

                override fun onFailure(call: Call<List<Procedimiento>>, t: Throwable) {

                }

            })
    }

    //Obtener los paises
    fun getAllPais(binding: ActivityRegisterBinding, paisid: String) {
        Constant.retrofit.getAllPais().enqueue(object : Callback<List<Pais>> {
            override fun onResponse(
                call: Call<List<Pais>>,
                response: Response<List<Pais>>
            ) {
                val list = mutableListOf<String>()
                list.add(0, "Seleccionar")
                val listPais = response.body()
                for (i in listPais!!.indices) list += listPais[i].nombre

                binding.spPais.adapter = ArrayAdapter(
                    binding.btnRegistrate.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                if (paisid== "null") {
                    binding.spPais.setSelection(0)
                } else {
                    binding.spPais.setSelection(paisid.toInt())
                }


            }

            override fun onFailure(call: Call<List<Pais>>, t: Throwable) {
                Toast.makeText(
                    binding.btnRegistrate.context,
                    Constant.NoInternet,
                    Toast.LENGTH_LONG
                ).show()

            }

        })
    }

    fun getAllPais(binding: ActivityMedicoAgregarBinding, paisid: String) {
        Constant.retrofit.getAllPais().enqueue(object : Callback<List<Pais>> {
            override fun onResponse(
                call: Call<List<Pais>>,
                response: Response<List<Pais>>
            ) {
                val list = mutableListOf<String>()
                list.add(0, "Seleccionar")
                val listPais = response.body()
                for (i in listPais!!.indices) list += listPais[i].nombre

                binding.spPais.adapter = ArrayAdapter(
                    binding.btnGuardar.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                if (paisid== "null") {
                    binding.spPais.setSelection(0)
                } else {
                    binding.spPais.setSelection(paisid.toInt())
                }


            }

            override fun onFailure(call: Call<List<Pais>>, t: Throwable) {
                Toast.makeText(
                    binding.btnGuardar.context,
                    Constant.NoInternet,
                    Toast.LENGTH_LONG
                ).show()

            }

        })
    }

    fun getAllTurno(binding: ActivityMedicoAgregarBinding, turnoid: String) {
        Constant.retrofit.getAllTurno().enqueue(
            object : Callback<List<Turno>> {
                override fun onResponse(
                    call: Call<List<Turno>>,
                    response: Response<List<Turno>>
                ) {
                    val listTurno = response.body()
                    val listIdTurno = mutableListOf<Long>()
                    val listt = mutableListOf<String>()
                    listt.add(0, "Seleccionar")
                    listIdTurno.add(0, 0)
                    Log.e("Turno", listTurno.toString())
                    for (i in listTurno!!.indices) {
                        listt += listTurno[i].turno
                        listIdTurno += listTurno[i].id
                    }
                    binding.spTurno.adapter = ArrayAdapter(
                        binding.spEspecialidad.context,
                        android.R.layout.simple_spinner_dropdown_item,
                        listt
                    )
                    if (turnoid== "null") {
                        binding.spTurno.setSelection(0)

                    } else {
                        binding.spTurno.setSelection(turnoid.toInt())
                    }
                    binding.spTurno.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long
                        ) {
                            if(binding.spTurno.selectedItemId.toInt() == 1){
                                binding.txtInputHorario.setText("08:00:00 AM - 01:00:00 PM")
                            }else if(binding.spTurno.selectedItemId.toInt() == 2){
                                binding.txtInputHorario.setText("02:00:00 PM - 01:00:00 PM")
                            }else{
                                binding.txtInputHorario.setText("")
                            }
                        }
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }
                    }


                }

                override fun onFailure(call: Call<List<Turno>>, t: Throwable) {
                    Toast.makeText(
                        binding.btnGuardar.context,
                        Constant.NoInternet,
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
    }

    fun getAllEspecialidades(binding: ActivityMedicoAgregarBinding, especialidadid: String) {
        Constant.retrofit.getAllEspecialidades().enqueue(object : Callback<List<Especialidad>> {
            override fun onResponse(
                call: Call<List<Especialidad>>,
                response: Response<List<Especialidad>>
            ) {
                val list = mutableListOf<String>()
                list.add(0, "Seleccionar")
                val listEspecialidad = response.body()
                for (i in listEspecialidad!!.indices) list += listEspecialidad[i].nombre

                binding.spEspecialidad.adapter = ArrayAdapter(
                    binding.btnGuardar.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                if (especialidadid == "null") {
                    binding.spEspecialidad.setSelection(0)
                } else {
                    binding.spEspecialidad.setSelection(especialidadid.toInt())
                }


            }

            override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                Toast.makeText(
                    binding.btnGuardar.context,
                    Constant.NoInternet,
                    Toast.LENGTH_LONG
                ).show()

            }

        })
    }


    //Obtener el estadoCivil
    fun getAllEstadoCivil(binding: ActivityRegisterBinding, estadocivilid: String) {
        Constant.retrofit.getAllEstadoCivil().enqueue(object : Callback<List<EstadoCivil>> {
            override fun onResponse(
                call: Call<List<EstadoCivil>>,
                response: Response<List<EstadoCivil>>
            ) {
                val list = mutableListOf<String>()
                list.add(0, "Seleccionar")
                val listEstadoCivil = response.body()
                for (i in listEstadoCivil!!.indices) list += listEstadoCivil[i].nombre
                binding.spEstadoCivil.adapter = ArrayAdapter(
                    binding.btnRegistrate.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                if (estadocivilid== "null") {
                    binding.spEstadoCivil.setSelection(0)
                } else {
                    binding.spEstadoCivil.setSelection(estadocivilid.toInt())
                }
            }

            override fun onFailure(call: Call<List<EstadoCivil>>, t: Throwable) {
                Toast.makeText(
                    binding.btnRegistrate.context,
                    Constant.NoInternet,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    fun getAllEstadoCivil(binding: ActivityMedicoAgregarBinding, estadocivilid: String) {
        Constant.retrofit.getAllEstadoCivil().enqueue(object : Callback<List<EstadoCivil>> {
            override fun onResponse(
                call: Call<List<EstadoCivil>>,
                response: Response<List<EstadoCivil>>
            ) {
                val list = mutableListOf<String>()
                list.add(0, "Seleccionar")
                val listEstadoCivil = response.body()
                for (i in listEstadoCivil!!.indices) list += listEstadoCivil[i].nombre
                binding.spEstadoCivil.adapter = ArrayAdapter(
                    binding.btnGuardar.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                if (estadocivilid== "null") {
                    binding.spEstadoCivil.setSelection(0)
                } else {
                    binding.spEstadoCivil.setSelection(estadocivilid.toInt())
                }
            }

            override fun onFailure(call: Call<List<EstadoCivil>>, t: Throwable) {
                Toast.makeText(
                    binding.btnGuardar.context,
                    Constant.NoInternet,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    //Obtener el Sexo
    fun getAllSexo(binding: ActivityRegisterBinding, sexoid: String) {
        Constant.retrofit.getAllSexo().enqueue(object : Callback<List<Sexo>> {
            override fun onResponse(
                call: Call<List<Sexo>>,
                response: Response<List<Sexo>>
            ) {
                val list = mutableListOf<String>()
                list.add(0, "Seleccionar")
                val listSexo = response.body()
                for (i in listSexo!!.indices) list += listSexo[i].nombre
                binding.spSexo.adapter = ArrayAdapter(
                    binding.btnRegistrate.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                if (sexoid== "null") {
                    binding.spSexo.setSelection(0)
                } else {
                    binding.spSexo.setSelection(sexoid.toInt())
                }
            }

            override fun onFailure(call: Call<List<Sexo>>, t: Throwable) {
                Toast.makeText(
                    binding.btnRegistrate.context,
                    Constant.NoInternet,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    fun getAllSexo(binding: ActivityMedicoAgregarBinding, sexoid: String) {
        Constant.retrofit.getAllSexo().enqueue(object : Callback<List<Sexo>> {
            override fun onResponse(
                call: Call<List<Sexo>>,
                response: Response<List<Sexo>>
            ) {
                val list = mutableListOf<String>()
                list.add(0, "Seleccionar")
                val listSexo = response.body()
                for (i in listSexo!!.indices) list += listSexo[i].nombre
                binding.spSexo.adapter = ArrayAdapter(
                    binding.btnGuardar.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    list
                )
                if (sexoid== "null") {
                    binding.spSexo.setSelection(0)
                } else {
                    binding.spSexo.setSelection(sexoid.toInt())
                }
            }

            override fun onFailure(call: Call<List<Sexo>>, t: Throwable) {
                Toast.makeText(
                    binding.btnGuardar.context,
                    Constant.NoInternet,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }


    fun createUsuarioPacienteControlSalud(
        binding: ActivityRegisterBinding,
        usuarioPaciente: createUsuarioPaciente,

        ) {
        Constant.retrofit.createContactoEmergencia(
            createContactoEmergencia(
                ""
            )
        )
            .enqueue(object : Callback<ContactoEmergencia> {
                override fun onResponse(
                    call: Call<ContactoEmergencia>,
                    response: Response<ContactoEmergencia>
                ) {
                    val idCE = putContactoEmergencia(response.body()?.id!!)
                    Constant.retrofit.createContactoMedico(
                        createContactoMedico(
                            ""
                        )
                    ).enqueue(object : Callback<ContactoMedico> {
                        override fun onResponse(
                            call: Call<ContactoMedico>,
                            response: Response<ContactoMedico>
                        ) {
                            val idCM = putContactoMedico(response.body()?.id!!)
                            Constant.retrofit.createEnfermedad(
                                createEnfermedad(
                                    ""
                                )
                            ).enqueue(object : Callback<Enfermedad> {
                                override fun onResponse(
                                    call: Call<Enfermedad>,
                                    response: Response<Enfermedad>
                                ) {
                                    val idE = putEnfermedad(response.body()?.id!!)
                                    Constant.retrofit.createMedicina(
                                        createMedicina(
                                            ""
                                        )
                                    ).enqueue(object : Callback<Medicina> {
                                        override fun onResponse(
                                            call: Call<Medicina>,
                                            response: Response<Medicina>
                                        ) {
                                            val idM = putMedicina(response.body()?.id!!)
                                            Constant.retrofit.createAlergia(
                                                createAlergia(
                                                    ""
                                                )
                                            )
                                                .enqueue(object : Callback<Alergia> {
                                                    override fun onResponse(
                                                        call: Call<Alergia>,
                                                        response: Response<Alergia>
                                                    ) {
                                                        val idA =
                                                            putAlergia(response.body()?.id!!)
                                                        Constant.retrofit.createUsuario(
                                                            usuarioPaciente
                                                        )
                                                            .enqueue(object :
                                                                Callback<Usuario> {
                                                                override fun onResponse(
                                                                    call: Call<Usuario>,
                                                                    response: Response<Usuario>
                                                                ) {
                                                                    val idU =
                                                                        putUsuario(response.body()?.id!!)
                                                                    val pais =
                                                                        putPais(binding.spPais.selectedItemPosition.toLong())
                                                                    val estadoCivil =
                                                                        putEstadoCivil(
                                                                            binding.spEstadoCivil.selectedItemPosition.toLong()
                                                                        )
                                                                    val sexo =
                                                                        putSexo(binding.spSexo.selectedItemPosition.toLong())
                                                                    val pac =
                                                                        createPaciente(
                                                                            binding.editTextTexNombre.text.toString(),
                                                                            binding.ediTextApePaterno.text.toString(),
                                                                            binding.ediTextApeMaterno.text.toString(),
                                                                            binding.editTextDni.text.toString()
                                                                                .toInt(),
                                                                            binding.editTextDireccion.text.toString(),
                                                                            binding.editTextTelefono.text.toString(),
                                                                            binding.editTexEmail.text.toString(),
                                                                            pais,
                                                                            estadoCivil,
                                                                            sexo,
                                                                            idU,
                                                                            idCE,
                                                                            idCM,
                                                                            idE,
                                                                            idM,
                                                                            idA
                                                                        )
                                                                    Constant.retrofit.createPaciente(
                                                                        pac
                                                                    ).enqueue(object :
                                                                        Callback<Paciente> {
                                                                        override fun onResponse(
                                                                            call: Call<Paciente>,
                                                                            response: Response<Paciente>
                                                                        ) {
                                                                            Toast.makeText(
                                                                                binding.btnRegistrate.context,
                                                                                "Usuario/Paciente Registrado con exito",
                                                                                Toast.LENGTH_SHORT
                                                                            ).show()
                                                                            (binding.btnRegistrate.context as Activity).finish()
                                                                        }

                                                                        override fun onFailure(
                                                                            call: Call<Paciente>,
                                                                            t: Throwable
                                                                        ) {
                                                                        }
                                                                    })
                                                                }

                                                                override fun onFailure(
                                                                    call: Call<Usuario>,
                                                                    t: Throwable
                                                                ) {
                                                                }
                                                            })
                                                    }

                                                    override fun onFailure(
                                                        call: Call<Alergia>,
                                                        t: Throwable
                                                    ) {
                                                    }
                                                }
                                                )
                                        }

                                        override fun onFailure(
                                            call: Call<Medicina>,
                                            t: Throwable
                                        ) {
                                        }
                                    })
                                }

                                override fun onFailure(
                                    call: Call<Enfermedad>,
                                    t: Throwable
                                ) {
                                }
                            })
                        }

                        override fun onFailure(call: Call<ContactoMedico>, t: Throwable) {
                        }
                    })
                }

                override fun onFailure(call: Call<ContactoEmergencia>, t: Throwable) {
                }
            }
            )
    }

    fun updatePaciente(binding: ActivityRegisterBinding, id: Long, paciente: updatePaciente) {
        Constant.retrofit.updatePaciente(id, paciente).enqueue(object : Callback<Paciente> {
            override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
            }
            override fun onFailure(call: Call<Paciente>, t: Throwable) {
            }
        })
    }

    fun updateUsuario(
        binding: ActivityRegisterBinding,
        id: Long,
        usuario: createUsuarioPaciente
    ) {
        Constant.retrofit.updateUsuario(id, usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {

            }

        })
    }

    fun updateAlergia(
        id: Long,
        alergia: createAlergia,
        binding: ActivityAlergiaPacienteBinding,
        usuario: String
    ) {
        Constant.retrofit.updateAlergia(id, alergia).enqueue(object : Callback<Alergia> {
            override fun onResponse(call: Call<Alergia>, response: Response<Alergia>) {
                val intent =
                    Intent(
                        binding.txtAlergia.context,
                        binding.txtAlergia.context::class.java
                    )
                intent.putExtra("usuario", usuario)
                intent.putExtra("alergiaid", response.body()?.id.toString())
                intent.putExtra("alergia", response.body()?.descripcion)
                binding.txtAlergia.context.startActivity(intent)
                (binding.txtAlergia.context as Activity).finish()
            }

            override fun onFailure(call: Call<Alergia>, t: Throwable) {
            }
        })
    }

    fun updateContactoEmergencia(
        id: Long,
        contactoEmergencia: createContactoEmergencia,
        binding: ActivityContactoEmergenciaPacienteBinding,
        usuario: String
    ) {
        Constant.retrofit.updateContactoEmergencia(id, contactoEmergencia)
            .enqueue(object : Callback<ContactoEmergencia> {
                override fun onResponse(
                    call: Call<ContactoEmergencia>,
                    response: Response<ContactoEmergencia>
                ) {
                    val intent = Intent(
                        binding.txtContactoEmergencia.context,
                        binding.txtContactoEmergencia.context::class.java
                    )
                    intent.putExtra("usuario", usuario)
                    intent.putExtra("contactoemergenciaid", response.body()?.id.toString())
                    intent.putExtra("contactoemergencia", response.body()?.descripcion)
                    binding.txtContactoEmergencia.context.startActivity(intent)
                    (binding.txtContactoEmergencia.context as Activity).finish()
                }

                override fun onFailure(call: Call<ContactoEmergencia>, t: Throwable) {
                }
            })
    }

    fun updateContactoMedico(
        id: Long,
        contactoMedico: createContactoMedico,
        binding: ActivityContactoMedicoPacienteBinding,
        usuario: String
    ) {
        Constant.retrofit.updateContactoMedico(id, contactoMedico)
            .enqueue(object : Callback<ContactoMedico> {
                override fun onResponse(
                    call: Call<ContactoMedico>,
                    response: Response<ContactoMedico>
                ) {
                    val intent = Intent(
                        binding.txtContactoMedico.context,
                        binding.txtContactoMedico.context::class.java
                    )
                    intent.putExtra("usuario", usuario)
                    intent.putExtra("contactomedicoid", response.body()?.id.toString())
                    intent.putExtra("contactomedico", response.body()?.descripcion)
                    binding.txtContactoMedico.context.startActivity(intent)
                    (binding.txtContactoMedico.context as Activity).finish()
                }

                override fun onFailure(call: Call<ContactoMedico>, t: Throwable) {
                }
            })
    }

    fun updateEnfermedad(
        id: Long,
        enfermedad: createEnfermedad,
        binding: ActivityEnfermedadPacienteBinding,
        usuario: String
    ) {
        Constant.retrofit.updateEnfermedad(id, enfermedad)
            .enqueue(object : Callback<Enfermedad> {
                override fun onResponse(
                    call: Call<Enfermedad>,
                    response: Response<Enfermedad>
                ) {
                    val intent =
                        Intent(
                            binding.txtEnfermedad.context,
                            binding.txtEnfermedad.context::class.java
                        )
                    intent.putExtra("usuario", usuario)
                    intent.putExtra("enfermedadid", response.body()?.id.toString())
                    intent.putExtra("enfermedad", response.body()?.descripcion)
                    binding.txtEnfermedad.context.startActivity(intent)
                    (binding.txtEnfermedad.context as Activity).finish()
                }

                override fun onFailure(call: Call<Enfermedad>, t: Throwable) {
                }
            })
    }

    fun updateMedicina(
        id: Long,
        medicina: createMedicina,
        binding: ActivityMedicinaPacienteBinding,
        usuario: String
    ) {
        Constant.retrofit.updateMedicina(id, medicina).enqueue(object : Callback<Medicina> {
            override fun onResponse(call: Call<Medicina>, response: Response<Medicina>) {
                val intent =
                    Intent(
                        binding.txtMedicina.context,
                        binding.txtMedicina.context::class.java
                    )
                intent.putExtra("usuario", usuario)
                intent.putExtra("medicinaid", response.body()?.id.toString())
                intent.putExtra("medicina", response.body()?.descripcion)
                binding.txtMedicina.context.startActivity(intent)
                (binding.txtMedicina.context as Activity).finish()
            }

            override fun onFailure(call: Call<Medicina>, t: Throwable) {
            }
        })
    }

}






