package br.ramongreim.code_challenge_zap.imovel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.ramongreim.code_challenge_zap.R
import br.ramongreim.code_challenge_zap.model.Imovel
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_imovel.*
import kotlinx.android.synthetic.main.imovel_row.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class ImovelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imovel)

        setupViews()
    }

    private fun setupViews() {

        val imageList = ArrayList<SlideModel>()
        val imovel = intent.extras?.get("imovel") as Imovel
        val isForSale = imovel.pricingInfos.businessType.equals("SALE")

        imovel.images.forEach {
            Log.d("ramon", it)
            imageList.add(SlideModel(it))
        }

        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)

        imageSlider.stopSliding()
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        val ptBr = Locale("pt", "BR")
        val z: NumberFormat = NumberFormat.getCurrencyInstance(ptBr)


        tvValor.text =
            z.format(imovel.pricingInfos.price.toFloat())
        tvNumQuartos.text = imovel.bedrooms.toString()
        tvNumVagas.text = imovel.parkingSpaces.toString()
        tvNumBanheiros.text = imovel.bathrooms.toString()
        tvNumMetros.text = imovel.usableAreas.toString()

        if (isForSale)
            tvModalidade.text = this.getString(R.string.imovel_tipo_venda)
        else
            tvModalidade.text = this.getString(R.string.imovel_tipo_aluguel)

    }

}