package com.neo.veterinaria.view.recycleView

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neo.veterinaria.R
import com.neo.veterinaria.model.Mascota
import com.neo.veterinaria.view.AtenderActivity
import java.lang.Exception

class MascotaAdapterSalaDeEspera(val lista: ArrayList<Mascota>) :

    RecyclerView.Adapter<MascotaAdapterSalaDeEspera.ViewHolder>() {

    //METODOS QUE SE IMPLEMENTAN DEL RECYCLEW VIWEW ADAPTER
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MascotaAdapterSalaDeEspera.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.seleccionmascota_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MascotaAdapterSalaDeEspera.ViewHolder, position: Int) {
        holder.id.text = lista[position].id.toString()
        holder.nombre.text = lista[position].nombre

        //Boton atender
        holder.atender.setOnClickListener {
            try {
                val intent: Intent = Intent(it.context, AtenderActivity::class.java)

                intent.putExtra("id", lista[position].id.toString())
                intent.putExtra("causaDeRevision", lista[position].causaDeRevision)
                intent.putExtra("medicamentos", lista[position].medicamentos)
                intent.putExtra("nombreMedico", lista[position].nombreMedico)


                it.context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("ROMPE AL START", e.message.toString())
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //es el maping de nuestro layout seleccionmascota_layout

        var id: TextView
        var nombre: TextView
        var atender: Button

        init {
            id = view.findViewById(R.id.txt_id_sala)
            nombre = view.findViewById(R.id.txt_nombre_sala)
            atender = view.findViewById(R.id.btn_atender_sala)
        }
    }


}