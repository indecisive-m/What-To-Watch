package com.example.whattowatch.presentation.search_results.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whattowatch.R
import com.example.whattowatch.ui.theme.WhatToWatchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearchQueryChange: (String) -> Unit,
    searchQuery: String,
    onImeSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val focusRequester = remember { FocusRequester() }
    var isTextFieldFocused by remember { mutableStateOf(false) }

    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        label = {
            Text(
                text = stringResource(R.string.search_placeholder)
            )
        },
        leadingIcon = {
            IconButton(
                onClick = { onImeSearch(searchQuery) }
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }

        },


        //// TODO Implement speech recognition later on?

        trailingIcon = {
            AnimatedVisibility(
                visible = isTextFieldFocused
            ) {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = stringResource(R.string.search_with_voice)
                )

            }

        },
        shape = RoundedCornerShape(36.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onImeSearch(searchQuery)
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),

        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isTextFieldFocused = focusState.isFocused
            }

    )

}


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    WhatToWatchTheme {
        SearchBar(
            onSearchQueryChange = {},
            searchQuery = "",
            onImeSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}