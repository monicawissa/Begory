package com.attendance.myproject.begory.presentationLayer.main.profile
import android.content.ContentValues
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.annotation.StringRes
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.androidbuts.multispinnerfilter.KeyPairBoolData
import com.androidbuts.multispinnerfilter.MultiSpinnerListener
import com.attendance.myproject.begory.R
import com.attendance.myproject.begory.Utilities.ggle.Event
import com.attendance.myproject.begory.data.Models.Level
import com.attendance.myproject.begory.data.Models.User
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.attendance.myproject.begory.data.source.AppRepository
import com.attendance.myproject.begory.data.source.remote.IRemoteDataSource


class ProfileViewModel  @ViewModelInject constructor(private val appRepository: AppRepository,
                                                          @Assisted private val savedStateHandle: SavedStateHandle
) :
        ViewModel() ,LifecycleObserver {
    var usertmp:User=User()
    val name = MutableLiveData<String>()
    //var name: String = "hi"
    val mobile = MutableLiveData<String>()
    val mobile2 = MutableLiveData<String>()
    val password= MutableLiveData<String>()
    val address= MutableLiveData<String>()
    val balanceReal= MutableLiveData<String>()
    val balanceAfterBook= MutableLiveData<String>()
    val studentLevel= MutableLiveData<String>()
    //var mTitleTV = savedStateHandle.getLiveData<String>("settingType").value
    var selectedData:String=""
//    private val _name = MutableLiveData("hi")
//    val name: LiveData<String> = _name

//    //set Data
//    private val _isDataAvailable = MutableLiveData<User>()
//    val isDataAvailable: LiveData<User>
//        get() = _isDataAvailable

    //clickable of button
    private val _isBtnAvailable = MutableLiveData<Boolean>()
    val isBtnAvailable: LiveData<Boolean>
        get() = _isBtnAvailable
    //to open main
    private val _isopenLogin = MutableLiveData<Boolean>()
    val isopenLogin: LiveData<Boolean>
        get() = _isopenLogin
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

    init {
        _ishideKeyboard.value = false
        _isBtnAvailable.value = true
        //_isDataAvailable.value=User()
        getUser()
//        address="test"
    }

    private fun getUser() {
        appRepository.getUserLastUpdate(object:IRemoteDataSource.LoginCallback{
            override fun onResponse(user: User) {
                //name="yes"
                setuser(user)
                //_isDataAvailable.value=user
            }
            override fun onDataNotAvailable(message: Int?) {
                //name="No"
            }
        })
    }

    private fun setuser(user: User) {
        usertmp=user
        name.value=usertmp.name!!
        mobile.value=usertmp.mobile!!
        mobile2.value=usertmp.mobile2!!
        balanceReal.value=usertmp.realPrice.toString()
        balanceAfterBook.value=usertmp.price.toString()
        password.value=usertmp.password!!
        address.value=usertmp.address!!
        if(usertmp.studentLevel==null)
            studentLevel.value=""
        else studentLevel.value=usertmp.studentLevel.toString()
        //_isDataAvailable.value=true
    }

    private fun showSnackbarMessage(@StringRes message: Int){
        msnackbarText.value = Event(message)
    }

    private fun showSnackbarMessage2(message: String) {
        msnackbarText2.value = message
    }


    private fun isDataValid(): Boolean {
        // validate
        Log.d(ContentValues.TAG, "showMessage: isDataValid")
        if (password.value==null||password.value!!.isEmpty()) {
            Log.d(ContentValues.TAG, "showMessage: PasswordValid")
            showSnackbarMessage(R.string.fill__password)
            return false
        }
        return true
    }


    fun update() {
        _dataLoading.value = true
        _isBtnAvailable.value=false
            if (isDataValid()) {
                usertmp.name=name.value
                usertmp.mobile=mobile.value
                usertmp.mobile2=mobile2.value
                usertmp.password=password.value
                usertmp.mobile_password="${mobile.value} ${usertmp.password}"
                usertmp.address=address.value
                appRepository.updateStudent(usertmp, object : IRemoteDataSource.MessageCallback {
                    override fun onResponse(message: Int?) {
                        showSnackbarMessage(message!!)
                        _dataLoading.value = false
                        _isBtnAvailable.value = true
                    }

                    override fun onDataNotAvailable(message: Int?) {
                        showSnackbarMessage(message!!)
                        _dataLoading.value = false
                        _isBtnAvailable.value = true
                    }

                })
            } else _dataLoading.value = false

    }
    fun logout() {
        _dataLoading.value = true
        _isBtnAvailable.value=false

            appRepository.setUserAsLoggedOut()
            _isopenLogin.value=true
        _dataLoading.value=false
    }
}
