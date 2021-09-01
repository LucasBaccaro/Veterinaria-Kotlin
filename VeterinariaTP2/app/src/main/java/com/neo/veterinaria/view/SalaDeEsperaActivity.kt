package com.neo.veterinaria.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neo.veterinaria.R
import com.neo.veterinaria.view.recycleView.MascotaAdapter
import com.neo.veterinaria.view.recycleView.MascotaAdapterSalaDeEspera
import com.neo.veterinaria.viewmodel.MascotaViewModel

class SalaDeEsperaActivity : AppCompatActivity() {

    lateinit var rv_mascotasSala: RecyclerView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sala_de_espera)
        inicialize()

        //Instancio ViewModel
        val mascotaVM = ViewModelProvider(this).get(MascotaViewModel::class.java)

        //Inicio recyclerView
        rv_mascotasSala.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_mascotasSala.adapter = MascotaAdapterSalaDeEspera(mascotaVM.getMascotasNoAtendidas(this))
    }

    //Inicializo
    fun inicialize() {
        rv_mascotasSala = findViewById(R.id.i_rv_mascotassala)
    }

}