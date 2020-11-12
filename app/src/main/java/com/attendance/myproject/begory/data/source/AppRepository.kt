package com.attendance.myproject.begory.data.source
import com.attendance.myproject.begory.data.Models.User
import com.attendance.myproject.begory.data.source.local.prefs.IPreferencesHelper
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
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

    override fun getUser(callback: IRemoteDataSource.LoginCallback) {
         mIPreferencesHelper.getUser(callback)
    }

    override fun setUser(user: User) {
        mIPreferencesHelper.setUser(user)    }

    override fun setUserAsLoggedOut() {
        mIPreferencesHelper.setUserAsLoggedOut()
    }

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

    override fun register(user: User,
                          callback: IRemoteDataSource.MessageCallback) {
        mRemoteDataSource.register(user,object :IRemoteDataSource.MessageCallback{
            override fun onResponse(message: Int?) {
                callback.onResponse(message)
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }

        })
    }



}