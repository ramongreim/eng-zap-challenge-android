package br.ramongreim.code_challenge_zap

import br.ramongreim.code_challenge_zap.model.Imovel

class Presenter {

    lateinit var mainActivity: MainActivity
    private lateinit var filteredList: ArrayList<Imovel>
    private lateinit var vivaRealList: ArrayList<Imovel>
    private lateinit var zapList: ArrayList<Imovel>


    fun boudingBox(lat: Double, lon: Double): Boolean {
        return lat >= -23.568704 && lat <= -23.546686 && lon >= -46.693419 && lon <= -46.641146
    }

    fun setUpPage(imoveis: List<Imovel>, code: Int) {
        solveGroup(imoveis)
        if (code == 0)
            mainActivity.loadPage(vivaRealList.toList() , code)
        else
            mainActivity.loadPage(zapList.toList() , code)
    }

    fun solveGroup(imoveis: List<Imovel>) {
        vivaRealList = ArrayList()
        zapList = ArrayList()
        filteredList = ArrayList()

        for (x in imoveis) {
            var vendaMinimaZap = 600000.0
            var aluguelMaxVivaReal= 4000.0
            var preco = x.pricingInfos.price.toDouble()
            var latitude = x.address.geoLocation.location.lat
            var longitude = x.address.geoLocation.location.lon
            var venda = x.pricingInfos.businessType == "SALE"

            if(latitude != 0.0 && longitude != 0.0){
                if(!venda){//se for aluguel
                    if (boudingBox(latitude, longitude))
                        aluguelMaxVivaReal = aluguelMaxVivaReal + (0.5 * aluguelMaxVivaReal)
                    if(x.pricingInfos.rentalTotalPrice.toDouble() >= 3500.0) {
                            zapList.add(x)
                    }
                    if(x.pricingInfos.rentalTotalPrice.toDouble() <=  aluguelMaxVivaReal){
                        vivaRealList.add(x)
                        if(x.pricingInfos.monthlyCondoFee.toDouble() <= (0.3 * preco)){
                            vivaRealList.add(x)
                        }
                    }
                }
                else{//se for venda
                    if (boudingBox(latitude, longitude))
                       vendaMinimaZap = vendaMinimaZap - (0.1 * vendaMinimaZap)
                    if ((preco / x.usableAreas > 3500.0) && preco >= vendaMinimaZap)
                        zapList.add(x)
                    //ok
                    if (preco <= 700000.0)
                       vivaRealList.add(x)
                }
            }
        }
    }
}