package com.socinet.uicomponent

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import com.socinet.fragment.AlbumFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class AlbumFragmentUI : AnkoComponent<AlbumFragment> {
    lateinit var recyclerView: RecyclerView
    lateinit var loadLty: RelativeLayout
    var paginationLoad = false

    override fun createView(ui: AnkoContext<AlbumFragment>) =  with(ui)  {
        relativeLayout {
            recyclerView = recyclerView {
                clipToPadding = false
                topPadding = dip(5)
                id = View.generateViewId()
            }.lparams{
                width = matchParent
                height = matchParent
            }
            loadLty = relativeLayout{
                progressBar {
                }.lparams{
                    width = wrapContent
                    height = wrapContent
                    gravity = Gravity.CENTER
                }
            }.lparams{
                width = matchParent
                height = matchParent
            }
        }
    }

}