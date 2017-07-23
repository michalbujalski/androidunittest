package com.mb.simpleapp.data.users

interface UserDataStore{
    fun save(user: UserData);
}