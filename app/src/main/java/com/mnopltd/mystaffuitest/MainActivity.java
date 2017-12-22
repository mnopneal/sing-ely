package com.mnopltd.mystaffuitest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.widget.Toast.makeText;
import static com.mnopltd.mystaffuitest.NoteFreqTable.SearchNoteFreqTableForFreq;

public class MainActivity extends AppCompatActivity {

    public TouchEventAction ourView;
    static public int myHeight = 1280; static int myWidth = 720;
    static public char ourClef;
    static public int ourTranspose;
    static public int sharpFlatZigZag = 0;
    static public String ourKeySharpFlatList, ourKeyShow;

    static SoundPool soundPool;
    static int[] sm;
    static AudioManager amg;
    static Context mContext;

    /* Bitmap 3 lines were here.
         static public Bitmap mybitmap = Bitmap.createBitmap(myWidth, myHeight, Bitmap.Config.ARGB_8888);
        static public Canvas mycanvas = new Canvas(mybitmap);
        static public Paint mypaint = new Paint();
   *
    * */
    static public Bitmap mybitmap;
    static public Canvas mycanvas;
    static public Paint mypaint = new Paint();
    Button mybutton;

    static public float myXDpi;

    static public Boolean showNotes;        /* These are set by the UI and used for options */
    static public Boolean showKeyPress;
    static public Boolean showPianoKeys;
    static public Boolean showFrequencies;
    static public Boolean showCoordinates;
    static public Float fudgeFactor = 1.1f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        myXDpi = dm.xdpi;
        Log.e("Startup", "Width: " + width + "; Height: " + height + "; dens: " + dens + "; xdpi:" + myXDpi);

        mybitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mycanvas = new Canvas(mybitmap);
        myHeight = height; myWidth = width;

        mContext = getApplicationContext();
        makeText(mContext, "Loading Sounds...", Toast.LENGTH_LONG).show();
        InitSound();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        getPriorFudgeFactor();
        setContentView(R.layout.activity_main);

        makeText(mContext, "Starting UI...", Toast.LENGTH_LONG).show();

        final Spinner mySpinnerClef = (Spinner) findViewById(R.id.spinnerClef);
        mySpinnerClef.setSelection(2);
        ArrayAdapter<String> myClefAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.clefNames));
        myClefAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerClef.setAdapter(myClefAdapter);

        final Spinner mySpinnerKey = (Spinner) findViewById(R.id.spinnerKey);
        mySpinnerKey.setSelection(3);
        ArrayAdapter<String> myKeyAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.keyNames));
        myKeyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerKey.setAdapter(myKeyAdapter);

        final Spinner mySpinnerTranspose = (Spinner) findViewById(R.id.spinnerTranspose);
        ArrayAdapter<String> myTransposeAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.transposeNames));
        myTransposeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerTranspose.setAdapter(myTransposeAdapter);

        ourView = new TouchEventAction(this, null);
        ourView.setImageBitmap(mybitmap);
        mypaint.setColor(Color.BLACK);
        mypaint.setStrokeWidth(10);

        mybutton = (Button) findViewById(R.id.mybutton);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String locString;
                int begPos, endPos;
                NoteFreqTable.BuildNoteFreqTableInternally(); /* Construct that table */
                NoteFreqTable.printNoteFreqTable(); /* Print out that table */
                Log.e("MyDebug", "mySpinnerClef = " + mySpinnerClef.getSelectedItem().toString()  );
                Log.e("MyDebug", "mySpinnerKey = " + mySpinnerKey.getSelectedItem().toString()    );
                Log.e("MyDebug", "mySpinnerTranspose = " + mySpinnerTranspose.getSelectedItem().toString()  );

                CheckBox myCheckBoxShowNotes = (CheckBox) findViewById(R.id.checkBoxShowNotes);
                showNotes = myCheckBoxShowNotes.isChecked();
                Log.e("MyDebug", "ShowNotes = " + showNotes.toString()  );

                CheckBox myCheckBoxShowPianoKeys = (CheckBox) findViewById(R.id.checkBoxShowPianoKeys);
                showPianoKeys = myCheckBoxShowPianoKeys.isChecked();
                Log.e("MyDebug", "ShowPianoKeys = " + showPianoKeys.toString()  );

                CheckBox myCheckBoxShowFrequencies = (CheckBox) findViewById(R.id.checkBoxShowFrequencies);
                showFrequencies = myCheckBoxShowFrequencies.isChecked();
                Log.e("MyDebug", "ShowFrequencies = " + showFrequencies.toString()  );

                CheckBox myCheckBoxShowCoordinates = (CheckBox) findViewById(R.id.checkBoxShowCoordinates);
                showCoordinates = myCheckBoxShowCoordinates.isChecked();
                Log.e("MyDebug", "ShowCoordinates = " + showCoordinates.toString()  );

                CheckBox myCheckBoxShowKeyPress = (CheckBox) findViewById(R.id.checkBoxShowKeyPress);
                showKeyPress = myCheckBoxShowKeyPress.isChecked();
                Log.e("MyDebug", "ShowNotes = " + showKeyPress.toString()  );

                locString = mySpinnerClef.getSelectedItem().toString();   /*looks like "Treble" or "Bass" */
                ourKeyShow = locString + " Staff in ";
                locString = locString.toLowerCase();
                ourClef = locString.charAt(0);
                Log.e("MyDebug", "ourClef = " + ourClef);

                locString = mySpinnerKey.getSelectedItem().toString();    /* Looks like     D/Bm [C#F#]  */
                begPos = locString.indexOf('[');   endPos = locString.indexOf(']');
                if (begPos == endPos)
                    ourKeySharpFlatList = "";
                else
                    ourKeySharpFlatList = locString.substring(begPos + 1, endPos);
                ourKeyShow = ourKeyShow + locString.substring(0, begPos - 1) + " Playback ";
                Log.e("MyDebug", "ourKeySharpFlatList = " + ourKeySharpFlatList);

                locString = mySpinnerTranspose.getSelectedItem().toString();  /* Looks like -1:Playback one half step down */
                ourKeyShow = ourKeyShow + locString;
                endPos = locString.indexOf(':');                              /* So, endPos = 2 */
                if (endPos > 0)
                    locString = locString.substring(0,endPos); /* Now we just have the number */
                ourTranspose =  Integer.parseInt(locString);
                Log.e("MyDebug", "ourTranspose = " + ourTranspose);

                Log.e("MyDebug", "about to instantiate StaffDisplay");
                Log.i("MyDebug", "Building " + ourKeyShow);
                StaffTable.BuildStaffTableInternally(ourClef, ourTranspose, myHeight, ourKeySharpFlatList);
                /* myHeight may be 1280 but bottom of screen is 1150 */
                StaffTable.printStaffTable();
                mycanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                StaffTable.displayStaffTable();
                DrawClefSymbol(ourClef);
                mypaint.setTextSize(25); mycanvas.drawText(ourKeyShow, 0, myHeight - 35, mypaint);
                setContentView(ourView);
                Log.e("MyDebug", "After setContentView(ourView)");
                /* StaffTable.playStaff();*/
                /* At thils point we would like to :
                        Wait For Click anywhere on screen; obtain Y position;
                        Ask StaffTable.SearchStaffTableForFreq(Y position) to get frequency;
                        Play that frequency
                        Recognize a Back button if possible:
                            View the regular window; return?
                 */
            }
        });


        /* fakeDisplayStaff(); */
        /*doStaffTests(); */
        /*setContentView(ourView); */
        /*doSearchTests();*/
        /*doStaffSearchTests(); */
    }


    public void myNonStaticRestore() {setContentView(R.layout.activity_main);
    }

    public void DrawClefSymbol(char staff) {
        Drawable d;
        int myPos;
        if (staff == 'b' ) {
            d = getResources().getDrawable(R.drawable.bass_clef_th);
            d.setBounds(10, 10, 100, 200);
            d.draw(mycanvas);
        }
        else if (staff == 't' ) {
            d = getResources().getDrawable(R.drawable.treble_clef_big);
            d.setBounds(10, 10, 200, 300);
            d.draw(mycanvas);
        }
        else {
            d = getResources().getDrawable(R.drawable.treble_clef_big);
            d.setBounds(10, 10, 200, 300);
            d.draw(mycanvas);
            myPos = (int) ( (float)myHeight * 0.65);
            d = getResources().getDrawable(R.drawable.bass_clef_th);
            d.setBounds(10, myPos, 100,  myPos + 200);
            d.draw(mycanvas);
        }


    }

    static public void DisplayStaffLine(int yPos, char vtype, char sharpFlat, int halfChunk, String toShow, int idx)
    {
        int startx, endx, thirdWidth, offset, myTextSize;



        Log.i("MyDebug", "Start of StaffDisplayStaffLine " + yPos + "; " + vtype + "; " + sharpFlat);
        if (sharpFlat != ' ' && idx > 2 && idx < 10)  /* constrain sharp & flats to body of staff */
        {
            offset = halfChunk / 10;   /* just a little bit of fudge to line up better */
            mypaint.setTextSize(100);  /* should set font size based on screen size */
            mypaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));
            mycanvas.drawText(String.valueOf(sharpFlat), 40+ sharpFlatZigZag , yPos + halfChunk + offset, mypaint);
            if (sharpFlatZigZag == 0) sharpFlatZigZag = 40;
            else sharpFlatZigZag = 0;
            mypaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
        }
        if (showCoordinates) {
            mypaint.setTextSize(20);
            mycanvas.drawText(Integer.toString(yPos), 0, yPos, mypaint);
        }
        myTextSize = 50;
        if (MainActivity.getShowPianoKeys()) myTextSize = myTextSize - 15;
        if (MainActivity.getShowFrequencies()) myTextSize = myTextSize - 15;
        mypaint.setTextSize(myTextSize); mycanvas.drawText(toShow, myWidth - 120, yPos + 8, mypaint);
        if (vtype == ' ') /* A Space */
            return;/* There ain't nothing to do on a space */
        if (vtype == '_') /* A Full Staff Line */
        {
            startx = 40;
            endx = myWidth - 130;
            Log.i("MyDebug", "  Full Width drawline: " + startx + "; " + yPos + "; " + endx );
            mycanvas.drawLine(startx, yPos, endx, yPos, mypaint);
        }
        else /* A short line above/below the staff.  Where to put it? how about 1/3 centered? */
        {
            thirdWidth = myWidth / 3;
            startx = thirdWidth;
            endx = thirdWidth * 2;
            Log.i("MyDebug", "  Part Width drawline: " + startx + "; " + yPos + "; " + endx );
            mycanvas.drawLine(startx, yPos, endx, yPos, mypaint);
        }

    }

    public void fakeDisplayStaff()
    {
    /*
        DisplayStaffLine(1932, '-', ' ');
        DisplayStaffLine(1815, ' ', ' ');
        DisplayStaffLine(1698, '-', ' ');
        DisplayStaffLine(1581, ' ', 'b');
        DisplayStaffLine(1464, '_', ' ');
        DisplayStaffLine(1347, ' ', ' ');
        DisplayStaffLine(1230, '_', ' ');
        DisplayStaffLine(1113, ' ', ' ');
        DisplayStaffLine(996,  '_', ' ');
        DisplayStaffLine(879,  ' ', ' ');
        DisplayStaffLine(762,  '_', 'b');
        DisplayStaffLine(645,  ' ', ' ');
        DisplayStaffLine(528,  '_', ' ');
        DisplayStaffLine(411,  ' ', ' ');
        DisplayStaffLine(294,  '-', ' ');
        DisplayStaffLine(177,  ' ', ' ');
        DisplayStaffLine(60,   '-', ' ');
    */
    }

    public void doNoteSearchTests()
    {
    double freq;
    freq = SearchNoteFreqTableForFreq('C', ' ', 2, 0); /* Search for Freq */
        Log.i("MyDebug", "C2 result:" + freq + " (Should be 65.406)");

    freq = SearchNoteFreqTableForFreq('C', ' ', 2, 2); /* Search for Freq */
        Log.i("MyDebug", "C2 + 2 result: " + freq + " (Should be 73.416)");

    freq = SearchNoteFreqTableForFreq('C', '#', 2, 0); /* Search for Freq */
        Log.i("MyDebug", "C2# result: " + freq + " (Should be 69.296)");

    freq = SearchNoteFreqTableForFreq('D', ' ', 2, 0); /* Search for Freq */
        Log.i("MyDebug", "D2 result: " + freq + " (Should be 73.416)");

    freq = SearchNoteFreqTableForFreq('D', 'b', 2, 0); /* Search for Freq */
        Log.i("MyDebug", "D2b result: " + freq + " (Should be 69.296)");

    freq = SearchNoteFreqTableForFreq('E', 'b', 2, -1); /* Search for Freq */
        Log.i("MyDebug", "E2b - 1 result: " + freq + " (Should be 73.416)");

    freq = SearchNoteFreqTableForFreq('D', 'b', 2, 15); /* Search for Freq */
        Log.i("MyDebug", "D2b + 15 result: " + freq + " (Should ignore transpose > 13 and be 69.296)");

    freq = SearchNoteFreqTableForFreq('D', 'b', 2, -10);/* Search for Freq */
        Log.i("MyDebug", "D2b - 10 result: " + freq + " (Should be 38.891)");

    freq = SearchNoteFreqTableForFreq('D', 'b', 0, -10); /* Search for Freq */
        Log.i("MyDebug", "D2b - 10 result: " + freq + " (Should not run off table and be 16.351)");

    }

    public void doStaffSearchTests()
    {
        double freq;
        freq = StaffTable.SearchStaffTableForFreq(1188); /* Search for Freq */
        Log.e("MyDebug", "C2 result:" + freq + " (Should be 65.406)");

        freq = StaffTable.SearchStaffTableForFreq(1118);
        Log.e("MyDebug", "D2 result: " + freq + " (Should be 73.416)");

    }


    public void doStaffTests()
    {
    /*Log.i("MyDebug", "Building Treble Staff in key of D major; (C#, F#) no transpose ");
    StaffTable.BuildStaffTableInternally('t', 0, myHeight, "C#F#");
    StaffTable.printStaffTable();
    StaffTable.displayStaffTable();

        Log.i("MyDebug", "Building Treble Staff in key of D major; (C#, F#) transpose down 2 half-steps ");
    StaffTable.BuildStaffTableInternally('t', -2, myHeight, "C#F#");
    StaffTable.printStaffTable();
    */
    Log.i("MyDebug", "Building Bass Staff in key of F major; (Bb) no transpose ");
    StaffTable.BuildStaffTableInternally('b', 0, myHeight, "Bb");
    StaffTable.printStaffTable();
    StaffTable.displayStaffTable();
    }

    public static void InitSound() {

        int maxStreams = 1;
        Log.e("InitSound", "called...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().setMaxStreams(maxStreams).build();
        } else {
            soundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
        }

        sm = new int[71];
        // fill your sounds
        /* We can put below C2 because the staff doesn't go that low  */
        sm[0] = soundPool.load(mContext, R.raw.piano_0_c1, 1);
        sm[1] = soundPool.load(mContext, R.raw.piano_1_cs1, 1);
        sm[2] = soundPool.load(mContext, R.raw.piano_2_d1, 1);
        sm[3] = soundPool.load(mContext, R.raw.piano_3_ds1, 1);
        sm[4] = soundPool.load(mContext, R.raw.piano_4_e1, 1);
        sm[5] = soundPool.load(mContext, R.raw.piano_5_f1, 1);
        sm[6] = soundPool.load(mContext, R.raw.piano_6_fs1, 1);
        sm[7] = soundPool.load(mContext, R.raw.piano_7_g1, 1);
        sm[8] = soundPool.load(mContext, R.raw.piano_8_gs1, 1);
        sm[9] = soundPool.load(mContext, R.raw.piano_9_a1, 1);

        sm[10] = soundPool.load(mContext, R.raw.piano_10_as1, 1);
        sm[11] = soundPool.load(mContext, R.raw.piano_11_b1, 1);
        sm[12] = soundPool.load(mContext, R.raw.piano_12_c2, 1);
        sm[13] = soundPool.load(mContext, R.raw.piano_13_cs2, 1);
        sm[14] = soundPool.load(mContext, R.raw.piano_14_d2, 1);
        sm[15] = soundPool.load(mContext, R.raw.piano_15_ds2, 1);
        sm[16] = soundPool.load(mContext, R.raw.piano_16_e2, 1);
        sm[17] = soundPool.load(mContext, R.raw.piano_17_f2, 1);
        sm[18] = soundPool.load(mContext, R.raw.piano_18_fs2, 1);
        sm[19] = soundPool.load(mContext, R.raw.piano_19_g2, 1);
        sm[20] = soundPool.load(mContext, R.raw.piano_20_gs2, 1);
        sm[21] = soundPool.load(mContext, R.raw.piano_21_a2, 1);
        sm[22] = soundPool.load(mContext, R.raw.piano_22_as2, 1);
        sm[23] = soundPool.load(mContext, R.raw.piano_23_b2, 1);
        sm[24] = soundPool.load(mContext, R.raw.piano_24_c3, 1);
        sm[25] = soundPool.load(mContext, R.raw.piano_25_cs3, 1);
        sm[26] = soundPool.load(mContext, R.raw.piano_26_d3, 1);
        sm[27] = soundPool.load(mContext, R.raw.piano_27_ds3, 1);
        sm[28] = soundPool.load(mContext, R.raw.piano_28_e3, 1);
        sm[29] = soundPool.load(mContext, R.raw.piano_29_f3, 1);
        sm[30] = soundPool.load(mContext, R.raw.piano_30_fs3, 1);
        sm[31] = soundPool.load(mContext, R.raw.piano_31_g3, 1);
        sm[32] = soundPool.load(mContext, R.raw.piano_32_gs3, 1);
        sm[33] = soundPool.load(mContext, R.raw.piano_33_a3, 1);
        sm[34] = soundPool.load(mContext, R.raw.piano_34_as3, 1);
        sm[35] = soundPool.load(mContext, R.raw.piano_35_b3, 1);
        sm[36] = soundPool.load(mContext, R.raw.piano_36_c4, 1);
        sm[37] = soundPool.load(mContext, R.raw.piano_37_cs4, 1);
        sm[38] = soundPool.load(mContext, R.raw.piano_38_d4, 1);
        sm[39] = soundPool.load(mContext, R.raw.piano_39_ds4, 1);
        sm[40] = soundPool.load(mContext, R.raw.piano_40_e4, 1);
        sm[41] = soundPool.load(mContext, R.raw.piano_41_f4, 1);
        sm[42] = soundPool.load(mContext, R.raw.piano_42_fs4, 1);
        sm[43] = soundPool.load(mContext, R.raw.piano_43_g4, 1);
        sm[44] = soundPool.load(mContext, R.raw.piano_44_gs4, 1);
        sm[45] = soundPool.load(mContext, R.raw.piano_45_a4, 1);
        sm[46] = soundPool.load(mContext, R.raw.piano_46_as4, 1);
        sm[47] = soundPool.load(mContext, R.raw.piano_47_b4, 1);
        sm[48] = soundPool.load(mContext, R.raw.piano_48_c5, 1);
        sm[49] = soundPool.load(mContext, R.raw.piano_49_cs5, 1);
        sm[50] = soundPool.load(mContext, R.raw.piano_50_d5, 1);
        sm[51] = soundPool.load(mContext, R.raw.piano_51_ds5, 1);
        sm[52] = soundPool.load(mContext, R.raw.piano_52_e5, 1);
        sm[53] = soundPool.load(mContext, R.raw.piano_53_f5, 1);
        sm[54] = soundPool.load(mContext, R.raw.piano_54_fs5, 1);
        sm[55] = soundPool.load(mContext, R.raw.piano_55_g5, 1);
        sm[56] = soundPool.load(mContext, R.raw.piano_56_gs5, 1);
        sm[57] = soundPool.load(mContext, R.raw.piano_57_a5, 1);
        sm[58] = soundPool.load(mContext, R.raw.piano_58_as5, 1);
        sm[59] = soundPool.load(mContext, R.raw.piano_59_b5, 1);

        /* ok, David's first batch of sounds are all off by an octave.  So, C2 is really C3.
        Ergo, we are going to shift all of them up 12 slots.      */
        int idx;
        for(idx=70; idx >= 12; idx--) { sm[idx] = sm[idx - 12];    }
        for(idx=11; idx >= 0; idx--) { sm[idx] = 0;    }


        Log.e("InitSound", "After Load called...");
        amg = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        mySleep(1);
        Log.e("InitSound", "At end...");
    }

    static public void playSound(int sound) {
        Log.e("PlaySound", "Called for: " + sound + " sm is: " + sm[sound] );
        if (sm[sound] == 0) return;
        if (showKeyPress) showNoteDetails();
        soundPool.play(sm[sound], 1f, 1f, 1, 0, 1f);
    }

    public final void cleanUpIfEnd() {
        sm = null;
        soundPool.release();
        soundPool = null;
    }
    static public void mySleep(int secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    static public int getMyWidth() {
        return myWidth;
    }
    static public boolean getShowNotes() { return showNotes;    }
    static public boolean getShowPianoKeys() { return showPianoKeys;    }
    static public boolean getShowFrequencies() { return showFrequencies;    }
    static public float getfudgeFactor() { return fudgeFactor;    }


    static public void showNoteDetails(){
        int fudgedY;
        String toShow;
        toShow = "";

        if (showNotes) toShow = StaffTable.getLastNote() + TouchEventAction.getLastAccidental() ;
        if (showCoordinates) toShow = toShow + "at X,Y: " + String.valueOf(TouchEventAction.getLastX()) + "," + String.valueOf(TouchEventAction.getLastY())
                    + "," + String.valueOf(StaffTable.getLastFudgedY());
        if (MainActivity.getShowPianoKeys()) toShow = toShow +  " - note " + String.valueOf(StaffTable.getLastPianoNote());
        if (MainActivity.getShowFrequencies()) toShow = toShow  + " - " + String.valueOf(StaffTable.getLastFreq()) + "hz";


        Toast toast = Toast.makeText(mContext, toShow, Toast.LENGTH_SHORT);

        /* This puts it where the finger press was... */
        /* toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, (TouchEventAction.getLastY() + StaffTable.gethalfChunk() + 10) ); */

        /* But maybe it's better to put it where we recognized it. */
        fudgedY = (int)( (float)StaffTable.getLastLinePos() / fudgeFactor );
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, fudgedY + StaffTable.gethalfChunk() + 20  );
        toast.show();
    }

    static public int dpToPx(int dp) {
        return Math.round(dp * (myXDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    static public int pxToDp(int px) {
        return Math.round(px / (myXDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    static public void setNewFudgeFactor(float vpos) {
        String myString;
        FileOutputStream outputStream;
        /* Called from TouchEventJava with max Y position.   We want this to be equal to myHeight.  */
        fudgeFactor = (float) myHeight / vpos;
        Toast.makeText(mContext, "Fudge Factor set to " + fudgeFactor, Toast.LENGTH_LONG).show();

        myString = String.valueOf(fudgeFactor);

        /* Well, I tried a couple of approaches to bridge the static calls non-static.  Both failed.  I don't know how to get out of this swamp */
        writeToFile(myString, mContext  );
        /* OR
        try {
            outputStream = openFileOutput("fudgeFactor.dat", mContext.MODE_PRIVATE);
            outputStream.write(myString.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

    static private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("fudgefactor.dat", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

     static public void getPriorFudgeFactor(){
        String myString;
        myString = readFromFile(mContext);
         Log.e("getPriorFudgeFactor", "readFromFile returned: " + myString);
         if (myString != "") {
             fudgeFactor = Float.parseFloat(myString);
             if (fudgeFactor > 2f || fudgeFactor < 0.5f ) fudgeFactor = 1.1f;
             Log.e("getPriorFudgeFactor", "fudgeFactor is: " + fudgeFactor);
         }
     }

    static String readFromFile(Context context) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput("fudgefactor.dat");
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    static public void toastThis(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
