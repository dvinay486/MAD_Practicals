# Bluetooth & Wi-Fi Control - Step-by-Step Instructions

This guide explains how to build a simple Android application to control Bluetooth and Wi-Fi services.

## Prerequisites
* Android Studio installed.
* A basic understanding of Kotlin and XML.
* An Android device (Physical device is recommended for Bluetooth/Wi-Fi testing).

---

## Step 1: Set up Permissions
To interact with hardware like Bluetooth and Wi-Fi, you must request permission from the user in the `AndroidManifest.xml` file.

* **BLUETOOTH**: Basic connectivity.
* **BLUETOOTH_ADMIN**: To change settings (On/Off).
* **ACCESS_WIFI_STATE**: To check Wi-Fi status.
* **CHANGE_WIFI_STATE**: To toggle Wi-Fi.

## Step 2: Design the User Interface (XML)
We use a `LinearLayout` to stack buttons vertically. Each button has an `android:id` which allows us to find and use it in our Kotlin code.

* **Button**: A clickable UI element.
* **TextView**: Used to display labels or titles.

## Step 3: Write the Logic (Kotlin)
In `MainActivity.kt`, we perform the following:

1. **Initialize Managers**:
   * `BluetoothAdapter`: Controls the Bluetooth hardware.
   * `WifiManager`: Controls the Wi-Fi hardware.
2. **Setup Listeners**: We use `.setOnClickListener {}` to define what happens when a button is pressed.
3. **Handle API Versions**: Android has tightened security over time.
   * For Wi-Fi (Android 10+): We cannot toggle it directly; we open a "Settings Panel" instead.
   * For Bluetooth: We send an "Intent" to the system to ask the user to enable it.

## Key Classes & Methods Used
* **BluetoothAdapter.getDefaultAdapter()**: Returns the device's Bluetooth hardware.
* **WifiManager**: System service to manage Wi-Fi.
* **Intent**: An object used to request an action from another app component or the system.
* **Toast**: A small popup message to provide feedback to the user.

---

## How to Run
1. Connect your Android device via USB.
2. Click the **Run** button in Android Studio.
3. Grant permissions if prompted on the device.
4. Use the buttons to toggle Bluetooth and Wi-Fi!
