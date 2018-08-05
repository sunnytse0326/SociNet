package com.socinet.uicomponent

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import com.socinet.fragment.PhotoFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PhotoFragmentUI : AnkoComponent<PhotoFragment> {
    lateinit var recyclerView: RecyclerView
    lateinit var loadLty: RelativeLayout
    var paginationLoad  = false

    override fun createView(ui: AnkoContext<PhotoFragment>) =  with(ui)  {
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