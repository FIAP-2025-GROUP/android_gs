package br.com.fiap.journey.screens

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

// 1. Modelo de dados ATUALIZADO para o Journey
// Agora enviamos não só a mensagem, mas o tópico e a escolaridade
data class ChatRequest(
    val msg: String,
    val topic: String,
    val user_education: Int
)

// 2. Interface da API
interface ChatApiService {
    @POST("chat")
    suspend fun sendMessage(@Body request: ChatRequest): String
}

// 3. Objeto Singleton com Timeout Aumentado
object RetrofitClient {
    // Endereço especial do emulador para o localhost do PC
    private const val BASE_URL = "http://10.0.2.2:5000/"

    // CONFIGURAÇÃO DO TIMEOUT
    // Aumentamos para 90 segundos para dar tempo da OpenAI pensar
    private val client = OkHttpClient.Builder()
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)    // Esse é o mais importante (espera da resposta)
        .writeTimeout(90, TimeUnit.SECONDS)
        .build()

    val instance: ChatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // <--- Injetamos o cliente configurado aqui
            .addConverterFactory(ScalarsConverterFactory.create()) // Para ler String pura
            .addConverterFactory(GsonConverterFactory.create())    // Para enviar JSON
            .build()
            .create(ChatApiService::class.java)
    }
}