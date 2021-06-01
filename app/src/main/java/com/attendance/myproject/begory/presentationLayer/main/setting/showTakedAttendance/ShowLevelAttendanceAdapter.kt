package com.attendance.myproject.begory.presentationLayer.main.setting.showTakedAttendance

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.attendance.myproject.begory.R
import com.attendance.myproject.begory.data.Models.StudentWithTotalAttendanceCount
import java.util.*

class ShowLevelAttendanceAdapter(context: Activity, menuList: ArrayList<StudentWithTotalAttendanceCount>?,
                                 mTerm1CheckBox: CheckBox, mTerm2CheckBox: CheckBox) : RecyclerView.Adapter<ShowLevelAttendanceAdapter.MyViewHolder?>() {
    private val mMenuList: ArrayList<StudentWithTotalAttendanceCount> ? = menuList
    private val mContext: Activity = context
//    private val mTerm1CheckBox: CheckBox? = mTerm1CheckBox
//    private  var mTerm2CheckBox:CheckBox? = mTerm2CheckBox
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_attendance_count, parent, false) as View
        return MyViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position % 2 == 0) holder.itemView.setBackgroundColor(Color.parseColor("#aacdcdcd"))
        else holder.itemView.setBackgroundColor(Color.parseColor("#aaffffff"))
        var attendance: StudentWithTotalAttendanceCount = mMenuList!![position]
        var totalKodas = 0
        var totalHesa = 0
        var attendanceTotalKodas = 0
        var attendanceTotalHesa = 0
        var totalA3traf = 0
        var totalTnawel = 0
        var attendanceTotalA3traf = 0
        var attendanceTotalTnawel = 0
//        if (mTerm1CheckBox!!.isChecked) {
            totalHesa += attendance.hesaTotalCountTerm1
            totalKodas += attendance.kodasTotalCountTerm1
            attendanceTotalHesa += attendance.hesaAttendanceTotalCountTerm1
            attendanceTotalKodas += attendance.kodasAttendanceTotalCountTerm1
            totalA3traf += attendance.a3trafTotalCountTerm1
            totalTnawel += attendance.tnawelTotalCountTerm1
            attendanceTotalA3traf += attendance.a3trafAttendanceTotalCountTerm1
            attendanceTotalTnawel += attendance.tnawelAttendanceTotalCountTerm1
//        }
//        if (mTerm2CheckBox!!.isChecked) {
            totalHesa += attendance.hesaTotalCountTerm2
            totalKodas += attendance.kodasTotalCountTerm2
            attendanceTotalHesa += attendance.hesaAttendanceTotalCountTerm2
            attendanceTotalKodas += attendance.kodasAttendanceTotalCountTerm2
            totalA3traf += attendance.a3trafTotalCountTerm2
            totalTnawel += attendance.tnawelTotalCountTerm2
            attendanceTotalA3traf += attendance.a3trafAttendanceTotalCountTerm2
            attendanceTotalTnawel += attendance.tnawelAttendanceTotalCountTerm2
//        }
        holder.mTextView.setText(attendance.name)
        holder.mHesaTextView.text = "$totalHesa/$attendanceTotalHesa"
        holder.mKodasTextView.text = "$totalKodas/$attendanceTotalKodas"
        holder.ma3trafTextView.text = "$totalA3traf/$attendanceTotalA3traf"
        holder.mtnawelTextView.text = "$totalTnawel/$attendanceTotalTnawel"


    }
    override fun getItemCount(): Int =
        mMenuList!!.size

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var mTextView: TextView = v.findViewById(R.id.tv_name)
        var mKodasTextView: TextView = v.findViewById(R.id.tv_kodas)
        var mHesaTextView: TextView = v.findViewById(R.id.tv_hesa)
        var ma3trafTextView: TextView = v.findViewById(R.id.tv_a3traf)
        var mtnawelTextView: TextView = v.findViewById(R.id.tv_tnawel)
    }
}


