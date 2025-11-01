package com.luciburcu.landmark.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luciburcu.landmark.homescreen.models.Landmark

/**
 * A single UI item representing a landmark in a list.
 */
@Composable
fun LandmarkListItem(landmark: Landmark, onLongClick: () -> Unit, onClick: () -> Unit) {
    Surface(modifier = Modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick)) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(width = 64.dp, height = 64.dp)
                    .background(color = Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = landmark.name.first().toString(),
                    fontStyle = FontStyle.Italic,
                    fontSize = 30.sp,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = landmark.name,
                )
                Text(
                    text = landmark.description,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Navigate"
            )
        }
    }
}