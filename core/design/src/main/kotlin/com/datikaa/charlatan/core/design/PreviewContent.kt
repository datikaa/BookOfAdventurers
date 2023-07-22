package com.datikaa.charlatan.core.design

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

private const val DEVICES = "devices"

@Preview(
    name = "Phone",
    group = DEVICES,
    device = Devices.PIXEL_2,
)
@Preview(
    name = "Tablet",
    group = DEVICES,
    device = Devices.PIXEL_C,
)
annotation class DevicePreviews

internal val LipSum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
        " Integer gravida ex ullamcorper," +
        " interdum lacus ac, bibendum ipsum."