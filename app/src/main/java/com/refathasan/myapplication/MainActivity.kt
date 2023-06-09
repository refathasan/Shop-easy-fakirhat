package com.refathasan.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), FragmentNavigationInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, LoginFragment())
            .commit()


    }

    override fun navifateFragment(fragment: Fragment, addtoStack: Boolean) {
        val trasection = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment)

        if(addtoStack){
            trasection.addToBackStack(null)
        }
        trasection.commit()
    }

}