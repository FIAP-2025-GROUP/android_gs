package br.com.fiap.journey.repository

import android.content.Context
import br.com.fiap.journey.database.dao.UsuarioDb
import br.com.fiap.journey.model.Usuario

class UsuarioRepository(context: Context) {

    var db = UsuarioDb.getDatabase(context).usuarioDao()

    fun salvar(usuario: Usuario): Long {
        return db.salvar(usuario)
    }

    fun atualizar(usuario:Usuario): Int{
        return db.atualizar(usuario)
    }

    fun buscarPorEmail(email: String): Usuario? {
        return db.buscarPorEmail(email)
    }

    fun buscarPorId(id: Long): Usuario? {
        return db.buscarPorId(id = id)
    }
}


