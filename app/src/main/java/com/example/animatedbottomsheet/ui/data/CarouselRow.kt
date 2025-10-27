package com.example.animatedbottomsheet.ui.data

import com.example.animatedbottomsheet.R

/**
 * title here is optional
 */
data class CarouselRow(
    val id: String,
    val icons: List<Int>,
    val reverse: Boolean,
    val title: String? = null
)

// Top-level (outside any class)
val COMMON_ICONS = listOf(
    R.drawable.ic_list_item_1_mod,
    R.drawable.ic_list_item_2_mod,
    R.drawable.ic_list_item_3_mod,
    R.drawable.ic_list_item_4_mod,
    R.drawable.ic_list_item_5_mod,
    R.drawable.ic_list_item_6_mod
)

val PLACES_ICONS = listOf(
    R.drawable.ic_list_item_5_mod,
    R.drawable.ic_list_item_6_mod,
    R.drawable.ic_list_item_7_mod,
    R.drawable.ic_list_item_8_mod,
    R.drawable.ic_list_item_9_mod,
    R.drawable.ic_list_item_10_mod
)

val FEATURES_ICONS = listOf(
    R.drawable.ic_list_item_11_mod,
    R.drawable.ic_list_item_3_mod,
    R.drawable.ic_list_item_4_mod,
    R.drawable.ic_list_item_6_mod,
    R.drawable.ic_list_item_7_mod,
    R.drawable.ic_list_item_9_mod
)

val DEFAULT_ICONS = listOf(
    R.drawable.ic_list_item_11_mod,
    R.drawable.ic_list_item_6_mod,
    R.drawable.ic_list_item_4_mod,
    R.drawable.ic_list_item_8_mod,
    R.drawable.ic_list_item_3_mod,
    R.drawable.ic_list_item_5_mod
)

val CAROUSEL_ROWS = listOf(
    CarouselRow("row1", COMMON_ICONS, reverse = false),
    CarouselRow("row2", PLACES_ICONS, reverse = true),
    CarouselRow("row3", DEFAULT_ICONS, reverse = false),
    CarouselRow("row4", FEATURES_ICONS, reverse = true),
    CarouselRow("row5", COMMON_ICONS, reverse = false),
    CarouselRow("row6", PLACES_ICONS, reverse = true),
    CarouselRow("row7", DEFAULT_ICONS, reverse = false)
)