package com.socinet.fragment

import android.arch.lifecycle.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import android.arch.lifecycle.Lifecycle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView
import android.widget.Toast
import com.socinet.adapter.TodoRecyclerViewAdapter
import com.socinet.uicomponent.TodoFragmentUI
import com.socinet.viewmodel.CommentViewModel


class TodoFragment : BaseFragment(), LifecycleObserver {
    private lateinit var commentViewModel: CommentViewModel
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    private lateinit var adapter: TodoRecyclerViewAdapter
    private lateinit var fragmentUI: TodoFragmentUI

    private var page = 0
    private var userId = 0

    companion object {
        val USERID = "USERID"

        fun newInstance(userId: Int): TodoFragment {
            var commentFragment = TodoFragment()
            var bundle = Bundle()
            bundle.putInt(TodoFragment.USERID, userId)
            commentFragment.arguments = bundle
            return commentFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentUI = TodoFragmentUI()
        return fragmentUI.createView(AnkoContext.create(ctx, this))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userId = arguments?.getInt(USERID)?:0

        commentViewModel = ViewModelProviders.of(this, CommentViewModel.VMFactory(mLifecycleRegistry, this)).get(CommentViewModel::class.java)

        adapter = TodoRecyclerViewAdapter(object : TodoRecyclerViewAdapter.OnClickListener {
            override fun onBackgroundClicked(position: Int) {
            }
        }, mutableListOf())

        fragmentUI.recyclerView.layoutManager = LinearLayoutManager(this.activity)
        fragmentUI.recyclerView.adapter = adapter

        commentViewModel.getTodos(page, userId).observe(this, Observer { posts ->
            fragmentUI.loadLty.visibility = View.GONE
            adapter.addPosts(posts ?: mutableListOf())
            adapter.notifyDataSetChanged()
        })

        commentViewModel.getErrors().observe(this, Observer { error ->
            fragmentUI.loadLty.visibility = View.GONE
            Toast.makeText(this@TodoFragment.activity, error?.localizedMessage, Toast.LENGTH_SHORT).show()
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
        commentViewModel.getTodos(page, userId)
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