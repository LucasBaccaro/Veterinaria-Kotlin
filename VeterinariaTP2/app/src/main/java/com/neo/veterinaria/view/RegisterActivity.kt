package com.neo.veterinaria.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.neo.veterinaria.R
import com.neo.veterinaria.viewmodel.MascotaViewModel

class RegisterActivity : AppCompatActivity() {

    lateinit var nombre: EditText
    lateinit var sp_tipos: Spinner
    lateinit var raza: EditText
    lateinit var edad: EditText
    lateinit var causaDeAtencion: EditText
    lateinit var cargar: Button

    //Array para el spinner
    val tiposMascota = arrayOf("Perro", "Gato", "Conejo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        inicialize()
        inicializoSpinner()
        var tipoSeleccionado: String = ""
        val mascotaRegisterVM: MascotaViewModel =
            ViewModelProvider(this).get(MascotaViewModel::class.java)

        //Spinner
        sp_tipos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // TODO: cargar la lista
                Toast.makeText(applicationContext, "no hay items seleccionados", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoSeleccionado = tiposMascota[position]
            }

        }

        //Boton cargar mascota
        cargar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Desea cargar la mascota?")

            //Si apreta SI
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                //Pregunto por los campos vacios
                if (nombre.text.isEmpty() || raza.text.isEmpty() || edad.text.isEmpty() || causaDeAtencion.text.isEmpty()) {
                    Toast.makeText(this, "Llene los campos", Toast.LENGTH_SHORT).show()
                } else {
                    //Registro a la mascota, para luego modificarla con los datos que llene el medico.
                    mascotaRegisterVM.saveMascota(
                        nombre.text.toString(),
                        tipoSeleccionado,
                        raza.text.toString(),
                        edad.text.toString().toInt(),
                        causaDeAtencion.text.toString(),
                        causaDeRevision = "",
                        medicamentos = "",
                        nombreMedico = "",
                        atendido = " NO ",
                        it.context
                    )
                    Toast.makeText(this, "Cargado con exito", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            //Si apreta SI
            builder.setNegativeButton(android.R.string.no) { _, _ ->
            }
            builder.show()
        }
    }

    //Inicializo
    fun inicialize() {
        nombre = findViewById(R.id.txt_NombreMascota)
        sp_tipos = findViewById(R.id.s_tipo)
        raza = findViewById(R.id.txt_raza)
        edad = findViewById(R.id.txt_Edad)
        causaDeAtencion = findViewById(R.id.txt_CausaDeAtencion)
        cargar = findViewById(R.id.btn_cargar)
    }

    //Inicializo spinner
    private fun inicializoSpinner() {

        val adaptadorTipos = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposMascota)

        sp_tipos.adapter = adaptadorTipos
    }
}