package com.attendance.myproject.begory.data.Models

import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Gift (
        @SerializedName("id")
    var id: String? = "",
        @SerializedName("name")
    var name: String? = "",
        @SerializedName("imagePath")
    var imagePath: String? = "",
       @SerializedName("notes")
    var notes: String? = "",
        @SerializedName("price")
    var price: Double? = 0.0,
        @SerializedName("numberOfItem")
        var numberOfItem: Int? = 0,
        //available :no,buy,book
        @SerializedName("booked")
    var booked: Boolean?=true,
        @SerializedName("initbooked")
        var initbooked: Boolean?=false,
        @SerializedName("delivered")
        var delivered: Boolean?=false
)