package com.waz.zclient.feature.auth.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.waz.zclient.R
import com.waz.zclient.core.extension.createScope
import com.waz.zclient.core.extension.replaceFragment
import com.waz.zclient.feature.auth.registration.di.REGISTRATION_SCOPE
import com.waz.zclient.feature.auth.registration.di.REGISTRATION_SCOPE_ID

class CreateAccountActivity : AppCompatActivity(R.layout.activity_create_account) {

    private val scope = createScope(
        scopeId = REGISTRATION_SCOPE_ID,
        scopeName = REGISTRATION_SCOPE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(R.id.activityCreateAccountLayoutContainer, CreateAccountFragment.newInstance(), false)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context) = Intent(context, CreateAccountActivity::class.java)
    }
}
