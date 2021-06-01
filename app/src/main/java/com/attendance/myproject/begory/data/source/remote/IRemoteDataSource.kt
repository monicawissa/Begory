package com.attendance.myproject.begory.data.source.remote

import com.attendance.myproject.begory.data.Models.Attendance
import com.attendance.myproject.begory.data.Models.Gift
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.attendance.myproject.begory.data.Models.User

//remote
public interface IRemoteDataSource {
    interface LoginCallback {
        fun onResponse(user: User)
        fun onDataNotAvailable(message: Int?)
    }
    interface UsersCallback {
        fun onResponse(user: List<User>)
        fun onDataNotAvailable(message: Int?)
    }
    interface MessageCallback {
        fun onResponse(message: Int?)
        fun onDataNotAvailable(message: Int?)
    }

    interface ShowGiftsCallback {
        fun onResponse(gift: List<Gift>)
        fun onDataNotAvailable(message: Int?)
    }
    interface ShowBookingGiftsCallback {
        fun onResponse(users: List<User>)
        fun onDataNotAvailable(message: Int?)
    }
    fun addGift(gift: Gift,level: FirebaseFilterType.LevelFilterType
                        , callback: IRemoteDataSource.MessageCallback)
    fun updateGift(gift: Gift,level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.MessageCallback)

    fun deleteGift(gift: Gift,level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.MessageCallback)

    fun login(mobile: String, password: String, callback: LoginCallback)
    fun registerStudent(user: User
                        , callback: IRemoteDataSource.MessageCallback)
    fun registerSubAdmin(user: User,callback: IRemoteDataSource.MessageCallback)
    fun registerAdmin(user: User,callback: IRemoteDataSource.MessageCallback)
    fun checkUserExist(mobile: String, callback: LoginCallback)
    fun updateStudent(user: User
                        , callback: IRemoteDataSource.MessageCallback)
    fun updateSubAdmin(user: User,callback: IRemoteDataSource.MessageCallback)
    fun updateAdmin(user: User,callback: IRemoteDataSource.MessageCallback)
    fun updatePassword(user: User,callback: IRemoteDataSource.LoginCallback)
    fun filterLevel(level: FirebaseFilterType.LevelFilterType, callback: UsersCallback)
    fun updateAttendance(listOfAttendence: List<Attendance>?, callback: MessageCallback)
    fun filterGift(studentLevel: FirebaseFilterType.LevelFilterType, giftsCallback: IRemoteDataSource.ShowGiftsCallback)
    fun bookingGifts(level: FirebaseFilterType.LevelFilterType, showBookingGiftsCallback: IRemoteDataSource.ShowBookingGiftsCallback)

//    fun getTopRatedMovies(callback: LoadAllMoviesCallback)
//    fun getMovieActors(callback: IResponseCast, movie_id: Int)
//    fun getMovieTrailer(callback: IResponseTrailer, movie_id: Int)
//    fun getMovieReview(callback: IResponseReview, movie_id: Int)
//    fun clearCache()
}