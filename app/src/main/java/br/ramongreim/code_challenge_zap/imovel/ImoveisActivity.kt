package br.ramongreim.code_challenge_zap.imovel

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.ramongreim.code_challenge_zap.R
import br.ramongreim.code_challenge_zap.adapter.ImovelAdapter
import br.ramongreim.code_challenge_zap.model.Imovel
import kotlinx.android.synthetic.main.activity_imoveis.*
import java.io.Serializable

class ImoveisActivity : AppCompatActivity(){

    lateinit var listaImoveis: List<Imovel>
    lateinit var recyclerImoveis: RecyclerView
    lateinit var btVerMais: Button

    var contador = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imoveis)

       if ( intent.getStringExtra("portal") == "zap"){
           imoveis.setBackgroundColor(Color.parseColor("#ff8c00"))
           ivLogo.setImageResource(R.drawable.logo_zap);
       }
       else{
           imoveis.setBackgroundColor(Color.parseColor("#1190cd"))
       }

        setupViews()
        populateRecycler()
    }


    private fun setupViews() {
        val imoveis = intent.extras?.get("imoveis") as List<Imovel>

        recyclerImoveis = rvImoveis

        btVerMais = btnVerMais

        listaImoveis = imoveis.take(contador)

        btVerMais.setOnClickListener {
            contador = contador + 20
            listaImoveis = imoveis.take(contador)
            populateRecycler()
        }
    }
    private fun populateRecycler() {
        rvImoveis.adapter = ImovelAdapter(listaImoveis, this, { partItem: Imovel -> onItemClick(partItem) })
    }

    private fun onItemClick(imovel: Imovel) {
        val intent = Intent(this, ImovelActivity::class.java)
        intent.putExtra("imovel", imovel as Serializable)
        startActivity(intent)
    }
}