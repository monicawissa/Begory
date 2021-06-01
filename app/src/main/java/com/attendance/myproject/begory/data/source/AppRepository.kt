package com.attendance.myproject.begory.data.source
import com.attendance.myproject.begory.R
import com.attendance.myproject.begory.data.Models.Attendance
import com.attendance.myproject.begory.data.Models.Gift
import com.attendance.myproject.begory.data.Models.User
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.attendance.myproject.begory.data.source.local.prefs.IPreferencesHelper
import com.attendance.myproject.begory.data.source.remote.IRemoteDataSource
import javax.inject.Inject


/**
 * Concrete implementation to load tasks from the data sources into a cache.
 *
 *
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
class AppRepository @Inject constructor(private val mRemoteDataSource: IRemoteDataSource,private val mIPreferencesHelper: IPreferencesHelper)
    : BaseDataSource {

    override fun getUser():User? {
         return mIPreferencesHelper.getUser()
    }
    override fun getUserLastUpdate(callback: IRemoteDataSource.LoginCallback) {
        mRemoteDataSource.checkUserExist(mIPreferencesHelper.getUser()!!.mobile!!,object :IRemoteDataSource.LoginCallback{
            override fun onResponse(user: User) {
                callback.onResponse(user)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }
    override fun setUser(user: User) {
        mIPreferencesHelper.setUser(user)    }

    override fun setUserAsLoggedOut() {
        mIPreferencesHelper.setUserAsLoggedOut()
    }

    override fun addGift(gift: Gift, level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.addGift(gift,level,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }

        })    }

    override fun updateGift(gift: Gift, level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.updateGift(gift,level,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }

    override fun deleteGift(gift: Gift, level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.deleteGift(gift,level,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })    }

    override fun login(mobile: String, password: String, callback: IRemoteDataSource.LoginCallback) {
        mRemoteDataSource.login(mobile,password,object :IRemoteDataSource.LoginCallback{
            override fun onResponse(user: User) {
                mIPreferencesHelper.setUser(user)
                callback.onResponse(user)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }

        })
    }

    override fun registerStudent(user: User,
                                 callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.registerStudent(user,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }

        })
    }

    override fun registerSubAdmin(user: User, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.registerSubAdmin(user,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }

        })
    }
    override fun registerAdmin(user: User, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.registerAdmin(user,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }

        })
    }

    override fun checkUserExist(mobile: String, callback: IRemoteDataSource.LoginCallback) {
        mRemoteDataSource.checkUserExist(mobile,object :IRemoteDataSource.LoginCallback{
            override fun onResponse(user: User) {
                callback.onResponse(user)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }

    override fun updateStudent(user: User, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.updateStudent(user,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }

    override fun updateSubAdmin(user: User, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.updateSubAdmin(user,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }

    override fun updateAdmin(user: User, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.updateAdmin(user,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }

    override fun updatePassword(user: User, callback: IRemoteDataSource.LoginCallback) {

    }

    override fun filterLevel(level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.UsersCallback) {
        mRemoteDataSource.filterLevel(level,object :IRemoteDataSource.UsersCallback{
            override fun onResponse(user: List<User>) {
                callback.onResponse(user)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }

    override fun updateAttendance(listOfAttendence: List<Attendance>?, callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.updateAttendance(listOfAttendence,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }



    override fun bookingGifts(level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.ShowBookingGiftsCallback) {
        mRemoteDataSource.bookingGifts(level,object :IRemoteDataSource.ShowBookingGiftsCallback{
            override fun onResponse(user: List<User>) {
                callback.onResponse(user)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }

    fun updatePassword(password: String, callback: IRemoteDataSource.MessageCallback) {
        val user=mIPreferencesHelper.getUser()!!
        user.password=password
        mRemoteDataSource.updatePassword(user,object :IRemoteDataSource.LoginCallback{
            override fun onResponse(user: User) {
                mIPreferencesHelper.setUser(user)
                callback.onResponse(R.string.edited)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })
    }
    override fun filterGift(level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.ShowGiftsCallback) {
        mRemoteDataSource.filterGift(level,object : IRemoteDataSource.ShowGiftsCallback{
            override fun onResponse(gift: List<Gift>) {
                callback.onResponse(gift)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }
        })

    }

}