package br.ramongreim.code_challenge_zap.network

import br.ramongreim.code_challenge_zap.model.Imovel
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("source-1.json")
    fun list(): Call<List<Imovel>>
}