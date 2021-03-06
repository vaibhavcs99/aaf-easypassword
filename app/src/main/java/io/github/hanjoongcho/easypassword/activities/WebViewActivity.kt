package io.github.hanjoongcho.easypassword.activities

import io.github.hanjoongcho.commons.activities.BaseSimpleActivity
import io.github.hanjoongcho.easypassword.extensions.pausePatternLock
import io.github.hanjoongcho.easypassword.extensions.resumePatternLock

/**
 * Created by CHO HANJOONG on 2018-01-20.
 */

class WebViewActivity : BaseSimpleActivity() {
    override fun onPause() {
        super.onPause()
        pausePatternLock()
    }

    override fun onResume() {
        super.onResume()
        resumePatternLock()
    }
}