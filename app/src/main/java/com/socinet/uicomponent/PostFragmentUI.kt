package com.socinet.uicomponent

import android.app.ProgressDialog
import android.graphics.Typeface
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import com.socinet.activity.HomeActivity
import com.socinet.R
import com.socinet.fragment.PostFragment
import com.socinet.fragment.UserFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.viewPager

class PostFragmentUI : AnkoComponent<PostFragment> {
    lateinit var recyclerView: RecyclerView
    lateinit var loadLty: RelativeLayout
    var paginationLoad = false

    override fun createView(ui: AnkoContext<PostFragment>) =  with(ui)  {
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