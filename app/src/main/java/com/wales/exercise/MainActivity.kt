package com.wales.exercise

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private var discountPrice = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }


    private fun setupView() {
        addButton.setOnClickListener {
            addCount()
        }

        bar_discount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, value: Int, p2: Boolean) {
                printPrice(value)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        num_price.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                var value = bar_discount.progress
                this.printPrice(value)
            }
            // false 表示收起鍵盤（不保留鍵盤）
            num_price.clearFocus()
            false
        }
        num_price.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                var value = bar_discount.progress
                printPrice(value)
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.reset_button -> resetCount()
            else -> false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addCount() {
        this.count++
        textView.text = this.count.toString()
    }

    private fun resetCount() {
        this.count = 0
        textView.text = this.count.toString()
    }

    private fun printPrice(value: Int) {
        discountPrice = num_price.text.toString().toFloatOrNull() ?: 0f
        txt_discount_price.text = "打折後\t${(discountPrice * value / 100).toString()}元"
        txt_discount.text = "折扣\t${(value.toFloat()) / 10}折"
    }
}
