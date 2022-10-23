package com.memksim.exchanger.ui.dashboard

data class DashboardUiState(
    val currencyList: List<DashboardItemUiState>
)

data class DashboardItemUiState(
    val charCode: String,
    val nominal: String,
    val name: String,
    val value: Double,
    val previous: Double,
    val isBookmarked: Boolean,
    val isTrendingUp: Boolean
)
