package com.socinet.uicomponent

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.socinet.activity.HomeActivity
import com.socinet.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class HomeUI : AnkoComponent<HomeActivity> {
    lateinit var viewPager: ViewPager
    lateinit var tabLty: TabLayout

    // Define pages on tabLayout
    val tabsIdx = arrayOf(R.string.albums, R.string.posts, R.string.todo)

    override fun createView(ui: AnkoContext<HomeActivity>) =  with(ui)  {
        verticalLayout {
            tabLty = tabLayout {
                tabMode = TabLayout.MODE_FIXED
            }.lparams{
                width = matchParent
                height = wrapContent
            }
            view{
                backgroundColor = R.color.colorDivider
            }.lparams{
                width = matchParent
                height = dip(0.5F)
            }
            viewPager = viewPager {
                id = View.generateViewId()
            }.lparams{
                width = matchParent
                height = matchParent
            }
        }
    }

}