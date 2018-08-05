package com.socinet.fragment

import android.arch.lifecycle.*
import android.content.Context
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
import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.socinet.activity.HomeActivity
import com.socinet.activity.UserDetailActivity
import com.socinet.adapter.UserRecyclerViewAdapter


class UserFragment : BaseFragment() {
    private lateinit var userViewModel: UserViewModel
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    private lateinit var adapter: UserRecyclerViewAdapter
    private lateinit var fragmentUI: UserFragmentUI

    companion object {
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentUI = UserFragmentUI()
        return fragmentUI.createView(AnkoContext.create(ctx, this))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel = ViewModelProviders.of(this, UserViewModel.VMFactory(mLifecycleRegistry, this)).get(UserViewModel::class.java)

        adapter = UserRecyclerViewAdapter(object : UserRecyclerViewAdapter.OnClickListener {
            override fun onUserInfoClicked(position: Int) {
                val intent = Intent(this@UserFragment.activity, UserDetailActivity::class.java)
                intent.putExtra(UserDetailActivity.USER, adapter.users[position])
                startActivity(intent)
            }

            override fun onContentClicked(position: Int) {
                val intent = Intent(this@UserFragment.activity, HomeActivity::class.java)
                intent.putExtra(HomeActivity.USERID, adapter.users[position].id)
                intent.putExtra(HomeActivity.USERNAME, adapter.users[position].username)
                startActivity(intent)
            }

            override fun onBackgroundClicked(position: Int) {
                val intent = Intent(this@UserFragment.activity, HomeActivity::class.java)
                intent.putExtra(HomeActivity.USERID, adapter.users[position].id)
                intent.putExtra(HomeActivity.USERNAME, adapter.users[position].username)
                startActivity(intent)
            }
        }, listOf())

        fragmentUI.recyclerView.layoutManager = LinearLayoutManager(this.activity)
        fragmentUI.recyclerView.adapter = adapter

        userViewModel.getUsers().observe(this, Observer { userList ->
            fragmentUI.loadLty.visibility = View.GONE
            adapter.users = userList ?: listOf()
            adapter.notifyDataSetChanged()
        })

        userViewModel.getErrors().observe(this, Observer { error ->
            fragmentUI.loadLty.visibility = View.GONE
            Toast.makeText(this@UserFragment.activity, error?.localizedMessage, Toast.LENGTH_SHORT).show()
        })

    }
}