package com.task.wordament

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.task.wordament.databinding.ActivityMainBinding
import com.task.wordament.wordBox.WordButtonData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = arrayListOf<WordButtonData>()
        // assuming every letter to be in upper case

        val charList = arrayListOf("r", "e", "w", "e","t","f","r","i", "m","o","w","i","n","e","e","c")

        for (char in charList) {
            list.add(WordButtonData(char))
        }

        binding.buttonGrid.setData(list)
        val correctList = arrayListOf<String>("cee", "eft", "emoter","ere","ewe","fere","few","rewet","roe","rot","rowen","tew","tome","tor","toric","towie",
            "foen","for","fret","from","ice","men","mor","mote","neoteric","nom","nori","noter","ofter","ore","owe","ref","ween","wem","wero","wice","won","wrote",
            "wore", "wof", "wire","wert","wen","weft","wee","towler","torli","tore","toe","tef","rote","rom","rewire","retore","reft","owre","orf","omen","now","not","nome",
        "new","mow","more","moe","meno", "lwl","ire","frow","fro","fore","fon","fewer","fet","ewer","erf","enow","emo","cire")
        binding.buttonGrid.setCorrectWords(correctList)
        binding.buttonGrid.selectedString.observe(this, { string ->
            Log.d(TAG, "found string: $string")
        })
        binding.buttonGrid.selectedScore.observe(this, { score ->
            Log.d(TAG, "score calculated: $score")
        })
    }
}