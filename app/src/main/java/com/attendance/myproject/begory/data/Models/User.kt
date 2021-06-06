package com.attendance.myproject.begory.data.Models

import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
        @SerializedName("id")
    var id: String? = "",
        @SerializedName("name")
    var name: String? = "",
        @SerializedName("mobile")
    var mobile: String? = "",
        @SerializedName("mobile2")
    var mobile2: String? = "",
        @SerializedName("password")
    var password: String? = "",
        @SerializedName("isShamas")
    var isShamas: Boolean? = false,

        //login
        @SerializedName("mobile_password")
    var mobile_password: String? = "",
        @SerializedName("notes")
    var notes: String? = "",
        @SerializedName("address")
    var address: String? = "",
        @SerializedName("adminLevel")
    var adminLevel: String?="",
        @SerializedName("subAdminLevel")
    var subAdminLevel: String?="",
        @SerializedName("studentLevel")
    var studentLevel: FirebaseFilterType.LevelFilterType?=null,
        @SerializedName("price")
    var price: Double? = 0.0,
        @SerializedName("realPrice")
    var realPrice: Double? = 0.0,
        @SerializedName("listOfAttendence")
    var listOfAttendence: Map<String,Attendance>? =null,
        @SerializedName("firstTime_ToLogin")
    var firstTime_ToLogin:Boolean=true,
        @SerializedName("selectedGifts")
        var selectedGifts: ArrayList<Gift>? = null)