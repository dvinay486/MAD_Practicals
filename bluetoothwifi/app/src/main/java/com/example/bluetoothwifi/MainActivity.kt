package com.example.bluetoothwifi

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * MainActivity: The main entry point of our application.
 * This class handles the user interface and the logic for Bluetooth and Wi-Fi.
 */
class MainActivity : AppCompatActivity() {

    // BluetoothAdapter: Represents the local device Bluetooth adapter.
    // It is used for performing fundamental Bluetooth tasks.
    var bluetoothAdapter: BluetoothAdapter? = null
    
    // WifiManager: Provides the primary API for managing all aspects of Wi-Fi connectivity.
    private lateinit var wifiManager: WifiManager

    /**
     * registerForActivityResult: A modern way to request permissions and handle the result.
     * This launcher will be used to ask for Bluetooth permissions on Android 12 (API 31) and above.
     */
    val requestBluetoothPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted, proceed to enable Bluetooth
            enableBluetooth()
        } else {
            // Permission denied, show a message to the user
            Toast.makeText(this, "Bluetooth permission is required to turn it on.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing BluetoothManager and BluetoothAdapter.
        // Starting from Android 4.3 (API 18), we get the adapter through BluetoothManager.
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter

        // Initializing WifiManager.
        // We use applicationContext to avoid memory leaks.
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        // Finding UI elements (Buttons) defined in the activity_main.xml layout.
        val btnBluetoothOn = findViewById<Button>(R.id.btnBluetoothOn)
        val btnBluetoothOff = findViewById<Button>(R.id.btnBluetoothOff)
        val btnWifiOn = findViewById<Button>(R.id.btnWifiOn)
        val btnWifiOff = findViewById<Button>(R.id.btnWifiOff)

        // --- BLUETOOTH ON ---
        btnBluetoothOn.setOnClickListener {
            // Checking if the device runs on Android 12 or higher.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // For Android 12+, we must explicitly check for BLUETOOTH_CONNECT permission.
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // If not granted, request it.
                    requestBluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
                } else {
                    enableBluetooth()
                }
            } else {
                // For older versions, we can call enableBluetooth directly.
                enableBluetooth()
            }
        }

        // --- BLUETOOTH OFF ---
        btnBluetoothOff.setOnClickListener {
            // Security Check for Android 12+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && 
                ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Bluetooth permission required.", Toast.LENGTH_SHORT).show()
            } else {
                // disable() is deprecated but still works on many devices for educational use.
                // Note: Modern apps should not turn off Bluetooth without user interaction.
                @Suppress("DEPRECATION")
                bluetoothAdapter?.disable()
                Toast.makeText(this, "Turning Bluetooth OFF...", Toast.LENGTH_SHORT).show()
            }
        }

        // --- WI-FI ON ---
        btnWifiOn.setOnClickListener {
            // THEORY: From Android 10 (API 29), apps cannot change Wi-Fi state directly.
            // We must use Settings Panels to let the user enable Wi-Fi.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val panelIntent = Intent(Settings.Panel.ACTION_WIFI)
                startActivity(panelIntent)
            } else {
                // For Android 9 and below, we can set isWifiEnabled directly.
                @Suppress("DEPRECATION")
                wifiManager.isWifiEnabled = true
                Toast.makeText(this, "Turning Wi-Fi ON...", Toast.LENGTH_SHORT).show()
            }
        }

        // --- WI-FI OFF ---
        btnWifiOff.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Same as Wi-Fi ON, open the panel for the user to toggle it.
                val panelIntent = Intent(Settings.Panel.ACTION_WIFI)
                startActivity(panelIntent)
            } else {
                @Suppress("DEPRECATION")
                wifiManager.isWifiEnabled = false
                Toast.makeText(this, "Turning Wi-Fi OFF...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Helper method to request turning on Bluetooth.
     */
    private fun enableBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show()
        } else {
            if (!bluetoothAdapter!!.isEnabled) {
                // THEORY: Intent is an object used to request an action from the system.
                // ACTION_REQUEST_ENABLE will pop up a system dialog asking the user to turn on Bluetooth.
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Bluetooth is already ON", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
