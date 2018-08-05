package com.socinet.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.socinet.fragment.PhotoFragment
import com.socinet.fragment.UserFragment


class AlbumAdapter(mFragmentManager: FragmentManager, mUserId: Int, mAlbumId: Int) :
        FragmentStatePagerAdapter(mFragmentManager) {
    private val userId: Int = mUserId
    private val albumId: Int = mAlbumId

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return PhotoFragment.newInstance(userId, albumId)
        }
        return UserFragment.newInstance()
    }

    override fun getCount(): Int {
        return 1
    }
}