package com.socinet.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socinet.adapter.HomeAdapter
import com.socinet.model.User
import com.socinet.uicomponent.HomeUI
import com.socinet.uicomponent.UserUI
import org.jetbrains.anko.setContentView

class UserDetailActivity : AppCompatActivity() {
    private lateinit var mainUI: UserUI
    private lateinit var user: User

    companion object {
        val USER = "USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = UserUI()
        mainUI.setContentView(this)

        user = this.intent.getParcelableExtra(USER)

        mainUI.name.text = user.name
        mainUI.userName.text = user.username
        mainUI.email.text = user.email
        mainUI.phone.text = user.phone
        mainUI.website.text = user.website

        mainUI.addressCity.text = user.address.city
        mainUI.addressSuit.text = user.address.suite
        mainUI.addressStreet.text = user.address.street
        mainUI.addressZipCode.text = user.address.zipcode

        mainUI.latitude.text = user.address.geo.lat.toString()
        mainUI.longitude.text = user.address.geo.lng.toString()

        mainUI.companyName.text = user.company.name
        mainUI.companyCatchPhrase.text = user.company.catchPhrase
        mainUI.companyBs.text = user.company.bs
    }
}
