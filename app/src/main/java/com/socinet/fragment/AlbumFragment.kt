package com.socinet.fragment

import android.arch.lifecycle.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import android.arch.lifecycle.Lifecycle
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView
import android.widget.Toast
import com.socinet.activity.AlbumActivity
import com.socinet.adapter.AlbumRecyclerViewAdapter
import com.socinet.uicomponent.AlbumFragmentUI
import com.socinet.viewmodel.AlbumViewModel


class AlbumFragment : BaseFragment(), LifecycleObserver {
    private lateinit var albumViewModel: AlbumViewModel
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    private lateinit var adapter: AlbumRecyclerViewAdapter
    private lateinit var fragmentUI: AlbumFragmentUI

    private var page = 0
    private var userId = 0

    companion object {
        val USERID = "USERID"

        fun newInstance(userId: Int): AlbumFragment {
            var postFragment = AlbumFragment()
            var bundle = Bundle()
            bundle.putInt(PostFragment.USERID, userId)
            postFragment.arguments = bundle
            return postFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentUI = AlbumFragmentUI()
        return fragmentUI.createView(AnkoContext.create(ctx, this))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userId = arguments?.getInt(USERID)?:0

        albumViewModel = ViewModelProviders.of(this, AlbumViewModel.VMFactory(mLifecycleRegistry, this)).get(AlbumViewModel::class.java)

        adapter = AlbumRecyclerViewAdapter(object : AlbumRecyclerViewAdapter.OnClickListener {
            override fun onBackgroundClicked(position: Int) {
                val intent = Intent(this@AlbumFragment.activity, AlbumActivity::class.java)
                intent.putExtra(AlbumActivity.USERID, userId)
                intent.putExtra(AlbumActivity.ALBUMID, adapter.posts[position].id)
                startActivity(intent)
            }
        }, mutableListOf())

        fragmentUI.recyclerView.layoutManager = LinearLayoutManager(this.activity)
        fragmentUI.recyclerView.adapter = adapter

        albumViewModel.getPosts(page, userId).observe(this, Observer { posts ->
            fragmentUI.loadLty.visibility = View.GONE
            adapter.addPosts(posts ?: mutableListOf())
            adapter.notifyDataSetChanged()
        })

        albumViewModel.getErrors().observe(this, Observer { error ->
            fragmentUI.loadLty.visibility = View.GONE
            Toast.makeText(this@AlbumFragment.activity, error?.localizedMessage, Toast.LENGTH_SHORT).show()
        })

        fragmentUI.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var recyclerViewState: Int = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerViewState = newState
                if (recyclerViewState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    val visibleItemCount = fragmentUI.recyclerView.childCount
                    val totalItemCount = fragmentUI.recyclerView.layoutManager.getItemCount()
                    var firstVisibleItem = (fragmentUI.recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (!fragmentUI.paginationLoad && totalItemCount - visibleItemCount <= firstVisibleItem + 3) {
                        fragmentUI.paginationLoad = true
                        if (adapter != null) {
                            loadMore()
                        }
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun loadMore() {
        page++
        albumViewModel.getPosts(page, userId)
    }


    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}