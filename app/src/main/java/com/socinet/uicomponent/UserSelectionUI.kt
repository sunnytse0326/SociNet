package com.socinet.uicomponent

import android.app.ProgressDialog
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.socinet.activity.HomeActivity
import com.socinet.R
import com.socinet.activity.UserSelectionActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
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