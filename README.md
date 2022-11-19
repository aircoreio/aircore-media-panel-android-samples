
### MediaPanel SDK Android Samples
The samples in this repo demonstrate how to use the Aircore MediaPanel SDK.

### Getting Started

#### [Compose](https://docs.aircore.io/getting-started/sync-android-getting-started)
#### [View](https://docs.aircore.io/getting-started/sync-view-android-getting-started)

### API References

#### [Compose](https://docs.aircore.io/android/mediapanel/api-reference)
#### [View](https://docs.aircore.io/android/mediapanelview/api-reference)

### Samples
#### [Compose](https://github.com/aircoreio/aircore-media-panel-android-samples/tree/main/Compose)
#### [View](https://github.com/aircoreio/aircore-media-panel-android-samples/tree/main/View)

#### Setup
```
git clone https://github.com/aircoreio/aircore-media-panel-android-samples.git
```
##### Launch
1. Ensure you have a modern version of Android Studio installed.
2. Open Android Studio
3. Click "Open Project"
4. Navigate to the directory where you checked out the code
5. For the Compose sample, select the Compose folder
6. For the View sample, select the View folder

#### Customization
1. Open the MainActivity and un-comment the customization code and modify its values to your taste.
2. For the Compose sample, you can also modify the App's MaterialTheme values to see how it affects the out of the box `MediaPanel`.
3. For the View sample, you can modify the App's Theme and Styles to see how it affects the out of the box `MediaPanelView`.

##### Compose
```kotlin
private val publishableApiKey = BuildConfig.PUBLISHABLE_API_KEY ?: "YOUR_PUBLISHABLE_API_KEY_HERE" // Replace with a Publishable API Key from the developer dashboard.
private val userId = UUID.randomUUID().toString() // Replace with any unique ID that represents your user in your system.
private val displayName = "Han Solo" // Replace with any name that represents your user in your system.
private val avatarUrl = "https://picsum.photos/seed/$userId/300/300" // Replace with a url for a profile picture that represents your user in your system.
private val channelId = "sample-app" // Replace with any unique name/id that represents a channel in your system.
```
##### View
```java
public static final String PUBLISHABLE_API_KEY = "YOUR_PUBLISHABLE_API_KEY_HERE"; // Replace with a Publishable API Key from the developer dashboard.
private final String userId = UUID.randomUUID().toString(); // Replace with any unique ID that represents your user in your system.
private final String displayName = "Han Solo"; // Replace with any name that represents your user in your system.
private final String avatarUrl = "https://picsum.photos/seed/" + userId + "/300/300";  // Replace with a url for a profile picture that represents your user in your system.
private final String channelId = "sample-app"; // Replace with any unique name/id that represents a channel in your system.
```

##### Running the Sample
1. Run a Gradle Sync
2. Choose an emulator or an actual device
3. Tap the ▷ button to run the sample