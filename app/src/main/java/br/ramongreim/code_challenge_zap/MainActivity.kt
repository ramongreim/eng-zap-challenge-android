package br.ramongreim.code_challenge_zap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import br.ramongreim.code_challenge_zap.imovel.ImoveisActivity
import br.ramongreim.code_challenge_zap.model.Imovel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    lateinit var btnVivaReal: ImageButton
    lateinit var btnZap: ImageButton
    private lateinit var loadingIndicator: ProgressBar
    lateinit var interactor: Interactor


    val vivaReal = 0
    val zap = 1
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Configurator.INSTANCE.configureActivity(this)
        setupViews()
    }
    private fun  setupViews() {
        btnVivaReal = btVivaReal
        btnZap = btZap
        loadingIndicator = progressBar

        btnVivaReal.setOnClickListener {
            loadingIndicator.visibility = View.VISIBLE
            interactor.getData(vivaReal)
        }

        btnZap.setOnClickListener {
            loadingIndicator.visibility = View.VISIBLE
            interactor.getData(zap)
        }
    }

    fun loadPage(imoveis: List<Imovel>, portal : Int) {
        loadingIndicator.visibility = View.GONE
        val intent: Intent = Intent(this, ImoveisActivity::class.java)
        intent.putExtra("imoveis", imoveis as Serializable)
        if (portal == 0)
            intent.putExtra("portal", "vivaReal")
        else
            intent.putExtra("portal", "zap")
        startActivity(intent)
    }

}