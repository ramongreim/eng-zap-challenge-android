package br.ramongreim.code_challenge_zap.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.ramongreim.code_challenge_zap.R
import br.ramongreim.code_challenge_zap.model.Imovel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.imovel_row.view.*
import java.text.NumberFormat
import java.util.*

class ImovelAdapter(val items: List<Imovel>, val context: Context, val clickListener: (Imovel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImovelViewHolder {
        return ImovelViewHolder(LayoutInflater.from(context).inflate(R.layout.imovel_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImovelViewHolder).bind(items[position], clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ImovelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(imovel: Imovel, clickListener: (Imovel) -> Unit) {

            itemView.setOnClickListener { clickListener(imovel) }

            Picasso.get()
                .load(imovel.images.get(0))
                .into(itemView.ivImovel)
            val ptBr = Locale("pt", "BR")
            val z: NumberFormat = NumberFormat.getCurrencyInstance(ptBr)

            itemView.tvNumValorImovel.text =
                z.format( imovel.pricingInfos.price.toFloat())
            itemView.tvNumVagasImovel.text = imovel.parkingSpaces.toString()
            itemView.tvNumBanheirosImovel.text = imovel.bathrooms.toString()
            itemView.tvNumQuartosImovel.text = imovel.bedrooms.toString()
            itemView.tvNumMetrosImovel.text = imovel.usableAreas.toString()

            if (imovel.pricingInfos.businessType.equals("SALE")) {
                itemView.tvModalidadeImovel.text =
                    itemView.context.getString(R.string.imovel_tipo_venda)
            }
            else
                itemView.tvModalidadeImovel.text =
                    itemView.context.getString(R.string.imovel_tipo_aluguel)


        }
    }
}