package com.socinet.uicomponent

import android.graphics.Typeface
import android.widget.TextView
import com.socinet.R
import com.socinet.activity.UserDetailActivity
import org.jetbrains.anko.*

class UserUI : AnkoComponent<UserDetailActivity> {
    lateinit var name: TextView
    lateinit var userName: TextView
    lateinit var email: TextView
    lateinit var addressStreet: TextView
    lateinit var addressSuit: TextView
    lateinit var addressCity: TextView
    lateinit var addressZipCode: TextView
    lateinit var latitude: TextView
    lateinit var longitude: TextView
    lateinit var phone: TextView
    lateinit var website: TextView
    lateinit var companyName: TextView
    lateinit var companyCatchPhrase: TextView
    lateinit var companyBs: TextView

    override fun createView(ui: AnkoContext<UserDetailActivity>) = with(ui) {
        verticalLayout {
            padding = dip(10)
            textView {
                textSize = 22f
                typeface = Typeface.DEFAULT_BOLD
                textResource = R.string.user_info
            }.lparams {
                width = matchParent
                height = wrapContent
                bottomMargin = dip(5)
                topMargin = dip(5)
            }

            name = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            userName = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            email = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            phone = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            website = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }

            textView {
                textSize = 22f
                typeface = Typeface.DEFAULT_BOLD
                textResource = R.string.address
            }.lparams {
                width = matchParent
                height = wrapContent
                bottomMargin = dip(5)
                topMargin = dip(5)
            }

            addressStreet = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            addressSuit = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            addressCity = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            addressZipCode = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }

            textView {
                textSize = 22f
                typeface = Typeface.DEFAULT_BOLD
                textResource = R.string.location
            }.lparams {
                width = matchParent
                height = wrapContent
                bottomMargin = dip(5)
                topMargin = dip(5)
            }

            latitude = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            longitude = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }

            textView {
                textSize = 22f
                typeface = Typeface.DEFAULT_BOLD
                textResource = R.string.company
            }.lparams {
                width = matchParent
                height = wrapContent
                bottomMargin = dip(5)
                topMargin = dip(5)
            }

            companyName = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            companyCatchPhrase = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }
            companyBs = textView {
                textSize = 16f
            }.lparams {
                width = matchParent
                height = wrapContent
            }

        }
    }

}