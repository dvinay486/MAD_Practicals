# MSBTE Practical: Hotel Management System using GridView

This project demonstrates how to implement a **GridView** in Android, specifically tailored for a Hotel Management System.

## Step-by-Step Instructions

### Step 1: Design the Main Layout (`activity_main.xml`)
- Open `app/src/main/res/layout/activity_main.xml`.
- Replace the `TextView` with a `GridView` component.
- **Key Attributes:**
    - `android:numColumns="2"`: Defines how many columns the grid should have.
    - `android:verticalSpacing` and `android:horizontalSpacing`: Adds gaps between items.

### Step 2: Design the Grid Item Layout (`grid_item.xml`)
- Create a new layout file `app/src/main/res/layout/grid_item.xml`.
- This file defines how a **single cell** in the grid looks.
- It contains an `ImageView` for the icon and a `TextView` for the category name.

### Step 3: Create the Data Model (`HotelItem.kt`)
- In your Java/Kotlin package, define a data class `HotelItem`.
- This class holds the `name` (String) and `imageResource` (Int) for each module like "Rooms", "Bookings", etc.

### Step 4: Create the Custom Adapter (`HotelAdapter.kt`)
- To connect your data to the `GridView`, you need an **Adapter**.
- Create a class `HotelAdapter` that extends `BaseAdapter`.
- **Functions to override:**
    - `getCount()`: Tells the grid how many items to display.
    - `getView()`: Inflates `grid_item.xml` and fills it with data for each position.
    - **ViewHolder Pattern:** Used inside `getView` to optimize performance by reusing view references.

### Step 5: Initialize in `MainActivity.kt`
- Find the `GridView` using `findViewById`.
- Create a list of `HotelItem` objects (the data source).
- Instantiate `HotelAdapter` and set it to the `GridView`.
- Implement `setOnItemClickListener` to handle what happens when a student or user clicks a grid item.

## Important Concepts
- **Adapter:** Acts as a bridge between the data source and the UI component (GridView).
- **Inflation:** The process of converting an XML layout file into actual View objects.
- **BaseAdapter:** A common base class of a general-purpose adapter that can be used in ListView or GridView.

## How to Run
1. Sync your project with Gradle.
2. Run the application on an emulator or a real Android device.
3. You will see a grid of hotel management options. Click any item to see a Toast message.
