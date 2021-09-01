package com.neo.veterinaria.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.neo.veterinaria.model.Mascota


//Clase DbHelper
class DbHelper(context: Context, factory:
SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "mascotas.db"
        private val DATABASE_VERSION=6

        val TABLE_NAME = "mascotas"
        val COLUMN_ID = "id"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_TIPO = "tipo"
        val COLUMN_RAZA = "raza"
        val COLUMN_EDAD = "edad"
        val COLUMN_CAUSA_DE_ATENCION = "causaDeAtencion"
        val COLUMN_CAUSA_DE_REVISION = "causaDeRevision"
        val COLUMN_MEDICAMENTOS = "medicamentos"
        val COLUMN_NOMBRE_MEDICO = "nombreMedico"
        val COLUMN_ATENDIDO = "atendido"
    }
    //Creo la tabla de mi app.
    override fun onCreate(db: SQLiteDatabase?) {
        var createTableMascota = ("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NOMBRE + " TEXT, "+
                COLUMN_TIPO + " TEXT, "+
                COLUMN_RAZA + " TEXT, "+
                COLUMN_EDAD + " TEXT, "+
                COLUMN_CAUSA_DE_ATENCION + " TEXT, "+
                COLUMN_CAUSA_DE_REVISION + " TEXT, "+
                COLUMN_MEDICAMENTOS + " TEXT, "+
                COLUMN_ATENDIDO + " TEXT, "+
                COLUMN_NOMBRE_MEDICO + " TEXT ) ")
        db?.execSQL(createTableMascota)
    }
    //Funcion de update
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
        onCreate(db)
    }
    //Funcion guardar Mascota en la BD
    fun saveMascot(mascota : Mascota): Boolean{

        try {
            // Recuperamos el objeto db para escritura en la base de datos
            val db = this.writableDatabase


            // Armo los valores que voy a insertar en mi tabla mascota
            val values = ContentValues()

            values.put(COLUMN_NOMBRE,mascota.nombre)
            values.put(COLUMN_TIPO,mascota.tipo)
            values.put(COLUMN_RAZA,mascota.raza)
            values.put(COLUMN_EDAD,mascota.edad)
            values.put(COLUMN_CAUSA_DE_ATENCION,mascota.causaDeAtencion)
            values.put(COLUMN_CAUSA_DE_REVISION,mascota.causaDeRevision)
            values.put(COLUMN_MEDICAMENTOS,mascota.medicamentos)
            values.put(COLUMN_NOMBRE_MEDICO,mascota.nombreMedico)
            values.put(COLUMN_ATENDIDO,mascota.atendido)

            db.insert(TABLE_NAME,null,values)

            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE:", e.message.toString() )
            return false
        }
    }
    //Funcion que obtengo todas las mascotas de mi BD
    fun obtenerMascotas() : ArrayList<Mascota>
    {
        val db=this.readableDatabase
        val listaMascotas: ArrayList<Mascota> = ArrayList<Mascota>()
        val query ="SELECT * FROM "+ TABLE_NAME


        val cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst()){

            do{
                val id=cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val causaDeAtencion = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA_DE_ATENCION))
                val causaDeRevision = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA_DE_REVISION))
                val medicamentos = cursor.getString(cursor.getColumnIndex(COLUMN_MEDICAMENTOS))
                val nombreMedico = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_MEDICO))
                val atendido= cursor.getString(cursor.getColumnIndex(COLUMN_ATENDIDO))


                listaMascotas.add(Mascota(id,nombre,tipo,raza,edad,causaDeAtencion,causaDeRevision,medicamentos,nombreMedico,atendido))
            }while (cursor.moveToNext())
        }
        return listaMascotas
    }
    //Modifico la mascota
    fun modificarMascota(id:Int,causaDeRevision:String,medicamentos:String,nombreMedico:String,atendido:String ) {
        try{
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_CAUSA_DE_REVISION,causaDeRevision)
            values.put(COLUMN_MEDICAMENTOS,medicamentos)
            values.put(COLUMN_NOMBRE_MEDICO,nombreMedico)
            values.put(COLUMN_ATENDIDO,atendido)

            val whereclause = "$COLUMN_ID=?"
            val whereargs = arrayOf(id.toString())

            db.update(TABLE_NAME,values,whereclause,whereargs)
        }catch (e:Exception)
        {
            Log.e("Error al modificar",e.message.toString())
        }
    }
    //Elimino mascota
    fun eliminarMascota():Boolean{
        var b :Boolean = false
        try{
            val db = this.writableDatabase

            db.delete(TABLE_NAME,null,null)
            b = true
        }catch (e:Exception)
        {
            Log.e("Error al borrar",e.message.toString())
            b=false
        }
        return b
    }
    //Obtengo mascotas segun la variable ATENDIDO, del objeto mascota
    fun obtenerMascotasParaAtender() : ArrayList<Mascota>
    {
        val db=this.readableDatabase
        val listaMascotas: ArrayList<Mascota> = ArrayList<Mascota>()
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ATENDIDO + " = " + " ' NO '"

        val cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst()){
            do{
                val id=cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val causaDeAtencion = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA_DE_ATENCION))
                val causaDeRevision = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA_DE_REVISION))
                val medicamentos = cursor.getString(cursor.getColumnIndex(COLUMN_MEDICAMENTOS))
                val nombreMedico = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_MEDICO))
                val atendido = cursor.getString(cursor.getColumnIndex(COLUMN_ATENDIDO))

                listaMascotas.add(Mascota(id,nombre,tipo,raza,edad,causaDeAtencion,causaDeRevision,medicamentos,nombreMedico,atendido))
            }while (cursor.moveToNext())
        }
        return listaMascotas
    }

    //Obtengo id para permitir seguir atendiendo mascotas, ya que cuando termina el dia, reseteo la tabla.
    fun obtenerID() : Boolean
    {
            val b:Boolean=false
            try {
                val db=this.readableDatabase
                val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + 20

                val cursor = db.rawQuery(query,null)

                if(cursor.moveToFirst()){
                    do{
                        val id=cursor.getInt(cursor.getColumnIndex(COLUMN_ID))

                    }while (cursor.moveToNext())
                    return true
                }
            }catch (e:Exception)
            {
                return false
            }
            return b
    }

    //Obtener nombre de medico, para poder comparar con los nombres ingresados y no dejar de atender mas de 4 animales por medico.
    fun obtenerNombreMedicos(nombre:String) : Int {
        val db=this.readableDatabase
        val listaMedicos: ArrayList<Mascota> = ArrayList<Mascota>()
        val query =  "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NOMBRE_MEDICO = '$nombre'"
        val cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst()){

            do{
                val id=cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nombreMedico = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_MEDICO))

                listaMedicos.add(Mascota(id,nombre,"","",0,"","","",nombreMedico,""))
            }while (cursor.moveToNext())
            return listaMedicos.size
        }
        return listaMedicos.size
    }
}



