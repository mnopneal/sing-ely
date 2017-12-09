package com.mnopltd.mystaffuitest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;


/**
 * Created by neal on 12/3/2017.
 */

public class TouchEventAction extends ImageView {
        Context context;
    public static int lastX;
    public static int lastY;
    public static char lastAccidental;

    public TouchEventAction(Context context, AttributeSet attrs) {
        super(context);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        int pNote;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("Action", "Down Tapped at: (" + eventX + "," + eventY + ")");
                /* Toast.makeText(MainActivity.this, "YPos: " + eventY, Toast.LENGTH_LONG).show(); */
                /* path.addCircle(eventX, eventY, 50, Path.Direction.CW); */
                lastX = (int) eventX;
                lastY = (int) eventY;
                lastAccidental = ' ';
                pNote = StaffTable.SearchStaffTableForPianoKey((int)eventY);
                if (eventX < 50) {
                    pNote = pNote - 1;  /* Flat Accidental */
                    lastAccidental = 'b';
                }
                if (eventX > (MainActivity.getMyWidth() - 140)) {
                    pNote = pNote + 1;  /* Sharp Accidental */
                    lastAccidental = '#';
                }
                Log.e("Action", "pNote: " + pNote);
                MainActivity.playSound(pNote);
                return true;
            case 1: /* This appears to be the back button;  What can we do at this point to restore control to the primary startup screen?
                Neither of the below calls will work due to the static/non-static boundary.  Nor is it clear that they could work.
                MainActivity.RestoreOriginalContentView();
                setContentView(R.layout.activity_main);
                */
                Log.e("Action", "Other Action: Back " + event.getAction() );
                return false;
            default:
                Log.e("Action", "Other Action: (" + event.getAction() );
                return false;
        }

        /* invalidate();
        return true;
        */
    }
    static public int getLastX() { return lastX;}
    static public int getLastY() { return lastY;}
    static public char getLastAccidental() { return lastAccidental;}
}
