package fr.openium.tppam.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://etudiants.openium.fr/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api: CineDomeApi by lazy { retrofit.create(CineDomeApi::class.java) }
}