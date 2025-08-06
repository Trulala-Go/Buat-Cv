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

class Buat : AppCompatActivity(){

  private lateinit var liner:LinearLayout
  private val REQUEST_PICK_IMAGE = 100
  private var imageUri: Uri? = null

  override fun onCreate(savedInstanceState:Bundle?){
    super.onCreate(savedInstanceState)
      setContentView(R.layout.buat)
      
      liner = findViewById(R.id.liner)
      
      Tombol()
      
      KataKata()
  }
  
  override fun onBackPressed(){
    if(liner.visibility == View.VISIBLE){liner.visibility = View.GONE}
    else{Keluar()}
  }
  
  private fun Keluar(){
    AlertDialog.Builder(this)
      .setTitle("Keluar")
      .setMessage("Batal membuat?")
      .setPositiveButton("Ya"){_,_-> finish()}
      .setNegativeButton("Tidak", null)
      .show()
  }
  
  private fun KataKata(){
    val nama = findViewById<EditText>(R.id.nama)
    val kontak = findViewById<EditText>(R.id.kontak)
    val pendidikan = findViewById<EditText>(R.id.pendidikan)
    val pengalaman = findViewById<EditText>(R.id.pengalaman)
    val pribadi = findViewById<EditText>(R.id.pribadi)

    val namaOut = findViewById<TextView>(R.id.namaOut)
    val kontakOut = findViewById<TextView>(R.id.kontakOut)
    val pendidikanOut = findViewById<TextView>(R.id.pendidikanOut)
    val pengalamanOut = findViewById<TextView>(R.id.pengalamanOut)
    val pribadiOut = findViewById<TextView>(R.id.pribadiOut)

    nama.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            namaOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })

    kontak.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            kontakOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    
    pribadi.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            pribadiOut.text = s.toString()
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

    pengalaman.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            pengalamanOut.text = s.toString()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}
  
  private fun Tombol(){
    findViewById<TextView>(R.id.keluar).setOnClickListener{
      Keluar()
    }
    
    findViewById<ImageView>(R.id.nav).setOnClickListener{
      liner.visibility = if(liner.visibility == View.GONE){View.VISIBLE}else{View.GONE}
    }
    
    findViewById<ImageView>(R.id.foto).setOnClickListener{
      AmbilFoto()
    }
    
    findViewById<ImageView>(R.id.simpan).setOnClickListener{
      Menyimpan()
    }
  }
  
  private fun AmbilFoto() {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    intent.type = "image/*"
    startActivityForResult(intent, REQUEST_PICK_IMAGE)
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
        imageUri = data?.data
        val fotoOut = findViewById<ImageView>(R.id.fotoOut)
        fotoOut.setImageURI(imageUri)
        contentResolver.takePersistableUriPermission(
            imageUri!!,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }
  }
  
  private fun Menyimpan() {
    val input = EditText(this)
    input.hint = "Nama file (tanpa .pdf)"

    AlertDialog.Builder(this)
        .setTitle("Simpan CV")
        .setView(input)
        .setPositiveButton("Simpan") { _, _ ->
            val nama = input.text.toString().trim()
            if (nama.isEmpty()) {
                Toast.makeText(this, "Nama file tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val layout = findViewById<LinearLayout>(R.id.liner_cv)
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
