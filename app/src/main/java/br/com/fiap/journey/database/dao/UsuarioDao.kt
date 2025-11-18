package br.com.fiap.journey.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.journey.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    fun salvar(usuario: Usuario): Long

    @Query("SELECT * FROM tbl_usuario WHERE email = :email")
    fun buscarPorEmail(email: String): Usuario?

}