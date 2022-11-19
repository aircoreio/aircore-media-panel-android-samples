package io.aircore.panel.mediasample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.aircore.panel.common.Client
import io.aircore.panel.common.ClientListener
import io.aircore.panel.common.theme.PanelColors
import io.aircore.panel.common.theme.PanelIconography
import io.aircore.panel.common.theme.PanelsTheme
import io.aircore.panel.media.MediaPanel
import io.aircore.panel.media.config.CollapsedStateOptions
import io.aircore.panel.media.config.ExpandedStateOptions
import io.aircore.panel.media.config.MediaPanelConfiguration
import io.aircore.panel.media.config.MediaPanelStrings
import io.aircore.panel.mediasample.ui.theme.Green500
import io.aircore.panel.mediasample.ui.theme.MyAppTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    // For information and best practices on creating and using a Publishable API Key,
    // please refer to docs (https://docs.airtime.com/key-concepts#apps-and-api-keys).
    private val publishableApiKey =
        BuildConfig.PUBLISHABLE_API_KEY ?: "YOUR_PUBLISHABLE_API_KEY_HERE"

    private val userId = UUID.randomUUID().toString()
    private val displayName = "Han Solo"
    private val avatarUrl = "https://picsum.photos/seed/$userId/300/300"

    private val channelId = "sample-app"

    // Option 1 : Use a Publishable API Key directly from the developer console
    private val client by lazy {
        Client.createWithPublishableApiKey(
            application = application,
            key = publishableApiKey,
            userId = userId
        ).apply {
            // Choose a name and profile picture that will be used to show the user on the MediaPanel
            userDisplayName = displayName
            userAvatarUrl = avatarUrl

            // Register event handlers for various events that are of interest for your host app
            addListener(object : ClientListener {
                override fun onSessionAuthTokenInvalid() {
                    //Request the server for a new token
                }
            })
        }
    }

    // Option 2 :Use a session auth token provided by your server by communication with Aircore's
    // provisioning service using the Secret API key
    // private val client = Client.createWithSessionAuthToken(application, authToken, userId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        client.connect(channelId)

        setContent {
            MyAppTheme {
                PanelsTheme(
                    // colors = remember { customColors() },
                    // icons = remember { customIcons() }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            // In Collapsed state the MediaPanel will fill its parent.
                            Box(Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                            ) {
                                MediaPanel(
                                    client = client,
                                    channelId = channelId,
                                    // configuration = remember { customConfiguration() }
                                )
                            }
                        }
                    ) {
                        Box(Modifier.padding(it))
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        client.disconnect(channelId)
        client.destroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Although out of the scope of this Sample, in order for your call state to survive Configuration
        // Changes like device rotation, consider moving your Client instance to a ViewModel
        // or a Service
    }

    // Customization Examples

    private fun customIcons(): PanelIconography {
        return PanelIconography(
            collapse = R.drawable.common_ic_collapse,
            expand = R.drawable.common_ic_expand,
            share = R.drawable.common_ic_share,
            overflowMenu = R.drawable.common_ic_overflow,
            join = R.drawable.common_ic_headphones,
            leave = R.drawable.common_ic_close,
            micEnabled = R.drawable.common_ic_mic_on,
            micDisabled = R.drawable.common_ic_mic_off
        )
    }

    private fun customColors(): PanelColors {
        return PanelColors(
            primary = Green500,
            primaryContrast = Color.White,
            danger = Color.Red,
            dangerContrast = Color.White,
            background = Color.White,
            text = Color.Black,
            subtext = Color.Black.copy(alpha = 0.7f),
            border = Color.Black.copy(alpha = 0.1f)
        )
    }

    private fun customConfiguration(): MediaPanelConfiguration {
        return MediaPanelConfiguration(
            panelTitle = "My Panel Title",
            panelSubtitle = "My Panel Subtitle",
            showMicrophoneButton = true,
            strings = customStrings(),
            collapsedStateOptions = CollapsedStateOptions.Bar(
                maxAvatars = 3,
                panelTitle = "Collapsed Title",
                panelSubtitle = "Collapsed Subtitle",
                joinButtonText = "Connect",
                joiningButtonText = "Connecting...",
                leaveButtonText = "Disconnect"
            ),
            expandedStateOptions = ExpandedStateOptions(
                panelTitle = "Expanded Title",
                panelSubtitle = "Expanded Title",
                joinButtonText = "Enter",
                joiningButtonText = "Entering",
                leaveButtonText = "Leave",
            ),
        )
    }

    private fun customStrings(): MediaPanelStrings {
        return MediaPanelStrings(
            joinButton = "Join",
            joiningButton = "Joining...",
            leaveButton = "Leave",
            retryButton = "Retry",
            emptyCallTitle = "No one is on the call yet.",
            emptyCallSubtitle = "Tap Join below to be the first!",
            joinButtonTooltip = "Tap Join to start the audio session",
            channelIsFullLabel = "The channel is full",
            usersActiveLabel = "Active",
            genericErrorLabel = "Something went wrong..."
        )
    }
}

