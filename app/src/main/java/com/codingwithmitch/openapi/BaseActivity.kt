package com.codingwithmitch.openapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.codingwithmitch.openapi.ui.auth.AuthActivity
import com.codingwithmitch.openapi.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(){

    private val TAG: String = "AppDebug"

    @Inject
    lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObervers()
    }

    fun subscribeObervers(){
        sessionManager.observeAuthState().observe(this, Observer {

            when(it.authStatus){

                AuthResource.AuthStatus.AUTHENTICATED ->{
                    // do nothing
                    Log.d(TAG, "authenticated: ")

                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED ->{
                    Log.d(TAG, "not authenticated: ")
                    navAuthActivity()
                }
//                AuthResource.AuthStatus.LOADING ->{
//                    Log.d(TAG, "loading: ")
//                    TODO("show progress bar")
//                }
//                AuthResource.AuthStatus.ERROR ->{
//                    Log.d(TAG, "error: ")
//                    TODO("display dialog error")
//                }
            }
        })
    }

    fun navAuthActivity(){
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }


}













