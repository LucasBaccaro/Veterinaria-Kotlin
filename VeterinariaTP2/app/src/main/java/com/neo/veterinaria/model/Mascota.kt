package com.neo.veterinaria.model

import java.io.Serializable

//Clase mascota serializada
data class Mascota (var id:Int=0, val nombre:String, var tipo:String, var raza:String, var edad:Int, var causaDeAtencion:String,
                    var causaDeRevision:String, var medicamentos:String, var nombreMedico:String, var atendido: String) : Serializable