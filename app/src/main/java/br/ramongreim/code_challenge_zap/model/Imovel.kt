package br.ramongreim.code_challenge_zap.model

import java.io.Serializable

data class Imovel(
    var usableAreas: Int,
    var listingType: String,
    var createdAt: String,
    var listingStatus: String,
    var id: String,
    var parkingSpaces: Int,
    var updatedAt: String,
    var owner: Boolean,
    var images: ArrayList<String>,
    var address: Address,
    var bathrooms: Int,
    var bedrooms: Int,
    var pricingInfos: PricingInfos
) : Serializable

data class PricingInfos(
    var yearlyIptu: String,
    var price: String,
    var businessType: String,
    var monthlyCondoFee: String,

    var period: String,
    var rentalTotalPrice: String

) : Serializable

data class Address(
    var city: String,
    var neighborhood: String,
    var geoLocation: Geolocation
) : Serializable

data class Geolocation(
    var precision: String,
    var location: Location
) : Serializable

data class Location(
    var lon: Double,
    var lat: Double
) : Serializable