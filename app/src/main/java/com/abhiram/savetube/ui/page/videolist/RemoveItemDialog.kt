package com.abhiram.savetube.ui.page.videolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abhiram.savetube.R
import com.abhiram.savetube.database.objects.DownloadedVideoInfo
import com.abhiram.savetube.ui.component.CheckBoxItem
import com.abhiram.savetube.ui.component.savetubeDialog

@Composable
fun RemoveItemDialog(
    deleteFile: Boolean = false,
    onDeleteFileToggled: (Boolean) -> Unit = {},
    info: DownloadedVideoInfo,
    onRemoveConfirm: (Boolean) -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    savetubeDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = stringResource(R.string.delete_info)) },
        icon = { Icon(Icons.Outlined.Delete, null) },
        text = {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    text = stringResource(R.string.delete_info_msg).format(info.videoTitle),
                )
                CheckBoxItem(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = stringResource(R.string.delete_file),
                    checked = deleteFile,
                    onValueChange = onDeleteFileToggled,
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                    onRemoveConfirm(deleteFile)
                }
            ) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) { Text(text = stringResource(R.string.dismiss)) }
        },
    )
}
