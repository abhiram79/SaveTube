package com.abhiram.savetube.ui.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhiram.savetube.Downloader
import com.abhiram.savetube.util.PreferenceUtil
import com.abhiram.savetube.util.PreferenceUtil.getBoolean
import com.abhiram.savetube.util.PreferenceUtil.getLong
import com.abhiram.savetube.util.PreferenceUtil.getString
import com.abhiram.savetube.util.UpdateUtil
import com.abhiram.savetube.util.YT_DLP_AUTO_UPDATE
import com.abhiram.savetube.util.YT_DLP_UPDATE_INTERVAL
import com.abhiram.savetube.util.YT_DLP_UPDATE_TIME
import com.abhiram.savetube.util.YT_DLP_VERSION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun YtdlpUpdater() {

    val downloaderState by Downloader.downloaderState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (downloaderState !is Downloader.State.Idle) return@LaunchedEffect

        if (!YT_DLP_AUTO_UPDATE.getBoolean() && YT_DLP_VERSION.getString().isNotEmpty())
            return@LaunchedEffect

        if (!PreferenceUtil.isNetworkAvailableForDownload()) {
            return@LaunchedEffect
        }

        val lastUpdateTime = YT_DLP_UPDATE_TIME.getLong()
        val currentTime = System.currentTimeMillis()

        if (currentTime < lastUpdateTime + YT_DLP_UPDATE_INTERVAL.getLong()) {
            return@LaunchedEffect
        }

        runCatching {
                Downloader.updateState(state = Downloader.State.Updating)
                withContext(Dispatchers.IO) { UpdateUtil.updateYtDlp() }
            }
            .onFailure { it.printStackTrace() }
        Downloader.updateState(state = Downloader.State.Idle)
    }
}
