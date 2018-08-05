package com.socinet.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.socinet.fragment.UserFragment


class UserSelectionAdapter(mFragmentManager: FragmentManager, mContext: Context) :
        FragmentStatePagerAdapter(mFragmentManager) {
    private val context: Context = mContext

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return UserFragment.newInstance()
        }
        return UserFragment.newInstance()
    }

    override fun getCount(): Int {
        return 1
    }
}