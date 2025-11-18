package br.com.fiap.journey.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_usuario")
data class Usuario(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var nome: String = "",
    var email:String = "",
    var educationLevel:String = "",
    var areaDeTrabalho:String = "",
    var senha:String = "",

    
)