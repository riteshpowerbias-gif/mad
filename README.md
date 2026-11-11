# U21CS503 Mobile Application Development Laboratory
**KPR Institute of Engineering and Technology**

This repository contains ten complete, ready-to-open Android Studio projects for the MAD Lab record (U21CS503).

---

## Project Table

| # | Folder | App Name | Package |
|---|--------|----------|---------|
| 01 | `01_HelloWorld` | Hello World | `com.example.exno1` |
| 02 | `02_UserLogin` | User Login | `com.example.loginapp` |
| 03 | `03_Calculator` | Calculator | `com.example.calculator2` |
| 04 | `04_CurrentLocation` | Get User Location | `com.example.getuserlocation` |
| 05 | `05_UserRegistrationLogin` | User Registration | `com.example.userregistration` |
| 06 | `06_BMICalculator` | BMI Calculator | `com.example.bmicalculator` |
| 07 | `07_ExpenseManager` | Expense Manager | `com.example.expensemanager` |
| 08 | `08_KmToMiles` | Km to Miles | `com.example.kmtomiles` |
| 09 | `09_StudentDatabaseCloud` | Student Database | `com.example.studentdatabase` |
| 10 | `10_TodoManager` | Todo Manager | `com.example.todomanagement` |

---

## Build Tool Versions (all 10 projects)

| Tool | Version |
|------|---------|
| Android Gradle Plugin (AGP) | 8.5.2 |
| Gradle | 8.9 |
| `compileSdk` / `targetSdk` | 35 (Android 15) |
| `minSdk` | 24 (Android 7.0) |
| Java compatibility | 17 |
| `androidx.appcompat:appcompat` | 1.7.0 |
| `com.google.android.material:material` | 1.12.0 |
| `androidx.constraintlayout:constraintlayout` | 2.1.4 |
| `com.google.android.gms:play-services-location` | 21.3.0 (Exp 04 only) |
| Firebase BoM | 33.7.0 (Exp 09 only) |
| `com.google.gms:google-services` | 4.4.2 (Exp 09 only) |

> **JDK requirement:** JDK 17 or later must be installed and configured in Android Studio  
> (File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK)

---

## How to Open Each Project in Android Studio

Each project is a **standalone** Android Studio project. Open them **one at a time**:

1. Launch Android Studio.
2. Go to **File → Open** (or click **Open** on the welcome screen).
3. Navigate to `d:\mad-lab-experiments\` and select **one project folder** (e.g. `01_HelloWorld`).
4. Click **OK** and wait for the **Gradle sync** to complete.
   - Android Studio will download the Gradle wrapper (~100 MB) and all dependencies on first sync.
   - This requires an internet connection.
5. Create or select an **AVD (emulator)** via the AVD Manager, or connect a physical device.
6. Click the **Run ▶** button (or press `Shift+F10`).

> **Do NOT open the root `mad-lab-experiments` folder** as a project — open each numbered subfolder individually.

---

## Demo Credentials (Experiment 02 – User Login)

| Field | Value |
|-------|-------|
| Username | `admin` |
| Password | `admin123` |

The login screen shows remaining attempts (max 3). The Reset button clears both fields and resets the counter.

---

## Location Testing (Experiment 04 – Current Location)

The app uses `FusedLocationProviderClient` to get the device's current coordinates.

**On the Android Emulator:**
1. Run the app on an AVD (emulator).
2. Click the `...` (Extended Controls) button in the emulator toolbar.
3. Go to **Location**.
4. Enter latitude and longitude (e.g. `11.0168°N, 76.9558°E` for Coimbatore).
5. Click **Set Location**.
6. The app will display the coordinates.

**Permissions:** The app requests `ACCESS_FINE_LOCATION` and `ACCESS_COARSE_LOCATION` at runtime. Grant them when prompted. Background location is NOT requested (not needed for foreground use).

---

## Firebase Setup (Experiment 09 – Student Database Cloud)

This project uses **Firebase Realtime Database**. The build will succeed without `google-services.json`, but saving/loading students requires Firebase to be configured.

**Step-by-step:**

1. Go to [https://console.firebase.google.com/](https://console.firebase.google.com/)
2. Click **Add project** → give it a name (e.g. `StudentDB`) → Continue.
3. In the project dashboard, click **Add app** → choose **Android** (the robot icon).
4. Enter the package name exactly: `com.example.studentdatabase`
5. Click **Register app**, then **Download `google-services.json`**.
6. Place the downloaded `google-services.json` file inside:  
   `09_StudentDatabaseCloud/app/google-services.json`
7. In the Firebase console: **Build → Realtime Database → Create database**.
8. Choose **Start in test mode** (allows open read/write for development).
9. Open the project in Android Studio, perform a Gradle sync, then run.

**Without `google-services.json`:** The app will show a Toast message explaining Firebase is not configured. It will not crash.

---

## Notes

- All projects use **AndroidX** only. No `android.support.*` libraries.
- All projects use **Java** (not Kotlin) and **XML layouts** (not Jetpack Compose).
- Gradle wrapper files (`gradle-wrapper.properties`) are included; Android Studio will download the wrapper JAR automatically on first sync.
- `local.properties` and `.idea/` are excluded (Android Studio generates them).
- Each project has a `CHANGES.md` documenting what was fixed vs. the PDF source.

