package com.attendance.myproject.begory.presentationLayer.main.setting.showTakedAttendance
import android.view.View
import android.widget.AdapterView
import androidx.annotation.StringRes
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.attendance.myproject.begory.Utilities.ggle.Event
import com.attendance.myproject.begory.data.Models.Level
import com.attendance.myproject.begory.data.Models.StudentWithTotalAttendanceCount
import com.attendance.myproject.begory.data.Models.User
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.attendance.myproject.begory.data.source.AppRepository
import com.attendance.myproject.begory.data.source.remote.IRemoteDataSource


class ShowLevelAttendViewModel  @ViewModelInject constructor(private val appRepository: AppRepository,
                                                             @Assisted private val savedStateHandle: SavedStateHandle
) :
        ViewModel() ,LifecycleObserver {
    var mStudentList: ArrayList<User> = ArrayList<User>()
    var mListLast: ArrayList<StudentWithTotalAttendanceCount> = ArrayList()
    var mSelectedDate:String=""
    var mSelectedTermId:Int=0
    var studentLevel: FirebaseFilterType.LevelFilterType? = null
    private val _mStudentAttendancesList= MutableLiveData<Boolean>()
    val mStudentAttendancesList: LiveData<Boolean>
        get() = _mStudentAttendancesList

    var mTitleTV = savedStateHandle.getLiveData<String>("settingType").value
    val spinnerListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            var levelId= FirebaseFilterType.fbConvert(((parent!!.getItemAtPosition(position)) as Level).levelId!!)
            if(levelId!=FirebaseFilterType.LevelFilterType.no){
                studentLevel = levelId
                mListLast.clear()
                mStudentList.clear()
                _mStudentAttendancesList.value=true
                getStudentList()
            }
        }
    }
//    //Btn check Available
//    private val _isCheckBtnAvailable = MutableLiveData<Boolean>()
//    val isCheckBtnAvailable: LiveData<Boolean>
//        get() = _isCheckBtnAvailable
    //progressbar
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading
    private val _ishideKeyboard = MutableLiveData<Boolean>()
    val ishideKeyboard: LiveData<Boolean>
        get() = _ishideKeyboard

    private val msnackbarText = MutableLiveData<Event<Int>>()
    val snackbarMessage: LiveData<Event<Int>>
        get() = msnackbarText
    private val msnackbarText2 = MutableLiveData<String>()
    val snackbarMessage2: LiveData<String>
        get() = msnackbarText2

    private fun showSnackbarMessage(@StringRes message: Int) {
        msnackbarText.value = Event(message)
    }

    private fun showSnackbarMessage2(message: String) {
        msnackbarText2.value = message
    }

    init {
        _ishideKeyboard.value = false
       // _isStudentAvailable.value = (mTitleTV.equals("تعديل مخدوم"))
//        _isCheckBtnAvailable.value=true
        _mStudentAttendancesList.value=false
    }

    fun getStudentList() {
        _dataLoading.value = true
//        _isCheckBtnAvailable.value=false
        appRepository.filterLevel(studentLevel!!, object : IRemoteDataSource.UsersCallback {
            override fun onResponse(users: List<User>) {
                mStudentList.addAll(users)
                manageStudentWithAttendance()
                _dataLoading.value = false
//                _isCheckBtnAvailable.value = true

            }

            override fun onDataNotAvailable(message: Int?) {
                showSnackbarMessage(message!!)
                _dataLoading.value = false
//                _isCheckBtnAvailable.value = true

            }
        })

    }

    var mList: ArrayList<StudentWithTotalAttendanceCount> = ArrayList()
    private fun manageStudentWithAttendance() {
        mList.clear()
        for (i in mStudentList.indices) {
            var studentWithAttendance = StudentWithTotalAttendanceCount()
            studentWithAttendance.id=(mStudentList[i].id)
            studentWithAttendance.name=(mStudentList[i].name)

            if (mStudentList[i].listOfAttendence == null || mStudentList[i].listOfAttendence!!.isEmpty()) {
                studentWithAttendance.hesaAttendanceTotalCountTerm1=0
                studentWithAttendance.hesaAttendanceTotalCountTerm2=0
                studentWithAttendance.kodasAttendanceTotalCountTerm1=0
                studentWithAttendance.kodasAttendanceTotalCountTerm2=0
                studentWithAttendance.a3trafAttendanceTotalCountTerm1=0
                studentWithAttendance.a3trafAttendanceTotalCountTerm2=0
                studentWithAttendance.tnawelAttendanceTotalCountTerm1=0
                studentWithAttendance.tnawelAttendanceTotalCountTerm2=0
                studentWithAttendance.hesaTotalCountTerm1=0
                studentWithAttendance.hesaTotalCountTerm2=0
                studentWithAttendance.kodasTotalCountTerm1=0
                studentWithAttendance.kodasTotalCountTerm2=0
                studentWithAttendance.a3trafTotalCountTerm1=0
                studentWithAttendance.a3trafTotalCountTerm2=0
                studentWithAttendance.tnawelTotalCountTerm1=0
                studentWithAttendance.tnawelTotalCountTerm2=0
            }
            else {
                var found = false
                for (n in mStudentList[i].listOfAttendence!!) {
                    if (n.value.termId=="1") {
                        studentWithAttendance.hesaTotalCountTerm1++
                        studentWithAttendance.kodasTotalCountTerm1++
                        studentWithAttendance.tnawelTotalCountTerm1++
                        studentWithAttendance.a3trafTotalCountTerm1++

                        if (n.value.iskodas) studentWithAttendance.kodasAttendanceTotalCountTerm1++

                        if (n.value.isAttend) studentWithAttendance.hesaAttendanceTotalCountTerm1++
                        if (n.value.isTnawel) studentWithAttendance.tnawelAttendanceTotalCountTerm1++
                        if (n.value.isA3traf) studentWithAttendance.a3trafAttendanceTotalCountTerm1++


                    }
                    if (n.value.termId=="2") {
                        studentWithAttendance.hesaTotalCountTerm2++
                        studentWithAttendance.kodasTotalCountTerm2++
                        studentWithAttendance.tnawelTotalCountTerm2++
                        studentWithAttendance.a3trafTotalCountTerm2++

                        if (n.value.iskodas) studentWithAttendance.kodasAttendanceTotalCountTerm2++

                        if (n.value.isAttend) studentWithAttendance.hesaAttendanceTotalCountTerm2++
                        if (n.value.isTnawel) studentWithAttendance.tnawelAttendanceTotalCountTerm2++
                        if (n.value.isA3traf) studentWithAttendance.a3trafAttendanceTotalCountTerm2++
                    }
                }
            }

            mList.add(studentWithAttendance)
        }
        mListLast.clear()
        mListLast.addAll(mList)
        _mStudentAttendancesList.value=true
    }


//    fun btnadd(){
//        _ishideKeyboard.value = true
//        _dataLoading.value = true
//        _isCheckBtnAvailable.value = false
//        appRepository.updateAttendance(mListLast, object : IRemoteDataSource.MessageCallback {
//            override fun onResponse(message: Int?) {
//                showSnackbarMessage(message!!)
//                _isCheckBtnAvailable.value = true
//                _dataLoading.value = false
//            }
//
//            override fun onDataNotAvailable(message: Int?) {
//                showSnackbarMessage(message!!)
//                _isCheckBtnAvailable.value = true
//                _dataLoading.value = false
//            }
//
//        })
//
//    }


}
