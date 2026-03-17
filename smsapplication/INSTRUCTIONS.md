# SMS Application: Step-by-Step Tutorial

This guide provides a detailed walkthrough for building a basic SMS sending application in Android.

## Prerequisites
*   Android Studio installed.
*   Basic knowledge of Kotlin and XML.
*   A physical Android device (as SMS cannot be sent from most emulators).

---

## Step 1: Add Permissions in AndroidManifest.xml
To send an SMS, the app needs the user's permission.
1.  Open `app/src/main/AndroidManifest.xml`.
2.  Add the `<uses-permission android:name="android.permission.SEND_SMS" />` tag before the `<application>` tag.
3.  **Theory**: This tells the Android system that your app intends to use the SMS service.

## Step 2: Design the User Interface (UI)
The UI defines how your app looks.
1.  Open `app/src/main/res/layout/activity_main.xml`.
2.  Use a `ConstraintLayout` to arrange:
    *   An `EditText` for the **Phone Number** (set `inputType="phone"`).
    *   An `EditText` for the **Message** content.
    *   A `Button` to trigger the **Send** action.
3.  **Theory**: Every UI element (Widget) needs a unique `android:id` so we can reference it in our Kotlin code.

## Step 3: Implement the Logic in MainActivity.kt
This is where the functionality happens.
1.  Open `app/src/main/java/com/example/smsapplication/MainActivity.kt`.
2.  **Initialize Views**: Use `findViewById` to link your XML elements to Kotlin variables.
3.  **Handle Clicks**: Set an `OnClickListener` on the button.
4.  **Request Runtime Permissions**: Since Android 6.0, users must approve "Dangerous Permissions" (like sending SMS) while the app is running, not just at install time.
5.  **Use SmsManager**: This is the built-in Android class used to send text messages. Use `smsManager.sendTextMessage(phoneNumber, null, message, null, null)`.

## Step 4: Run the Application
1.  Connect your physical Android device via USB.
2.  Enable **USB Debugging** in Developer Options.
3.  Click the **Run** (green play) button in Android Studio.
4.  Enter a phone number and message, click "Send SMS", and allow the permission when prompted.

---

## Key Classes Used:
*   **AppCompatActivity**: The base class for activities to ensure they look consistent across different Android versions.
*   **SmsManager**: The primary API for managing SMS operations.
*   **Toast**: A small popup used to show brief feedback to the user (e.g., "SMS Sent").
*   **ActivityCompat**: A helper class for handling runtime permissions.
