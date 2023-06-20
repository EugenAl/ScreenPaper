package dpr.svich.screenpaper.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(contentPaddingValues: PaddingValues) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPaddingValues)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth()
        ) {
            val checkedState = remember { mutableStateOf(false) }
            Text(text = "Dark mode", fontSize = 22.sp)
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it }
            )
        }
    }
}