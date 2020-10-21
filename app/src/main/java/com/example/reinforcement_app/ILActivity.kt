package com.example.reinforcement_app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import android.preference.*
import android.provider.Settings
import android.text.InputFilter
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import kotlinx.android.synthetic.main.activity_i_l.*
import java.util.*


class ILActivity : AppCompatActivity() {

    private var isCancelled = true
    private var lapse_expired = false
    lateinit var mp: MediaPlayer
    var logrado = true

    var MaxTime: Int = 0
    var MinTime: Int = 0
    var lapse: Int = 0

    override fun onBackPressed() {
        super.onBackPressed()
        isCancelled = true

    }

    fun Context.defaultRingtonePlayer(): MediaPlayer {
        return MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
    }

    val Context.defaultRingtone: Ringtone
        get() {
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            return RingtoneManager.getRingtone(this, uri)
        }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()
        if (id == R.id.action_two) {
            Toast.makeText(this, "Item Two Clicked", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.action_three) {
            Toast.makeText(this, "Item Three Clicked", Toast.LENGTH_LONG).show()
            return true
        }

        return super.onOptionsItemSelected(item)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i_l)
        //        val toolbar = findViewById(R.menu.menu_toolbar) as Toolbar?
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val ringtone: Ringtone = defaultRingtone
        //        if (fragmentManager.findFragmentById(android.R.id.content) == null) {
        //            fragmentManager.beginTransaction()
        //                .add(android.R.id.content, PreferencesFragment()).commit()
        //        }


        back_button_IL.setOnClickListener {
            if (isCancelled) {
                Toast.makeText(this, "drawer menu", Toast.LENGTH_LONG).show()
                //                back_button_IL.setImageResource(R.drawable.ic_baseline_menu_24)


                //                val navDrawer = findViewById<DrawerLayout>(R.id.drawer_layout)
                //                drawerLayout.openDrawer(Gravity.START);
                //                // If the navigation drawer is not open then open it, if its already open then close it.
                //                // If the navigation drawer is not open then open it, if its already open then close it.
                //                if (!navDrawer.isDrawerOpen(Gravity.LEFT)) navDrawer.openDrawer(Gravity.LEFT) else navDrawer.closeDrawer(
                //                    Gravity.RIGHT
                //                )
                super.onBackPressed();
            } else {
                isCancelled = true

                button_timer.setImageResource(R.drawable.ic_baseline_timer_24)
                card_lay.visibility = View.VISIBLE
                //                back_button_IL.setImageResource(R.drawable.ic_baseline_menu_24)
                //                back_button_IL.visibility = View.GONE
                text_view.text = "Inicia a trabajar con el cronometro"
                //                mp.stop()
                if (ringtone.isPlaying) {
                    ringtone.stop()
                }
                //                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
                //                if (!ringtone.isPlaying){
                //                    ringtone.play()
                //                }
                linearLayout.visibility = View.GONE

                this.window.clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN or
                            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                )
                //            super.onBackPressed();
            }
        }


        mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)


        title = ""
        val random = Random()
        linearLayout.visibility = View.GONE


        var millisInFuture: Long = 5000


        val countDownInterval: Long = 100
        var min = 15
        var max = 15 * 60
        seekBar.setMax(60 - 1);

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                textseeckbar.text = "${i + 1}"
                max = (i + 1) * 60
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
                //                Toast.makeText(applicationContext,"start tracking",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
                //                Toast.makeText(applicationContext,"stop tracking",Toast.LENGTH_SHORT).show()

            }
        })


        button_timer.setOnClickListener {
            hideKeyboard(it)
            logrado = true
            MinTime = min
            MaxTime = max

                //                if (max>=5) {
            lapse = random.nextInt(max - min + 1) + min
            millisInFuture = lapse.toLong() * 1000

            timer(millisInFuture, countDownInterval, this.window, mp, this, ringtone).start()


            if (isCancelled) {
                isCancelled = false
                button_timer.setImageResource(R.drawable.ic_baseline_timer_off_24)
                //                text_view.text = "${lapse} Estas trabajando?"
                card_lay.visibility = View.GONE
                back_button_IL.setImageResource(R.drawable.ic_baseline_arrow_back_24)
                //                        back_button_IL.visibility=View.VISIBLE

            } else {
                isCancelled = true
                button_timer.setImageResource(R.drawable.ic_baseline_timer_24)
                card_lay.visibility = View.VISIBLE
                //                        back_button_IL.visibility=View.GONE
                //                        back_button_IL.setImageResource(R.drawable.ic_baseline_menu_24)

                text_view.text = "Inicia a trabajar con el cronometro"
                //                        mp.stop()
                //                        mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
                if (ringtone.isPlaying) {
                    ringtone.stop()
                }
                //                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
                //                        if (!ringtone.isPlaying){
                //                            ringtone.play()
                //                        }
                linearLayout.visibility = View.GONE
            }
            this.window.clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )

        }
        // Count down timer start button
        button_start.setOnClickListener {
            // Start the timer
            //            if (lapse_expired) {
            //                mp.stop()
            //                text_view.text = "Sigue Trabajando!"
            lapse_expired = false
//                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
            if (ringtone.isPlaying) {
                ringtone.stop()
            }
            //                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
            //                if (!ringtone.isPlaying){
            //                    ringtone.play()
            //                }
            lapse = random.nextInt(MaxTime - MinTime + 1) + MinTime
            millisInFuture = lapse.toLong() * 1000

            timer(millisInFuture, countDownInterval, this.window, mp, this, ringtone).start()
            linearLayout.visibility = View.GONE
            this.window.clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
            logrado = true

//            }
        }


// Count down timer stop/cancel button
        button_stop.setOnClickListener {
// Start the timer
//            isCancelled = true
//            mp.stop()
//            it.isEnabled = false
//            button_start.isEnabled = true
//            this.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or
//                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
//                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON )
//            text_view.text = "intentalo de nuevo"
//                mp.stop()
//            text_view.text = "Debes trabajar!"
            lapse_expired = false
//                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
            if (ringtone.isPlaying) {
                ringtone.stop()
            }
//                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
//                if (!ringtone.isPlaying){
//                    ringtone.play()
//                }
            lapse = random.nextInt(MaxTime - MinTime + 1) + MinTime
            millisInFuture = lapse.toLong() * 1000
            timer(millisInFuture, countDownInterval, this.window, mp, this, ringtone).start()
            linearLayout.visibility = View.GONE
            this.window.clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
            logrado = false
        }


    }


    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Method to configure and return an instance of CountDownTimer object
    private fun timer(
        millisInFuture: Long,
        countDownInterval: Long,
        window: Window,
        mp: MediaPlayer,
        activity: Activity,
        ringtone: Ringtone
    ): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeRemaining = "${millisUntilFinished / 1000 + 1}"

                if (isCancelled) {
//"${text_view.text}"
                    cancel()
                } else {
                    if (logrado)
//                            text_view.text =  " Sigue esforzandote!"
                        text_view.text =
                            "Aprieta el boton para parar el cronometro\n Sigue trabajando!"
//                            "Aprieta el boton para parar el cronometro\n${timeRemaining}-Sigue trabajando!"
                    else
//                            text_view.text =  "Vuelve a intentarlo, tu puedes!"
                        text_view.text =
                            "Aprieta el boton para parar el cronometro\n Debes trabajar!"
//                            "Aprieta el boton para parar el cronometro\n${timeRemaining}-Debes trabajar!"

                }
            }

            override fun onFinish() {
                text_view.text = "Lo lograste?"
//                    mp.start()
//                    if (ringtone.isPlaying){
//                        ringtone.stop()
//                    }
//                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
                if (!ringtone.isPlaying) {
//                    ringtone.play()
                    vibrate()
                }
//                mp = MediaPlayer.create(applicationContext, R.raw.alarm_ringtone)
                button_start.isEnabled = true
                lapse_expired = true
                linearLayout.visibility = View.VISIBLE
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                )
            }

            @SuppressLint("ServiceCast")
            fun Context.vibrate(milliseconds:Long = 1000){
                val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                // Check whether device/hardware has a vibrator
                val canVibrate:Boolean = vibrator.hasVibrator()

                if(canVibrate){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        // void vibrate (VibrationEffect vibe)
                        vibrator.vibrate(
                            VibrationEffect.createOneShot(
                                milliseconds,
                                // The default vibration strength of the device.
                                VibrationEffect.DEFAULT_AMPLITUDE
                            )
                        )
                    }else{
                        // This method was deprecated in API level 26
                        vibrator.vibrate(milliseconds)
                    }
                }
            }


            // Extension property to check whether device has Vibrator
            val Context.hasVibrator:Boolean
                @SuppressLint("ServiceCast")
                get() {
                    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    return vibrator.hasVibrator()
                }


        }

    }
}