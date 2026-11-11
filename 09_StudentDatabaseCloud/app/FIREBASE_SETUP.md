# Firebase Setup Instructions

This app requires Firebase Realtime Database. Follow these steps:

1. Go to https://console.firebase.google.com/
2. Click "Add project" and create a new project (e.g. "StudentDatabase")
3. In the project, click "Add app" and select Android
4. Enter package name: com.example.studentdatabase
5. Download the generated google-services.json file
6. Place google-services.json inside the app/ folder (next to this file)
7. In Firebase console: Build > Realtime Database > Create database
8. Choose "Start in test mode" (for development)
9. Note your database URL (e.g. https://your-project-default-rtdb.firebaseio.com/)
10. Sync the project in Android Studio and run

NOTE: Without google-services.json, the app will show a Toast explaining Firebase is not configured and will display an empty list. It will NOT crash.
