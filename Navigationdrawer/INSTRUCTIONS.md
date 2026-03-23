# Navigation Drawer with Fragments - Implementation Guide

This guide explains how to create a Navigation Drawer in Android using Fragments. This is a common UI pattern used to provide navigation to different sections of an app.

---

## Prerequisites
- Android Studio installed.
- Basic knowledge of Kotlin and XML.
- Material Design components library included in `build.gradle` (usually added by default).

---

## Step 1: Define Menu Items
Create a menu resource file to define the items that will appear in the side drawer.
- **File:** `res/menu/drawer_menu.xml`
- **Concept:** The `<menu>` tag contains `<item>` tags. Each item has an `id`, `icon`, and `title`.

## Step 2: Create Fragment Layouts
Each screen you navigate to will be a Fragment. Create separate XML layout files for each.
- **Files:** `res/layout/fragment_home.xml`, `res/layout/fragment_gallery.xml`, etc.
- **Concept:** Fragments are like mini-activities. They have their own layout files.

## Step 3: Create Fragment Classes
Create Kotlin classes for each fragment to inflate their respective layouts.
- **Files:** `HomeFragment.kt`, `GalleryFragment.kt`, etc.
- **Method Used:** `onCreateView()` - This is where you tell the Fragment which layout file to use.

## Step 4: Setup Activity Layout
Update your main activity layout to include the `DrawerLayout` and `NavigationView`.
- **File:** `res/layout/activity_main.xml`
- **Components:**
    - `DrawerLayout`: The root container that allows the drawer to slide.
    - `Toolbar`: Replaces the default Action Bar to support the drawer toggle.
    - `FrameLayout`: A placeholder (container) where Fragments will be loaded.
    - `NavigationView`: The actual sliding drawer that holds the menu.

## Step 5: Create Navigation Header
(Optional but recommended) Create a layout for the top part of the drawer (usually shows user info or app logo).
- **File:** `res/layout/nav_header.xml`

## Step 6: Implement Logic in MainActivity
Handle the opening/closing of the drawer and switching between fragments.
- **File:** `MainActivity.kt`
- **Key Classes/Methods:**
    - `ActionBarDrawerToggle`: Connects the drawer to the toolbar.
    - `setNavigationItemSelectedListener`: Detects when a user clicks a menu item.
    - `supportFragmentManager`: Manages the fragments (replacing one with another).
    - `replaceFragment()`: A custom helper function to switch fragments using a transaction.

---

## Theory Corner
- **Fragment:** A reusable portion of an app's UI. An activity can host multiple fragments.
- **DrawerLayout:** A specialized layout that allows a "drawer" view to be pulled out from the edge of the window.
- **Fragment Transaction:** The process of adding, removing, or replacing fragments at runtime. Always remember to call `.commit()`.
