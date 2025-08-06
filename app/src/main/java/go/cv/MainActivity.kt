
package go.cv

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_STORAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AturIkon()

        findViewById<TextView>(R.id.keluar).setOnClickListener {
            Keluar()
        }

        findViewById<TextView>(R.id.buat).setOnClickListener {
            startActivity(Intent(this, Buat::class.java))
        }
        
        findViewById<TextView>(R.id.lamaran).setOnClickListener {
            startActivity(Intent(this, Lamaran::class.java))
        }

        findViewById<TextView>(R.id.buka).setOnClickListener {
            Toast.makeText(this, "Belum Siap", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.status).setOnClickListener {
            BukaAksesMemo()
        }
    }

    override fun onBackPressed() {
        Keluar()
    }

    private fun Keluar() {
        AlertDialog.Builder(this)
            .setTitle("Keluar")
            .setMessage("Meninggalkan Aplikasi?")
            .setPositiveButton("Ya") { _, _ -> finish() }
            .setNegativeButton("Tidak", null)
            .show()
    }

    private fun AturIkon() {
        val status = findViewById<ImageView>(R.id.status)
        if (MintaIzin()) {
            status.setImageResource(R.drawable.sd_terima)
        } else {
            status.setImageResource(R.drawable.sd_tolak)
        }
    }

    private fun BukaAksesMemo() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:$packageName")
            startActivity(intent)
        } catch (e: Exception) {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            startActivity(intent)
        }
    } else {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_STORAGE
        )
    }
  }

    private fun MintaIzin(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Environment.isExternalStorageManager()
    } else {
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Izin Diberikan", Toast.LENGTH_SHORT).show()
                AturIkon()
            } else {
                Toast.makeText(this, "Izin Ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onResume() {
    super.onResume()
    AturIkon()
  }
}