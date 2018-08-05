package com.socinet.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socinet.adapter.UserSelectionAdapter
import com.socinet.uicomponent.UserSelectionUI
import org.jetbrains.anko.setContentView

class UserSelectionActivity : AppCompatActivity() {
    private lateinit var mainUI: UserSelectionUI
    private lateinit var adapter: UserSelectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = UserSelectionUI()
        mainUI.setContentView(this)

        adapter = UserSelectionAdapter(supportFragmentManager, this)
        mainUI.viewPager.adapter = adapter
    }
}
