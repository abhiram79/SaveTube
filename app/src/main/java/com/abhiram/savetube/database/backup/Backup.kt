package com.abhiram.savetube.database.backup

import com.abhiram.savetube.database.objects.CommandTemplate
import com.abhiram.savetube.database.objects.DownloadedVideoInfo
import com.abhiram.savetube.database.objects.OptionShortcut
import kotlinx.serialization.Serializable

@Serializable
data class Backup(
    val templates: List<CommandTemplate>? = null,
    val shortcuts: List<OptionShortcut>? = null,
    val downloadHistory: List<DownloadedVideoInfo>? = null,
)
