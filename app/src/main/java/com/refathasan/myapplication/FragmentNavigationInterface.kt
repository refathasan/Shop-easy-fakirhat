package com.refathasan.myapplication

import androidx.fragment.app.Fragment

interface FragmentNavigationInterface {
    fun navifateFragment(fragment:Fragment, addtoStack:Boolean)
}