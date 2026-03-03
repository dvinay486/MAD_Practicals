# Android Intent Implementation Guide (Explicit & Implicit)

This guide will help you understand how to navigate between activities within your app (Explicit Intent) and how to interact with other apps, like opening a website (Implicit Intent).

---

## What is an Intent?
An **Intent** is an abstract description of an operation to be performed. It is most commonly used to start activities.

1.  **Explicit Intent:** You specify exactly which class (Activity) you want to start. Used for internal navigation.
2.  **Implicit Intent:** You specify an action you want to perform (e.g., "view a web page"). The Android system then finds an app on the device that can handle that action.

---

## Step-by-Step Implementation

### Step 1: Create the Main Layout (`activity_main.xml`)
Define two buttons in your main layout. One for the Explicit Intent and one for the Implicit Intent.
- Use `android:id` to identify the buttons in your code.
- Use constraints or linear layout to position them.

### Step 2: Create a Second Activity
To test the Explicit Intent, you need a destination.
1.  Right-click your package name -> **New** -> **Activity** -> **Empty Views Activity**.
2.  Name it `SecondActivity`.
3.  This will automatically create `SecondActivity.kt` and `activity_second.xml`.

### Step 3: Register the Activity in `AndroidManifest.xml`
Every activity must be declared in the manifest file. (If you used the "New Activity" wizard, Android Studio does this for you).
```xml
<activity android:name=".SecondActivity" />
```

### Step 4: Write the Logic in `MainActivity.kt`

#### For Explicit Intent:
1.  Find your button using `findViewById`.
2.  Create an `Intent` object: `Intent(this, SecondActivity::class.java)`.
3.  Call `startActivity(intent)`.

#### For Implicit Intent:
1.  Define the URI (Uniform Resource Identifier) for the website: `val uri = Uri.parse("https://www.google.com")`.
2.  Create an `Intent` with the action `ACTION_VIEW`: `Intent(Intent.ACTION_VIEW, uri)`.
3.  Call `startActivity(intent)`.

---

## Key Methods Explained

- **`setContentView(layout_res_id)`**: This method links the Kotlin/Java class to its visual XML design.
- **`findViewById(id)`**: This method is used to get a reference to a UI element (like a Button) defined in XML so you can interact with it in code.
- **`setOnClickListener { ... }`**: This is an event listener that executes the code inside the curly braces when the user taps the button.
- **`startActivity(intent)`**: This is a method of the `Context` class that tells the Android System to process the Intent and launch the required component.
- **`Uri.parse(string)`**: Converts a simple web address string into a `Uri` object that the Intent system understands.

---

## Summary of Files Created/Modified
1.  `activity_main.xml`: The UI for the first screen.
2.  `MainActivity.kt`: The logic to handle button clicks and trigger intents.
3.  `activity_second.xml`: The UI for the second screen.
4.  `SecondActivity.kt`: The class for the second screen.
5.  `AndroidManifest.xml`: Registration of all activities.
