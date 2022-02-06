package com.s_h_y_a.kotlindatastoresample.ui.editAccount

import android.widget.Toast
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.s_h_y_a.kotlindatastoresample.R

@Composable
fun SaveIcon(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val context = LocalContext.current
    IconButton(
        modifier = modifier,
        onClick = {
        onClick()
        Toast
            .makeText(context, R.string.saved, Toast.LENGTH_SHORT)
            .show()
    }) {
        Icon(
            imageVector = Icons.Outlined.Save,
            contentDescription = stringResource(R.string.save)
        )
    }

}