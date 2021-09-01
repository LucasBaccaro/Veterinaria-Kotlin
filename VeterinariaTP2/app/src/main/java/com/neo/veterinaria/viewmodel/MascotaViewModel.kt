package com.neo.veterinaria.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.neo.veterinaria.dao.DbHelper
import com.neo.veterinaria.model.Mascota

class MascotaViewModel : ViewModel() {

    //Guardo mascota y llamo al dbHelper para guardarlo en BD
    fun saveMascota(
        nombre: String,
        tipo: String,
        raza: String,
        edad: Int,
        causaDeAtencion: String,
        causaDeRevision: String,
        medicamentos: String,
        nombreMedico: String,
        atendido: String,
        context: Context
    ): Boolean {
        val db: DbHelper = DbHelper(context, null)
        return db.saveMascot(
            Mascota(
                0,
                nombre,
                tipo,
                raza,
                edad,
                causaDeAtencion,
                causaDeRevision,
                medicamentos,
                nombreMedico,
                atendido
            )
        )
    }

    //Obtengo ID desde el desde la BD
    fun getId(context: Context): Boolean {

        val db: DbHelper = DbHelper(context, null)
        return db.obtenerID()
    }

    //Obtengo las mascotas desde la BD
    fun getAllMascotas(context: Context): ArrayList<Mascota> {
        val db: DbHelper = DbHelper(context, null)
        return db.obtenerMascotas()
    }

    //Obtengo las mascotas no atendidas segun la variable ATENDIDO
    fun getMascotasNoAtendidas(context: Context): ArrayList<Mascota> {
        val db: DbHelper = DbHelper(context, null)
        return db.obtenerMascotasParaAtender()
    }

    //Modificar mascota desde la bd
    fun modificoMascota(
        causaDeRevision: String,
        medicamentos: String,
        nombreMedico: String,
        id: Int,
        atendido: String,
        context: Context
    ): Boolean {
        val db: DbHelper = DbHelper(context, null)
        //var num = db.obtenerNombreMedicos(nombreMedico)

        //Si es mayor a 5 el nombre del medico no lo dejo pasar
        if (db.obtenerNombreMedicos(nombreMedico) < 4) {
            db.modificarMascota(id, causaDeRevision, medicamentos, nombreMedico, atendido)
            Toast.makeText(context.applicationContext, "Mascota atendida", Toast.LENGTH_SHORT)
                .show()
            return true
        } else {
            Toast.makeText(
                context.applicationContext,
                "El medico no puede atender mas animales",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }

    //Elimino la tabla, cuando finaliza el dia desde la bd
    fun eliminarMascotas(context: Context): Boolean {

        var retorno: Boolean
        try {
            val db: DbHelper = DbHelper(context, null)
            db.eliminarMascota()
            retorno = true

        } catch (e: Exception) {
            e.message.toString()
            retorno = false
        }
        return retorno
    }

}


