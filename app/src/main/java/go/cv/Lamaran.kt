package go.cv

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.os.*
import android.content.*
import java.io.*
import android.widget.*
import android.view.*
import android.net.*
import android.text.*
import android.graphics.pdf.PdfDocument
import android.graphics.*

class Lamaran : AppCompatActivity(){

  private lateinit var liner:LinearLayout

  override fun onCreate(savedInstanceState:Bundle?){
    super.onCreate(savedInstanceState)
      setContentView(R.layout.surat_lamaran)
      
      liner = findViewById(R.id.liner)
      
      Tombol()
      LainLain()
  }
  
  override fun onBackPressed(){
    if(liner.visibility == View.VISIBLE){liner.visibility = View.GONE}
    else{Keluar()}
  }
  
  private fun Keluar(){
    AlertDialog.Builder(this)
      .setTitle("Keluar")
      .setMessage("Yakin Keluar?")
      .setPositiveButton("Ya"){_,_-> finish()}
      .setNegativeButton("Tidak", null)
      .show()
  }
  
  private fun Tombol(){
    findViewById<ImageView>(R.id.nav).setOnClickListener{
      liner.visibility = if(liner.visibility == View.GONE)View.VISIBLE else View.GONE
    }
    
    findViewById<TextView>(R.id.keluar).setOnClickListener{
      Keluar()
    }
    
    findViewById<ImageView>(R.id.simpan).setOnClickListener{
      Menyimpan()
    }
    
  }
  
  private fun LainLain(){
    val tanggal = findViewById<EditText>(R.id.tanggal)
    val hrd = findViewById<EditText>(R.id.hrd)
    val tempat = findViewById<EditText>(R.id.tempat)
    val isi = findViewById<EditText>(R.id.isi)
    val nama = findViewById<EditText>(R.id.nama)
    val rumah = findViewById<EditText>(R.id.rumah)
    val pendidikan = findViewById<EditText>(R.id.pendidikan)
    val ttl = findViewById<EditText>(R.id.ttl)
    val telpon = findViewById<EditText>(R.id.telpon)
    val penutup = findViewById<EditText>(R.id.penutup)
    val lampiran = findViewById<EditText>(R.id.lampiran)
    
    val tanggalOut = findViewById<TextView>(R.id.tanggalOut)
    val hrdOut = findViewById<TextView>(R.id.hrdOut)
    val tempatOut = findViewById<TextView>(R.id.tempatOut)
    val isiOut = findViewById<TextView>(R.id.isiOut)
    val namaOut = findViewById<TextView>(R.id.namaOut)
    val rumahOut = findViewById<TextView>(R.id.rumahOut)
    val pendidikanOut = findViewById<TextView>(R.id.pendidikanOut)
    val ttlOut = findViewById<TextView>(R.id.ttlOut)
    val telponOut = findViewById<TextView>(R.id.telponOut)
    val penutupOut = findViewById<TextView>(R.id.penutupOut)
    val lampiranOut = findViewById<TextView>(R.id.lampiranOut)
    
    tanggal.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            tanggalOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    penutup.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            penutupOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    tempat.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            tempatOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    hrd.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            hrdOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    isi.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            isiOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    nama.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            namaOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    rumah.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            rumahOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    pendidikan.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            pendidikanOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    ttl.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            ttlOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    telpon.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            telponOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    lampiran.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            lampiranOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
  }
  
  
  private fun Menyimpan() {
    val input = EditText(this)
    input.hint = "Nama file (tanpa .pdf)"

    AlertDialog.Builder(this)
        .setTitle("Simpan Surat Lamaran")
        .setView(input)
        .setPositiveButton("Simpan") { _, _ ->
            val nama = input.text.toString().trim()
            if (nama.isEmpty()) {
                Toast.makeText(this, "Nama file tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val layout = findViewById<LinearLayout>(R.id.liner_utama)
            val width = layout.width
            val height = layout.height

            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            layout.draw(canvas)

            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
            val page = document.startPage(pageInfo)
            page.canvas.drawBitmap(bitmap, 0f, 0f, null)
            document.finishPage(page)

            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            if (!folder.exists()) folder.mkdirs()
            val file = File(folder, "$nama.pdf")

            try {
                val outputStream = FileOutputStream(file)
                document.writeTo(outputStream)
                document.close()
                outputStream.close()
                Toast.makeText(this, "Disimpan di:\n${file.absolutePath}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Gagal menyimpan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        .setNegativeButton("Batal", null)
        .show()
  }
  
}
