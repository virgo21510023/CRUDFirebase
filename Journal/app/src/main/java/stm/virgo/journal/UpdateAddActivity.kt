package stm.virgo.journal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_update_add.*

@Suppress("SENSELESS_COMPARISON")
class UpdateAddActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter2

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)

        // Note that the Toolbar defined in the layout has the id "my_toolbar"
        setSupportActionBar(findViewById(R.id.my_toolbar))

        findViewById<Button>(R.id.btnBatal)
            .setOnClickListener {
                Log.d("BUTTONS", "User tapped the Supabutton")
                // create an intent to switch to second activity upon clicking
                // create an intent to switch to second activity upon clicking
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }

        presenter = Presenter2(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")
        if (itemDataItem == null) {
            btnAction.text = "TAMBAH"
            btnAction.setOnClickListener() {
                presenter.addData(
                    etTitle.text.toString(),
                    etContent.text.toString()
                )
            }
        } else if (itemDataItem != null) {
            btnAction.text = "PERBARUI"
            val item = itemDataItem as DataItem?
            etTitle.setText(item?.title.toString())
            etContent.setText(item?.content.toString())
            btnAction.setOnClickListener() {
                presenter.updateData(
                    item?.id ?: "",
                    etTitle.text.toString(),
                    etContent.text.toString()
                )
                finish()
            }
        }
    }

    override fun successAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun errorAdd(msg: String) {}
    override fun onSuccessUpdate(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorUpdate(msg: String) {}
    override fun onSuccessGet(data: List<DataItem>?) {}
    override fun onFailedGet(msg: String) {}
    override fun onSuccessDelete(msg: String) {}
    override fun onErrorDelete(msg: String) {}
}