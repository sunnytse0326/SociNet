package com.socinet.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.socinet.fragment.*


class HomeAdapter(mFragmentManager: FragmentManager, mContext: Context, mTabIdx: Array<Int>, mUserId: Int) :
        FragmentStatePagerAdapter(mFragmentManager) {
    private val tabIdx: Array<Int> = mTabIdx
    private val context: Context = mContext
    private val userId = mUserId

    override fun getItem(position: Int): Fragment {
        when(position){
            // 0 -> return UserFragment.newInstance()
            0 -> return AlbumFragment.newInstance(userId)
            1 -> return PostFragment.newInstance(userId)
            2 -> return TodoFragment.newInstance(userId)
        }
        return UserFragment.newInstance()
    }

    override fun getCount(): Int {
        return tabIdx.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabIdx[position])
    }
}