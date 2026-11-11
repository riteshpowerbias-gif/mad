# Changes Log — 01_HelloWorld

## Source Note

The original PDF for Experiment 1 did not include any source code or project files.
All files in this project were created from scratch based on standard Android project
structure and the experiment requirements.

## Files Created from Scratch

| File | Description |
|------|-------------|
| `settings.gradle` | Project settings with repo config and module inclusion |
| `build.gradle` | Root build file with AGP 8.5.2 plugin declaration |
| `gradle.properties` | AndroidX, Jetifier, and JVM heap settings |
| `gradle/wrapper/gradle-wrapper.properties` | Gradle 8.9 wrapper distribution config |
| `.gitignore` | Standard Android gitignore |
| `app/build.gradle` | App module build config (compileSdk 35, Java 17) |
| `app/proguard-rules.pro` | Empty ProGuard rules file |
| `app/src/main/AndroidManifest.xml` | App manifest with MainActivity launcher |
| `app/src/main/java/com/example/exno1/MainActivity.java` | Main activity extending AppCompatActivity |
| `app/src/main/res/layout/activity_main.xml` | ConstraintLayout with centered bold "Hello World!" TextView |
| `app/src/main/res/values/strings.xml` | String resources (app_name, hello_world) |
| `app/src/main/res/values/colors.xml` | Material color palette |
| `app/src/main/res/values/themes.xml` | Theme.HelloWorld based on MaterialComponents |
| `app/src/main/res/drawable/ic_launcher_background.xml` | Green vector background for adaptive icon |
| `app/src/main/res/drawable/ic_launcher_foreground.xml` | White diamond shape vector foreground |
| `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml` | Adaptive icon definition |
| `app/src/main/res/mipmap-anydpi-v26/ic_launcher_round.xml` | Round adaptive icon definition |
