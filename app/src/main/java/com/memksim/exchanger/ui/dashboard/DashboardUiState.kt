package com.memksim.exchanger.ui.dashboard

import com.memksim.exchanger.usecases.ItemState
import com.memksim.exchanger.usecases.UiState

data class DashboardUiState(
    val itemStateList: List<DashboardItemUiState>
) : UiState

data class DashboardItemUiState(
    val charCode: String,
    val nominal: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val isBookmarked: Boolean,
    val isTrendingUp: Boolean,
    val onSaveBookmark: (DashboardItemUiState) -> Unit
) : ItemState
