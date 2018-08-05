package com.socinet.fragment

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socinet.uicomponent.UserFragmentUI
import com.socinet.viewmodel.UserViewModel
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

open class BaseFragment : Fragment() {
    var baseActivity: FragmentActivity ?= null

    fun attach(activity: FragmentActivity){
        baseActivity = activity
    }
}