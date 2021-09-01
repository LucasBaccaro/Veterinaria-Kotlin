package com.neo.veterinaria.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.neo.veterinaria.R
import com.neo.veterinaria.viewmodel.MascotaViewModel
import java.lang.Exception

class AtenderActivity : AppCompatActivity() {

    lateinit var medicamentos: EditText
    lateinit var causaDeRevision: EditText
    lateinit var rgDocs: RadioGroup
    lateinit var mascotaAtendida: Button
    lateinit var id: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atender)
        //Llamo al MascotaViewModel para llamar a los metodos

        val mascotaVM: MascotaViewModel = ViewModelProvider(this).get(MascotaViewModel::class.java)
        inicialize()
        mapperElements()

        //Boton mascota atendida
        mascotaAtendida.setOnClickListener {

            if (causaDeRevision.text.isEmpty() || medicamentos.text.isEmpty()) {
                Toast.makeText(this, "Llene los campos", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val selected: Int = rgDocs!!.checkedRadioButtonId
                    val rb_selected: RadioButton = findViewById(selected)
                    val docs: String = rb_selected!!.text.toString()
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Realmente termino con la atencion de la mascota?")
                    builder.setMessage("Esta por confirmar la atencion")

                    builder.setPositiveButton(android.R.string.yes) { _, _ ->
                        mascotaVM.modificoMascota(
                            causaDeRevision.text.toString(),
                            medicamentos.text.toString(),
                            docs,
                            id.text.toString().toInt(),
                            atendido = " SI ",
                            it.context
                        )
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    builder.setNegativeButton(android.R.string.no) { _, _ ->
                        Toast.makeText(this, "Permaneces en la app", Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
                } catch (e: Exception) {
                    Toast.makeText(
                        this,
                        "Este medico no puede atender mas animales",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    //Inicializador
    fun inicialize() {
        medicamentos = findViewById(R.id.txt_Medicamentos)
        causaDeRevision = findViewById(R.id.txt_CausasDeRevision)
        mascotaAtendida = findViewById(R.id.btn_mascotaAtendida)
        rgDocs = findViewById(R.id.rg_docs)
        id = findViewById(R.id.txt_id)
    }

    //Mapeo de elementos
    fun mapperElements() {
        id.setText(intent.getStringExtra("id"))
        causaDeRevision.setText(intent.getStringExtra("causaRevision"))
        medicamentos.setText(intent.getStringExtra("medicamentos"))
    }
}