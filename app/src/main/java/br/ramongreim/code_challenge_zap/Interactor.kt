package br.ramongreim.code_challenge_zap

import br.ramongreim.code_challenge_zap.model.Imovel
import br.ramongreim.code_challenge_zap.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor {
    lateinit var presenter: Presenter

    fun getData(code: Int) {

        val call = APIClient().imovelService().list()

        call.enqueue(object : Callback<List<Imovel>?> {

            override fun onResponse(call: Call<List<Imovel>?>?, response: Response<List<Imovel>?>?) {

                response?.body()?.let {
                    val imoveis: List<Imovel> = it
                    presenter.setUpPage(imoveis , code)

                }
            }
            override fun onFailure(call: Call<List<Imovel>?>?, t: Throwable?) {
            }
        })
    }
}