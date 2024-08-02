package com.datikaa.bookofadventurers.core.design


import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

private const val DEVICES = "devices"

@Deprecated("Migrate to KMM Preview")
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
