# Develop a program to design Checkbox and Radiobutton

This explains how to create an Android application that uses CheckBoxes and RadioButtons to collect user input.

## Step 1: Design the UI (Layout)
Open `app/src/main/res/layout/activity_main.xml`. We use a `LinearLayout` with `vertical` orientation.

### Key Components Used:
- **CheckBox**: Used when the user can select multiple options from a list (e.g., Skills).
- **RadioGroup**: A container for `RadioButton` components. It ensures that only one option can be selected at a time.
- **RadioButton**: Used for mutually exclusive selections (e.g., Gender).
- **Button**: To trigger the action of collecting and displaying the selected data.
- **TextView**: To display the final result.

## Step 2: Write the Logic (MainActivity.kt)
Open `app/src/main/java/com/example/checkboxradiobutton/MainActivity.kt`.

### Key Methods and Functions:
1. **`setContentView(R.layout.activity_main)`**: Links the Kotlin code to the XML layout.
2. **`findViewById<T>(R.id.id_name)`**: Finds a view by its ID defined in XML and returns it as a specific type (CheckBox, Button, etc.).
3. **`setOnClickListener { ... }`**: A listener that waits for a user click event on a button.
4. **`isChecked`**: A property of `CheckBox` that returns `true` if it is checked.
5. **`checkedRadioButtonId`**: A property of `RadioGroup` that returns the ID of the selected `RadioButton`.
6. **`StringBuilder`**: A helper class used to build strings efficiently by appending text.

## Step 3: How to Run
1. Connect your Android device or start an Emulator.
2. Click the **Run** button (Green Play Icon) in Android Studio.
3. Select your skills using Checkboxes.
4. Select your gender using Radio Buttons.
5. Click **Submit** to see the summary at the bottom.
