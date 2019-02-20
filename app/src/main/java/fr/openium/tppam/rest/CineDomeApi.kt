package fr.openium.tppam.rest

import fr.openium.tppam.model.Info
import retrofit2.Call
import retrofit2.http.GET

interface CineDomeApi {
    @GET("pam/cine.json")
    fun getInfo() : Call<Info>
}