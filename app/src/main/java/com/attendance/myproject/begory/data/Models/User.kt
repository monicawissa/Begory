package com.attendance.myproject.begory.data.Models

import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.google.gson.annotations.SerializedName

data class User (
        @SerializedName("id")
    var id: String? = "",
        @SerializedName("name")
    var name: String? = "",
        @SerializedName("mobile")
    var mobile: String? = "",
        @SerializedName("password")
    var password: String? = "",
    //login
        @SerializedName("mobile_password")
    var mobile_password: String? = "",
        @SerializedName("notes")
    var notes: String? = "",
        @SerializedName("mobile2")
    var mobile2: String? = "",
        @SerializedName("address")
    var address: String? = "",
        @SerializedName("adminLevel")
    var adminLevel: FirebaseFilterType.LevelFilterType? = FirebaseFilterType.LevelFilterType.No,
        @SerializedName("subAdminLevel")
    var subAdminLevel: FirebaseFilterType.LevelFilterType? = FirebaseFilterType.LevelFilterType.No,
        @SerializedName("studentLevel")
    var studentLevel: FirebaseFilterType.LevelFilterType? = FirebaseFilterType.LevelFilterType.No,

        @SerializedName("balanceEqlomat")
    var balanceEqlomat: Double? = 0.0,
        @SerializedName("balanceBegory")
    var balanceBegory: Double? = 0.0,
        @SerializedName("listOfAttendence")

    var listOfAttendence: List<Attendance>? = null,
        @SerializedName("firstTime_ToLogin")
    var firstTime_ToLogin:Boolean=true)

