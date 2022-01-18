package br.ramongreim.code_challenge_zap

import br.ramongreim.code_challenge_zap.Interactor
import br.ramongreim.code_challenge_zap.MainActivity
import br.ramongreim.code_challenge_zap.Presenter

enum class Configurator {

    INSTANCE;

    fun configureActivity(mainActivity: MainActivity) {

        val presenter = Presenter()
        presenter.mainActivity = mainActivity

        val interactor = Interactor()
        interactor.presenter = presenter

        mainActivity.interactor = interactor
    }
}