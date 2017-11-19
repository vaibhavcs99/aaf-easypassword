package io.github.hanjoongcho.easypassword.activities

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import io.github.hanjoongcho.easypassword.R
import io.github.hanjoongcho.easypassword.databinding.ActivityAccountEditBinding
import io.github.hanjoongcho.easypassword.helper.database
import io.github.hanjoongcho.easypassword.models.Account

/**
 * Created by CHO HANJOONG on 2017-11-18.
 */

class AccountEditActivity : AppCompatActivity() {

    private var mBinding: ActivityAccountEditBinding? = null
    private var mAccount: Account? = null
    private var mSequence:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_edit)
        mSequence = intent.getIntExtra(Account.SEQUENCE, -1)
        mAccount = this@AccountEditActivity.database().selectAccountBy(mSequence)

        mBinding = DataBindingUtil.setContentView<ActivityAccountEditBinding>(this,
                R.layout.activity_account_edit)

        mBinding?.accountId?.setText(mAccount?.id)
        mBinding?.accountPassword?.setText(mAccount?.password)
        mBinding?.accountSummary?.setText(mAccount?.summary)
        mBinding?.accountManageTarget?.setText(mAccount?.title)

        setSupportActionBar(mBinding?.toolbarPlayer)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }

        mBinding?.save?.setOnClickListener(View.OnClickListener { _ ->
            val account: Account = Account(
                    mBinding?.accountManageTarget?.text.toString(),
                    mBinding?.accountSummary?.text.toString(),
                    "web",
                    mBinding?.accountId?.text.toString(),
                    mBinding?.accountPassword?.text.toString(),
                    4,
                    mAccount?.sequence!!
            )
            this@AccountEditActivity.database().updateAccount(account)
            finish()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.edit -> {
            }
            R.id.save -> {
                val account: Account = Account(
                        mBinding?.accountManageTarget?.text.toString(),
                        mBinding?.accountSummary?.text.toString(),
                        "web",
                        mBinding?.accountId?.text.toString(),
                        mBinding?.accountPassword?.text.toString(),
                        4,
                        mAccount?.sequence!!
                )
                this@AccountEditActivity.database().updateAccount(account)
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.account_detail, menu)
//        return true
//    }

    companion object {
        fun getStartIntent(context: Context, sequence: Int): Intent {
            return Intent(context, AccountEditActivity::class.java)
                    .apply { putExtra(Account.SEQUENCE, sequence) }
        }
    }
}