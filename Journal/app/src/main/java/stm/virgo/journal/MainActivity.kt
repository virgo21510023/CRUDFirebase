package stm.virgo.journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Note that the Toolbar defined in the layout has the id "my_toolbar"
        setSupportActionBar(findViewById(R.id.my_toolbar))

//tambahan
        presenter = Presenter(this)
        presenter.getData()
//        btnTambah.setOnClickListener {
//            startActivity(Intent(applicationContext, UpdateAddActivity::class.java))
//            finish()
//        }
        //fab done
        val fab: View = findViewById(R.id.fabAdd)
        fab.setOnClickListener {
//                view ->
//            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()
            startActivity(Intent(applicationContext, UpdateAddActivity::class.java))
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_favorite -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onSuccessGet(data: List<DataItem>?) {
        rvCategory.adapter = DataAdapter(data, object : DataAdapter.onClickItem {
            override fun clicked(item: DataItem?) {
                val bundle = Bundle()
                bundle.putSerializable("dataItem", item)
                val intent = Intent(applicationContext, UpdateAddActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.id)
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        })
    }

    override fun onFailedGet(msg: String) {
    }

    override fun onSuccessDelete(msg: String) {
        presenter.getData()
    }

    override fun onErrorDelete(msg: String) {
        Toast.makeText(
            this,
            "Delete Tidak Berhasil",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun successAdd(msg: String) {
    }

    override fun errorAdd(msg: String) {
    }

    override fun onSuccessUpdate(msg: String) {
    }

    override fun onErrorUpdate(msg: String) {
    }
}