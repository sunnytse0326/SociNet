package com.socinet.fragment

import android.arch.lifecycle.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.support.v4.ctx
import android.arch.lifecycle.Lifecycle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.Toast
import com.socinet.adapter.PhotoRecyclerViewAdapter
import com.socinet.uicomponent.PhotoFragmentUI
import com.socinet.viewmodel.PhotoViewModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert


class PhotoFragment : BaseFragment(), LifecycleObserver {
    private lateinit var photoViewModel: PhotoViewModel
    private val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)
    private lateinit var adapter: PhotoRecyclerViewAdapter
    private lateinit var photoUI: PhotoFragmentUI

    private var page = 0
    private var userId = 0
    private var albumId = 0

    companion object {
        val USERID = "USERID"
        val ALBUMID = "ALBUMID"

        fun newInstance(userId: Int, albumId: Int): PhotoFragment {
            var photoFragment = PhotoFragment()
            var bundle = Bundle()
            bundle.putInt(PhotoFragment.USERID, userId)
            bundle.putInt(PhotoFragment.ALBUMID, albumId)
            photoFragment.arguments = bundle
            return photoFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        photoUI = PhotoFragmentUI()
        return photoUI.createView(AnkoContext.create(ctx, this))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userId = arguments?.getInt(USERID)?:0
        albumId = arguments?.getInt(ALBUMID)?:0
        initPhotoViews()
    }

    private fun initPhotoViews() {
        photoViewModel = ViewModelProviders.of(this, PhotoViewModel.VMFactory(mLifecycleRegistry, this)).get(PhotoViewModel::class.java)

        adapter = PhotoRecyclerViewAdapter(object : PhotoRecyclerViewAdapter.OnClickListener {
            override fun onBackgroundClicked(position: Int) {
                var imageView: ImageView? = null
                alert {
                    customView {
                        relativeLayout {
                            imageView = imageView {
                                scaleType = ImageView.ScaleType.CENTER_CROP
                            }.lparams{
                                width = dip(300)
                                height = dip(300)
                                gravity = Gravity.CENTER
                            }
                        }
                    }
                }.show()
                Picasso.get().load(adapter.photos[position].url).into(imageView)
            }
        }, mutableListOf())

        photoUI.recyclerView.layoutManager = GridLayoutManager(this.activity, 3) as GridLayoutManager
        photoUI.recyclerView.adapter = adapter

        photoViewModel.getPhotos(page, albumId).observe(this, Observer { photos ->
            photoUI.loadLty.visibility = View.GONE
            adapter.addPhotos(photos ?: listOf())
            adapter.notifyDataSetChanged()
            photoUI.paginationLoad = false
        })

        photoViewModel.getErrors().observe(this, Observer { error ->
            photoUI.loadLty.visibility = View.GONE
            Toast.makeText(this@PhotoFragment.activity, error?.localizedMessage, Toast.LENGTH_SHORT).show()
        })

        photoUI.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var recyclerViewState: Int = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                recyclerViewState = newState
                if (recyclerViewState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    val visibleItemCount = photoUI.recyclerView.childCount
                    val totalItemCount = photoUI.recyclerView.layoutManager.getItemCount()
                    var firstVisibleItem = (photoUI.recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                    if (!photoUI.paginationLoad && totalItemCount - visibleItemCount <= firstVisibleItem + 3) {
                        photoUI.paginationLoad = true
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
        photoViewModel.getPhotos(page, albumId)
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