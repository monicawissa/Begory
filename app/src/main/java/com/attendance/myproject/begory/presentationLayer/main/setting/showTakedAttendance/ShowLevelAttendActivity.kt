package com.attendance.myproject.begory.presentationLayer.main.setting.showTakedAttendance

import android.app.DatePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attendance.myproject.begory.R
import com.attendance.myproject.begory.data.Models.Level
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.attendance.myproject.begory.data.source.local.prefs.AppPreferencesHelper
import com.attendance.myproject.begory.databinding.ActivityShowLevelAttendanceBinding
import com.attendance.myproject.begory.presentationLayer.main.setting.BaseActivity1
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class ShowLevelAttendActivity : BaseActivity1() , CompoundButton.OnCheckedChangeListener{
    private lateinit var binding: ActivityShowLevelAttendanceBinding
    private val mLevelsList: ArrayList<Level> = ArrayList<Level>()
    private lateinit var  list :List<Int>

    private  val showLevelAttendViewModel: ShowLevelAttendViewModel by viewModels()
    var recyclerViewAdapter: ShowLevelAttendanceAdapter? = null
    lateinit var  settingType:String

    private var myCalendar: Calendar? = null


    override val layoutId: Int
        get() = R.layout.activity_show_level_attendance
    override fun initializeView() {
    }
    private fun fillMenu(): List<Int>? {
        val mItems = ArrayList<Int>()
        mItems.clear()
        val v= AppPreferencesHelper (applicationContext)
        val user=v.getUser()
        if((user!!.subAdminLevel.toString()).contains((FirebaseFilterType.LevelFilterType.College).toString())||
                (user!!.adminLevel.toString()).contains((FirebaseFilterType.LevelFilterType.College).toString()))
            mItems.add(R.string.lev_college)
        if((user!!.subAdminLevel.toString()).contains((FirebaseFilterType.LevelFilterType.Grad).toString())||
                (user!!.adminLevel.toString()).contains((FirebaseFilterType.LevelFilterType.Grad).toString()))
            mItems.add(R.string.lev_Grad)
        if((user!!.subAdminLevel.toString()).contains((FirebaseFilterType.LevelFilterType.Augustine).toString())||
                (user!!.adminLevel.toString()).contains((FirebaseFilterType.LevelFilterType.Augustine).toString()))
            mItems.add(R.string.lev_Augustine)
        return mItems.toList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(ContentValues.TAG, "showMessage: create UpdateBlaBla")
        binding = DataBindingUtil.setContentView(this, layoutId)
//        settingType= intent.getIntExtra(this.getString(R.string.settingType),0).toString()
        binding.showLevelAttendViewModel=showLevelAttendViewModel
        binding.lifecycleOwner = this
        list=fillMenu()!!
        // TODO: change id
        (binding.rvAttendance as (RecyclerView)).setHasFixedSize(true)
        //TODO: Choose your layout
        //GridLayout
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(this, 1)
        binding.rvAttendance.layoutManager = gridLayoutManager
        binding.cbTerm1.setOnCheckedChangeListener(this)
        binding.cbTerm2.setOnCheckedChangeListener(this)

        initLevelsSpinner()
        subscribeToNavigationChanges(showLevelAttendViewModel)
    }
    private fun initRecyclerView( ) {
        //if()
        recyclerViewAdapter = ShowLevelAttendanceAdapter(this, showLevelAttendViewModel.mListLast, binding.cbTerm1, binding.cbTerm2)
        binding.rvAttendance.adapter = recyclerViewAdapter
        recyclerViewAdapter!!.notifyDataSetChanged()
        binding.rvAttendance.smoothScrollToPosition(0)
    }
    private fun initLevelsSpinner() {
        mLevelsList.clear()
        var level = Level(-1, getString(R.string.choose_level))
        mLevelsList.add(level)
        for(i in list){
            var level = Level(i, getString(i))
            mLevelsList.add(level)
        }
        val dataAdapter: ArrayAdapter<Level> = ArrayAdapter<Level>(this,
                android.R.layout.simple_spinner_item, mLevelsList)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spLevels.adapter = dataAdapter
    }
    override fun onCheckedChanged(compoundButton: CompoundButton?, b: Boolean) {
        initRecyclerView()
    }

//    override fun openMainActivity() {
//        UiManager.startActivity(this@UpdateBlaBlaActivity, MainActivity::class.java)
//        finish()
//    }
    private fun subscribeToNavigationChanges(viewModelShow: ShowLevelAttendViewModel) {

        // The activity observes the navigation commands in the ViewModel
        val activity = this@ShowLevelAttendActivity
        viewModelShow.run {
            ishideKeyboard.observe(activity,
                    Observer {
                        if (it == true) hide_Keyboard()
                    })
            mStudentAttendancesList.observe(activity, Observer { if (it == true) initRecyclerView() })
            snackbarMessage.observe(activity, Observer { showMessage(getString(it.getContentIfNotHandled()!!)) })
            snackbarMessage2.observe(activity, Observer { showMessage(it) })
        }
    }
}