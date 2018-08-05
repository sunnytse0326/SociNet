package com.socinet.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

open class BaseFragment : Fragment() {
    var baseActivity: FragmentActivity ?= null

    fun attach(activity: FragmentActivity){
        baseActivity = activity
    }
}