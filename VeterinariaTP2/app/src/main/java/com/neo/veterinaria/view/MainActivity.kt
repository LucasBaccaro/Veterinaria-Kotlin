package com.neo.veterinaria.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neo.veterinaria.R
import com.neo.veterinaria.view.recycleView.MascotaAdapter
import com.neo.veterinaria.viewmodel.MascotaViewModel


class MainActivity : AppCompatActivity() {

    lateinit var ingresar: Button
    lateinit var informes: Button
    lateinit var salaDeEspera: Button
    lateinit var cierre: Button
    lateinit var salir: Button
    lateinit var rv_mascotas: RecyclerView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicialize()

        //Llamo al MascotaViewModel para llamar a los metodos
        val mascotaVM = ViewModelProvider(this).get(MascotaViewModel::class.java)

        //Boton ingresar
        ingresar.setOnClickListener {

            if (mascotaVM.getId(it.context)) {
                Toast.makeText(this, "No se pueden atender mas mascotas", Toast.LENGTH_SHORT).show()
            } else {
                val intent: Intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        //Boton sala de spera
        salaDeEspera.setOnClickListener {

            //Llamo a la actividad sala de espera
            val intent: Intent = Intent(this, SalaDeEsperaActivity::class.java)
            startActivity(intent)
        }

        //Boton informes
        informes.setOnClickListener {

            //Builder para confirmar los informes
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Desea realizar los informes diarios?")
            builder.setMessage("Accion de informes")

            //Si oprime SI
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                rv_mascotas.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                rv_mascotas.adapter = MascotaAdapter(mascotaVM.getAllMascotas(this))
            }
            //Si proime NO
            builder.setNegativeButton(android.R.string.no) { _, _ ->
            }
            builder.show()
        }

        //Boton cierre de dia
        cierre.setOnClickListener {
            //Builder para confirmar el cierre
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Deseas terminar el dia?")
            builder.setMessage("Esta por cerrar el dia.")

            //Si oprime SI
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                mascotaVM.eliminarMascotas(it.context)
                Toast.makeText(this, "Cierre del dia completado", Toast.LENGTH_SHORT).show()
            }
            //Si oprime NO
            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(this, "El dia continua su curso", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        //Boton salir
        salir.setOnClickListener {
            //Builder para confirmar el cierre
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Realmente desea salir?")
            builder.setMessage("Esta por salir de la aplicacion")

            //Si oprime SI
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                finishAffinity()
            }
            //Si oprime NO
            builder.setNegativeButton(android.R.string.no) { _, _ ->
                Toast.makeText(this, "Permaneces en la app", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }

    //Inicializador
    private fun inicialize() {
        ingresar = findViewById(R.id.btn_ingreso)
        informes = findViewById(R.id.btn_informes)
        salaDeEspera = findViewById(R.id.btn_salaDeEspera)
        cierre = findViewById(R.id.btn_cerrarDia)
        rv_mascotas = findViewById(R.id.i_rv_mascotas)
        salir = findViewById(R.id.btn_salir)
    }
}