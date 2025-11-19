package br.com.fiap.journey.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.journey.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    fun salvar(usuario: Usuario): Long

    @Update
    fun atualizar(usuario: Usuario): Int

    @Query("SELECT * FROM tbl_usuario WHERE id = :id")
    fun buscarPorId(id: Long): Usuario

    @Query("SELECT * FROM tbl_usuario WHERE email = :email")
    fun buscarPorEmail(email: String): Usuario?

}