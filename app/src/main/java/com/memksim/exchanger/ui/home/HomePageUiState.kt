package com.memksim.exchanger.ui.home

import com.memksim.exchanger.usecases.ItemState
import com.memksim.exchanger.usecases.UiState

data class HomePageUiState(
    val itemStateList: List<HomePageItemUiState>
) : UiState

data class HomePageItemUiState(
    val lastUpdated: String,
    val charCode: String,
    val nominal: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val isBookmarked: Boolean,
    val isTrendingUp: Boolean,
    val onRemoveBookmark: (HomePageItemUiState) -> Unit
) : ItemState