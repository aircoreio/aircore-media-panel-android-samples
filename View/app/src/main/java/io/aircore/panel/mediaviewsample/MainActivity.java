package io.aircore.panel.mediaviewsample;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import io.aircore.panel.common.Client;
import io.aircore.panel.common.ClientListener;
import io.aircore.panel.common.theme.PanelColors;
import io.aircore.panel.common.theme.PanelIconography;
import io.aircore.panel.media.config.CollapsedStateOptions;
import io.aircore.panel.media.config.ExpandedStateOptions;
import io.aircore.panel.media.config.MediaPanelConfiguration;
import io.aircore.panel.media.config.MediaPanelStrings;
import io.aircore.panel.media.view.MediaPanelView;
import io.aircore.panel.media.view.PanelColorsHelper;

public class MainActivity extends AppCompatActivity {
    
    public static final String PUBLISHABLE_API_KEY = "YOUR_PUBLISHABLE_API_KEY_HERE";
    
    private Client client;

    private final String userId = UUID.randomUUID().toString();
    private final String displayName = "Han Solo";
    private final String avatarUrl = "https://picsum.photos/seed/" + userId + "/300/300";

    private final String channelId = "sample-app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = createClient();
        client.connect(channelId);

        setContentView(R.layout.media_panel_activity);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        MediaPanelView mediaPanelView = findViewById(R.id.media_panel_view);

//        PanelsTheme customTheme = new PanelsTheme(
//                customColors(),
//                customIcons()
//        );

//        MediaPanelConfiguration customConfiguration = customConfiguration();

        mediaPanelView.connect(
                client, 
                channelId
//                customTheme, 
//                customConfiguration
        );
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();

        client.disconnect(channelId);
        client.destroy();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Although out of the scope of this Sample, in order for your call state to survive Configuration
        // Changes like device rotation, consider moving your Client instance to a ViewModel
        // or a Service
    }

    private Client createClient() {
        Client client = Client.createWithPublishableApiKey(
                getApplication(),
                getPublishableApiKey(),
                userId
        );
        client.setUserDisplayName(displayName);
        client.setUserAvatarUrl(avatarUrl);
        client.addListener(new ClientListener() {
            @Override
            public void onSessionAuthTokenNearingExpiry() {
            }

            @Override
            public void onSessionAuthTokenInvalid() {
            }

            @Override
            public void onSessionAuthTokenMismatch() {
            }

            @Override
            public void onLocalUserJoined(@NonNull String channelId) {
            }

            @Override
            public void onLocalUserLeft(@NonNull String channelId) {
            }

            @Override
            public void onError(@NonNull Exception e) {
            }
        });

        return client;
    }
    
    private PanelIconography customIcons() {
        return new PanelIconography(
                R.drawable.common_ic_collapse, // collapse
                R.drawable.common_ic_expand, // expand
                R.drawable.common_ic_share, // share
                R.drawable.common_ic_overflow, // overflowMenu
                R.drawable.common_ic_headphones, // join
                R.drawable.common_ic_close,  // leave
                R.drawable.common_ic_mic_on, // micEnabled
                R.drawable.common_ic_mic_off // micDisabled
        );
    }
    
    private PanelColors customColors() {
        return PanelColorsHelper.fromColorInt(
                getColor(R.color.green_500), // primary
                getColor(R.color.white), // primaryContrast
                getColor(R.color.error), // danger
                getColor(R.color.white), // dangerContrast
                getColor(R.color.white), // background
                getColor(R.color.black), // text
                getColor(android.R.color.darker_gray), // subtext
                getColor(R.color.border) // border
        );
    }

    private MediaPanelConfiguration customConfiguration() {
        return new MediaPanelConfiguration(
                "My Panel Title", // panelTitle
                "My Panel Subtitle", // panelSubtitle
                true, // showMicrophoneButton
                customStrings(), // strings
                new CollapsedStateOptions.Bar( // collapsedStateOptions
                        3, // maxAvatars
                        "Collapsed Title", // panelTitle
                        "Collapsed Subtitle", // panelSubtitle
                        "Connect", // joinButtonText
                        "Connecting...", // joiningButtonText
                        "Disconnect" // leaveButtonText
                ),
                new ExpandedStateOptions( // expandedStateOptions
                        "Expanded Title", // panelTitle
                        "Expanded Title", // panelSubtitle
                        "Enter", // joinButtonText
                        "Entering", // joiningButtonText
                        "Leave" // leaveButtonText
                ));
    }

    private MediaPanelStrings customStrings() {
        return new MediaPanelStrings(
                "Join", // joinButton
                "Joining...", // joiningButton
                "Leave", // leaveButton
                "Retry", // retryButton
                "No one is on the call yet.", // emptyCallTitle
                "Tap Join below to be the first!", // emptyCallSubtitle
                "Tap Join to start the audio session", // joinButtonTooltip
                "The channel is full", // channelIsFullLabel
                "Active", // usersActiveLabel
                "Something went wrong..." // genericErrorLabel
        );
    }

    private static String getPublishableApiKey() {
        if (BuildConfig.PUBLISHABLE_API_KEY == null || BuildConfig.PUBLISHABLE_API_KEY.isEmpty()) {
            return PUBLISHABLE_API_KEY;
        } else {
            return BuildConfig.PUBLISHABLE_API_KEY;
        }
    }
}