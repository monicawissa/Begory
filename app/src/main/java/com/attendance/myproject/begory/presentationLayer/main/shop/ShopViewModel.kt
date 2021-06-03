package com.attendance.myproject.begory.presentationLayer.main.shop
import androidx.annotation.StringRes
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.attendance.myproject.begory.Utilities.ggle.Event
import com.attendance.myproject.begory.data.Models.Attendance
import com.attendance.myproject.begory.data.Models.Gift
import com.attendance.myproject.begory.data.Models.User
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.attendance.myproject.begory.data.source.AppRepository
import com.attendance.myproject.begory.data.source.remote.IRemoteDataSource


class ShopViewModel  @ViewModelInject constructor(private val appRepository: AppRepository,
                                                  @Assisted private val savedStateHandle: SavedStateHandle
) :
        ViewModel() ,LifecycleObserver {
    var mGiftList: ArrayList<Gift> = ArrayList()
    var mSelectedDate:String=""
    var studentLevel: FirebaseFilterType.LevelFilterType? = null
    private val _mGiftListListener= MutableLiveData<Boolean>()
    val mGiftListListener: LiveData<Boolean>
        get() = _mGiftListListener
    var mTitleTV = savedStateHandle.getLiveData<String>("settingType").value

//    //Btn check Available
    private val _isCheckBtnAvailable = MutableLiveData<Boolean>()
    val isCheckBtnAvailable: LiveData<Boolean>
        get() = _isCheckBtnAvailable
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
        getUser()
        _mGiftListListener.value=false
        getGiftsList()

    }
     var user: User?=null

    private fun getUser() {
        user=appRepository.getUser()
        getGiftsList()
    }
    fun getGiftsList() {
        _dataLoading.value = true
//        _isCheckBtnAvailable.value=false
        appRepository.filterGift(user!!.studentLevel!!, object : IRemoteDataSource.ShowGiftsCallback {
            override fun onResponse(gifts: List<Gift>) {
                mGiftList.addAll(gifts)
                _mGiftListListener.value=true
                _dataLoading.value = false
            }

            override fun onDataNotAvailable(message: Int?) {
                showSnackbarMessage(message!!)
                _dataLoading.value = false

            }
        })

    }
    fun update() {}
    fun btnadd(){
        _isCheckBtnAvailable.value = false
                _isCheckBtnAvailable.value = true
                _dataLoading.value = false
          

    }
}
