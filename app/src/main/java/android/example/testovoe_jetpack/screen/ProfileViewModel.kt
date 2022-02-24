package android.example.testovoe_jetpack.screen

import android.example.testovoe_jetpack.pojo.UserInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    private val _userInfo: MutableLiveData<UserInfo> = MutableLiveData(UserInfo())
    val userInfo: LiveData<UserInfo> = _userInfo
}