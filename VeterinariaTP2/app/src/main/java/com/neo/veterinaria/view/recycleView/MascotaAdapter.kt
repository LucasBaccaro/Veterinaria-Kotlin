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


class MascotaAdapter(val lista: ArrayList<Mascota>) :

    RecyclerView.Adapter<MascotaAdapter.ViewHolder>() {

    //METODOS QUE SE IMPLEMENTAN DEL RECYCLEW VIWEW ADAPTER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.mascota_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //nos evitamos hacer el recorrido de la lista, lo hace el recyclewview
        holder.id.text = "CODIGO: " + lista[position].id.toString()
        holder.nombre.text = "NOMBRE: " + lista[position].nombre
        holder.tipo.text = "TIPO: " + lista[position].tipo
        holder.raza.text = "RAZA: " + lista[position].raza
        holder.edad.text = "EDAD: " + lista[position].edad.toString()
        holder.causaDeAtencion.text = "CAUSA DE ATENCION: " + lista[position].causaDeAtencion
        holder.causaDeRevision.text = "CAUSA DE REVISION: " + lista[position].causaDeRevision
        holder.medicacion.text = "MEDICACION: " + lista[position].medicamentos
        holder.nombreMedico.text = "NOMBRE DEL MEDICO: " + lista[position].nombreMedico
    }

    //MAPEO DE ELEMENTOS
    //GENERAMOS UNA CLASE HIJA QUE IMPLEMENTA VIEWHOLDER
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //es el maping de nuestro layout mascota_layout

        var id: TextView
        var nombre: TextView
        var tipo: TextView
        var raza: TextView
        var edad: TextView
        var causaDeAtencion: TextView
        var causaDeRevision: TextView
        var medicacion: TextView
        var nombreMedico: TextView


        init {
            id = view.findViewById(R.id.rv_txt_id)
            nombre = view.findViewById(R.id.rv_txt_nombre)
            tipo = view.findViewById(R.id.rv_txt_tipo)
            raza = view.findViewById(R.id.rv_txt_raza)
            edad = view.findViewById(R.id.rv_txt_edad)
            causaDeAtencion = view.findViewById(R.id.rv_txt_causaAtencion)
            causaDeRevision = view.findViewById(R.id.rv_txt_causaRevision)
            medicacion = view.findViewById(R.id.rv_txt_medicamentos)
            nombreMedico = view.findViewById(R.id.rv_txt_nombreMedico)
        }
    }
}