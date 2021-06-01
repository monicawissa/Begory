package com.attendance.myproject.begory.data.source.remote

import android.content.ContentValues
import android.util.Log
import com.attendance.myproject.begory.R
import com.attendance.myproject.begory.data.Models.Attendance
import com.attendance.myproject.begory.data.Models.Gift
import com.attendance.myproject.begory.data.Models.User
import com.attendance.myproject.begory.data.Models.remote.FirebaseFilterType
import com.google.firebase.database.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val firebaseDatabase: FirebaseDatabase):IRemoteDataSource {
    val baseRef = firebaseDatabase.reference
    override fun addGift(gift: Gift, level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.MessageCallback) {
        val ref = baseRef.child(FirebaseFilterType.gifts).child(level.toString())
        val id = ref.push().key
        gift.id = id
        ref.child(id!!).setValue(gift).addOnSuccessListener {
            callback.onResponse(R.string.added)
        }.addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
    }

    override fun updateGift(gift: Gift,level: FirebaseFilterType.LevelFilterType,  callback: IRemoteDataSource.MessageCallback) {
        val ref = baseRef.child(FirebaseFilterType.gifts).child(level.toString())
        ref.child(gift.id!!).setValue(gift).addOnSuccessListener {
            callback.onResponse(R.string.edited)
        }.addOnFailureListener { callback.onDataNotAvailable(R.string.error) }    }

    override fun deleteGift(gift: Gift,level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.MessageCallback) {
        val ref = baseRef.child(FirebaseFilterType.gifts).child(level.toString())
        ref.child(gift.id!!).removeValue().addOnSuccessListener {
            callback.onResponse(R.string.deleted)
        }.addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
    }

    override fun login(mobile: String, password: String, callback: IRemoteDataSource.LoginCallback){
        val ref = baseRef.child(FirebaseFilterType.users).orderByChild("mobile").equalTo(mobile)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach {
                        val user = it.getValue(User::class.java);
                        if (user!!.mobile_password.equals("$mobile $password"))
                            callback.onResponse(user)
                        else callback.onDataNotAvailable(R.string.wrong_password)
                    }

                } else callback.onDataNotAvailable(R.string.account_not_found)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })

    }
    override fun registerStudent(user: User,
                                 callback: IRemoteDataSource.MessageCallback) {
        val ref = baseRef.child(FirebaseFilterType.users).orderByChild("mobile").equalTo(user.mobile)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    callback.onDataNotAvailable(R.string.user_exist)
                } else {
                    val query =baseRef.child(FirebaseFilterType.users)
                    Log.d(ContentValues.TAG, "showMessage: " + query)
                    val id = query.push().key
                    user.id = id
                    query.child(id!!).setValue(user).addOnSuccessListener {
                        callback.onResponse(R.string.added)
                    }
                            .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })
    }
    override fun registerSubAdmin(user1: User,
                                  callback: IRemoteDataSource.MessageCallback) {
        val ref = baseRef.child(FirebaseFilterType.users).orderByChild("mobile").equalTo(user1.mobile)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach {
                        val user = it.getValue(User::class.java);
                        if (user!!.subAdminLevel!!.isEmpty()) {
                            var query = baseRef.child(FirebaseFilterType.users).child(user!!.id!!).child("subAdminLevel").setValue(user1.subAdminLevel)
                                    .addOnSuccessListener { callback.onResponse(R.string.added) }
                                    .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
                        } else callback.onDataNotAvailable(R.string.user_exist)
                    }
                } else {
                    val query =baseRef.child(FirebaseFilterType.users)
                    Log.d(ContentValues.TAG, "showMessage: " + query)
                    val id = query.push().key
                    user1.id = id
                    query.child(id!!).setValue(user1)
                            .addOnSuccessListener { callback.onResponse(R.string.added) }
                            .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })


    }
    override fun registerAdmin(user1: User,
                               callback: IRemoteDataSource.MessageCallback) {
        val ref = baseRef.child(FirebaseFilterType.users).orderByChild("mobile").equalTo(user1.mobile)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach {
                        val user = it.getValue(User::class.java);
                        if (user!!.adminLevel!!.isEmpty()) {
                            var query = baseRef.child(FirebaseFilterType.users).child(user!!.id!!).child("adminLevel").setValue(user1.adminLevel)
                                    .addOnSuccessListener { callback.onResponse(R.string.added) }
                                    .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
                        } else callback.onDataNotAvailable(R.string.user_exist)
                    }
                } else {
                    val query = baseRef.child(FirebaseFilterType.users)
                    Log.d(ContentValues.TAG, "showMessage: " + query)
                    val id = query.push().key
                    user1.id = id
                    query.child(id!!).setValue(user1)
                            .addOnSuccessListener { callback.onResponse(R.string.added) }
                            .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })


    }
    override fun checkUserExist(mobile: String, callback: IRemoteDataSource.LoginCallback){
        val ref = baseRef.child(FirebaseFilterType.users).orderByChild("mobile").equalTo(mobile)
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach {
                        val user = it.getValue(User::class.java);
                        callback.onResponse(user!!)
                    }
                } else {
                    callback.onDataNotAvailable(R.string.account_not_found)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })
    }
    override fun updateStudent(user: User, callback: IRemoteDataSource.MessageCallback){
        baseRef.child(FirebaseFilterType.users).child(user.id!!).setValue(user).addOnSuccessListener { callback.onResponse(R.string.edited) }
                .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
    }
    override fun updateSubAdmin(user: User, callback: IRemoteDataSource.MessageCallback){
        baseRef.child(FirebaseFilterType.users).child(user.id!!).child("subAdminLevel").setValue(user.subAdminLevel).addOnSuccessListener { callback.onResponse(R.string.edited) }
                .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }

    }
    override fun updateAdmin(user: User, callback: IRemoteDataSource.MessageCallback){
        baseRef.child(FirebaseFilterType.users).child(user.id!!).child("adminLevel").setValue(user.adminLevel).addOnSuccessListener { callback.onResponse(R.string.edited) }
                .addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
    }

    override fun updatePassword(userr: User, callback: IRemoteDataSource.LoginCallback) {
        checkUserExist(userr.mobile!!,object : IRemoteDataSource.LoginCallback{
            override fun onResponse(user: User) {
                user.password=userr.password
                user.mobile_password="${user.mobile} ${user.password}"
                user.firstTime_ToLogin=false
                baseRef.child(FirebaseFilterType.users).child(user.id!!).setValue(user).addOnSuccessListener {
                    callback.onResponse(user)
                }.addOnFailureListener { callback.onDataNotAvailable(R.string.error) }
            }

            override fun onDataNotAvailable(message: Int?) {
                callback.onDataNotAvailable(message)
            }

        })

    }

    override fun filterLevel(level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.UsersCallback) {
        val ref = baseRef.child(FirebaseFilterType.users).orderByChild("studentLevel").equalTo(level.toString())
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    var mlist:ArrayList<User> = ArrayList()
                    dataSnapshot.children.forEach {
                        //var user:User=User(it.value)
                        mlist.add( it.getValue(User::class.java)!! )

                    }
                    callback.onResponse(mlist.toList())
                } else {
                    callback.onDataNotAvailable(R.string.no_student)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })
    }
    //return list of users with changing name of student to the list of his gifts
    override fun bookingGifts(level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.ShowBookingGiftsCallback) {
        val ref = baseRef.child(FirebaseFilterType.users).orderByChild("studentLevel").equalTo(level.toString())
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    var mlist:ArrayList<User> = ArrayList()
                    dataSnapshot.children.forEach {
                        //var user:User=User(it.value)
                        mlist.add( it.getValue(User::class.java)!! )

                    }
                    mlist.sortWith((compareByDescending({ it.realPrice })))
                    for (student in mlist)
                    {
                        var bookedItems = ""
                        if (student.selectedGifts!=null) {
                            bookedItems = "\n"
                            for (gift in student.selectedGifts!!)
                                bookedItems += gift.name + " ( " + gift.price + " )" + "\n"
                        }

                        if (!bookedItems.equals("")) {
                            student.name = StringBuilder().append(student.name).append("\n").append(bookedItems).toString()
                            student.name = student.name!!.substring(0, student.name!!.length-1)
                        }
                    }
                    callback.onResponse(mlist.toList())
                } else {
                    callback.onDataNotAvailable(R.string.no_booking_gift)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })
    }
    override fun filterGift(level: FirebaseFilterType.LevelFilterType, callback: IRemoteDataSource.ShowGiftsCallback) {
        val ref = baseRef.child(level.toString())
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    var mlist:ArrayList<Gift> = ArrayList()
                    dataSnapshot.children.forEach {
                        //var user:User=User(it.value)
                        mlist.add( it.getValue(Gift::class.java)!! )
                        mlist.sortWith((compareByDescending({ it.price })))
                    }
                    callback.onResponse(mlist.toList())
                } else {
                    callback.onDataNotAvailable(R.string.no_gift)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback.onDataNotAvailable(R.string.error)
            }
        })
    }
    fun Boolean.toInt() = if (this) 1 else 0

    override fun updateAttendance(listOfAttendence: List<Attendance>?, callback: IRemoteDataSource.MessageCallback) {
        var error:Int =0
        for (i in listOfAttendence!!){
            baseRef.child(FirebaseFilterType.users).child(i.id).child("listOfAttendence")
                    .child(i.date).setValue(i)
                    .addOnFailureListener { error=R.string.error }
            val cal=(i.isTnawel.toInt()*5)+(i.isA3traf.toInt()*5)+(i.iskodas.toInt()*5)+(i.isAttend.toInt()*5)
            baseRef.child(FirebaseFilterType.users).child(i.id).child("balanceEqlomat").setValue(ServerValue.increment(cal.toDouble()))
        }
        if(error==0)callback.onResponse(R.string.edited)
        else callback.onDataNotAvailable(error)
    }
}
