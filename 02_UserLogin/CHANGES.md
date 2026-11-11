# CHANGES.md — 02_UserLogin

## Changes & Fixes Applied

### MainActivity.java
- **PDF source had NO logic**: The PDF's `MainActivity.java` contained only `onCreate()` and `setContentView()`. Added complete login logic:
  - `attemptsLeft` counter (starts at 3)
  - `submitButton` click handler: validates empty fields, checks lockout, verifies credentials, decrements attempts on failure, locks after 3 failed attempts
  - `resetButton` click handler: clears both fields, resets `infoLabel` message, resets `attemptsLeft` to 3
  - Demo credentials: **admin / admin123**

### activity_main.xml (Layout)
- **PDF constraints were broken/incomplete**: Missing vertical constraints on button row, no end-to-start constraint linking `resetButton` → `submitButton`, no bottom chain for `infoLabel`. Rebuilt the entire layout with fully-constrained views:
  - `headerImage` → constrained top-to-parent, start/end to parent
  - `usernameEditText` → constrained top-to-bottom of `headerImage` (margin 16dp)
  - `passwordEditText` → constrained top-to-bottom of `usernameEditText` (margin 8dp)
  - `resetButton` / `submitButton` → side-by-side, each `width=0dp`, constrained end-to-start and start-to-end of each other
  - `infoLabel` → constrained top-to-bottom of `resetButton` (margin 16dp)

### header_image.xml (Drawable)
- **Not provided in PDF**: Created a vector drawable (300×150dp) with an indigo gradient background and "LOGIN" text rendered as SVG paths (no `<text>` elements, which are unsupported in Android vectors).

### minSdk
- **Fixed**: PDF referenced API 15; corrected to minSdk 24 per global config.

### New Files Created (not in PDF)
- `settings.gradle`, `build.gradle` (root), `gradle.properties`
- `gradle/wrapper/gradle-wrapper.properties` (Gradle 8.9)
- `app/build.gradle` (AGP 8.5.2, namespace, deps)
- `app/proguard-rules.pro`
- `AndroidManifest.xml`
- `ic_launcher_background.xml` / `ic_launcher_foreground.xml`
- `mipmap-anydpi-v26/ic_launcher.xml` / `ic_launcher_round.xml`
- `values/strings.xml`, `values/colors.xml`, `values/themes.xml`
