package br.ramongreim.code_challenge_zap.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun imovelService(): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }
}