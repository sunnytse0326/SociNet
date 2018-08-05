package com.socinet.uicomponent

import android.support.v4.view.ViewPager
import android.view.View
import com.socinet.activity.UserSelectionActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.viewPager

class UserSelectionUI : AnkoComponent<UserSelectionActivity> {
    lateinit var viewPager: ViewPager

    override fun createView(ui: AnkoContext<UserSelectionActivity>) =  with(ui)  {
        verticalLayout {
            viewPager = viewPager {
                id = View.generateViewId()
            }.lparams{
                width = matchParent
                height = matchParent
            }
        }
    }

}