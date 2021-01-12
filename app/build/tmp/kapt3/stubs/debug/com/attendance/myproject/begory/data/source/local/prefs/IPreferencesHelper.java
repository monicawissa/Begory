package com.attendance.myproject.begory.data.source.local.prefs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0005H&\u00a8\u0006\b"}, d2 = {"Lcom/attendance/myproject/begory/data/source/local/prefs/IPreferencesHelper;", "", "getUser", "Lcom/attendance/myproject/begory/data/Models/User;", "setUser", "", "user", "setUserAsLoggedOut", "app_debug"})
public abstract interface IPreferencesHelper {
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.attendance.myproject.begory.data.Models.User getUser();
    
    public abstract void setUser(@org.jetbrains.annotations.NotNull()
    com.attendance.myproject.begory.data.Models.User user);
    
    public abstract void setUserAsLoggedOut();
}