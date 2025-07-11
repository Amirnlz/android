package com.x8bit.bitwarden.ui.vault.feature.vault

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bitwarden.ui.platform.components.icon.model.IconData
import com.bitwarden.ui.platform.components.model.CardStyle
import com.bitwarden.ui.platform.resource.BitwardenDrawable
import com.bitwarden.ui.platform.theme.BitwardenTheme
import com.x8bit.bitwarden.ui.platform.components.listitem.BitwardenListItem
import com.x8bit.bitwarden.ui.platform.components.listitem.SelectionItemData
import com.x8bit.bitwarden.ui.vault.feature.itemlisting.model.ListingItemOverflowAction
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

/**
 * A Composable function that displays a row item for different types of vault entries.
 *
 * @param startIcon The [IconData] object used to draw the icon at the start of the item.
 * @param startIconTestTag The test tag for the [startIcon].
 * @param label The primary text label to display for the item.
 * @param supportingLabel An optional secondary text label to display beneath the primary label.
 * @param onClick The lambda to be invoked when the item is clicked.
 * @param overflowOptions List of options to display for the item.
 * @param onOverflowOptionClick The lambda to be invoked when an overflow option is clicked.
 * @param cardStyle Indicates the type of card style to be applied.
 * @param modifier An optional [Modifier] for this Composable, defaulting to an empty Modifier.
 * This allows the caller to specify things like padding, size, etc.
 */
@Composable
fun VaultEntryListItem(
    startIcon: IconData,
    startIconTestTag: String,
    label: String,
    onClick: () -> Unit,
    overflowOptions: ImmutableList<ListingItemOverflowAction.VaultAction>,
    onOverflowOptionClick: (ListingItemOverflowAction.VaultAction) -> Unit,
    cardStyle: CardStyle,
    modifier: Modifier = Modifier,
    trailingLabelIcons: ImmutableList<IconData> = persistentListOf(),
    supportingLabel: String? = null,
) {
    BitwardenListItem(
        modifier = modifier,
        label = label,
        supportingLabel = supportingLabel,
        startIcon = startIcon,
        startIconTestTag = startIconTestTag,
        trailingLabelIcons = trailingLabelIcons,
        onClick = onClick,
        selectionDataList = overflowOptions
            .map { option ->
                SelectionItemData(
                    text = option.title(),
                    onClick = { onOverflowOptionClick(option) },
                )
            }
            .toImmutableList(),
        cardStyle = cardStyle,
    )
}

@Preview
@Composable
private fun VaultEntryListItem_preview() {
    BitwardenTheme {
        VaultEntryListItem(
            startIcon = IconData.Local(BitwardenDrawable.ic_globe),
            startIconTestTag = "Test Tag",
            label = "Example Login",
            supportingLabel = "Username",
            onClick = {},
            overflowOptions = persistentListOf(),
            onOverflowOptionClick = {},
            cardStyle = CardStyle.Full,
            modifier = Modifier,
        )
    }
}
