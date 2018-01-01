package com.segyo.memo

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

/**
 * Created by sekyo on 2018-01-02.
 */
class EditActivity : AppCompatActivity() {
    private var backButton: ImageButton? = null
    private var deleteButton: ImageButton? = null
    private var saveButton: FloatingActionButton? = null
    private var editText: EditText? = null
    private var time: String? = null
    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val intent = intent

        time = intent.getStringExtra("time")
        content = intent.getStringExtra("content")

        backButton = findViewById(R.id.back_button)
        deleteButton = findViewById(R.id.delete_button)
        saveButton = findViewById(R.id.save_button)
        editText = findViewById(R.id.edit_text)

        if (time != null) {
            editText!!.setText(content)
        }

        backButton!!.setOnClickListener { saveMemo() }

        saveButton!!.setOnClickListener { saveMemo() }

        deleteButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                AlertDialog.Builder(this@EditActivity)
                        .setTitle("메모 삭제")
                        .setMessage("메모를 삭제하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("삭제"
                        ) { dialog, id ->
                            val dbHelper = DBHelper.getInstance(applicationContext)

                            if (time != null) {
                                dbHelper!!.deleteMemo(time!!)
                            }
                            Toast.makeText(applicationContext, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .setNegativeButton("취소",
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface, id: Int) {
                                        dialog.cancel()
                                    }
                                }).create().show()
            }
        })
    }

    override fun onBackPressed() {
        saveMemo()
        super.onBackPressed()
    }

    private fun saveMemo() {
        val dbHelper = DBHelper.getInstance(applicationContext)

        if (editText!!.text.toString() != content) {
            if (time != null) {
                dbHelper!!.updateMemo(time!!, editText!!.text.toString())
                Toast.makeText(applicationContext, "메모가 수정되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                dbHelper!!.insertMemo(editText!!.text.toString())
                Toast.makeText(applicationContext, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }
}