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
    public static boolean figureFudgeFactor;

    public TouchEventAction(Context context, AttributeSet attrs) {
        super(context);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        float eventRawY = event.getRawY();
        int pNote;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("Action", "Down Tapped at: (" + eventX + "," + eventY + ")");
                /* Toast.makeText(MainActivity.this, "YPos: " + eventY, Toast.LENGTH_LONG).show(); */
                /* path.addCircle(eventX, eventY, 50, Path.Direction.CW); */
                Log.e("Action", "Converts to: " + MainActivity.dpToPx((int)eventY) +  " or " + MainActivity.pxToDp((int)eventY));

                lastX = (int) eventX;
                lastY = (int) eventY;
                lastAccidental = ' ';

                if (eventX < 40 && eventY < 40) {
                    /* Top left; basically set things in motion for next keypress to be bottom right, then do fudge factor; */
                    MainActivity.toastThis("To figure the fudge factor, next press the bottom right of screen..." );
                    figureFudgeFactor = true;
                    return true;
                }
                if (figureFudgeFactor) {
                    /* Ok, we want to see what factor we need in order to take the supplied Y coordinate and make it the same as the screen height.
                    *  This is probably simplest if we call a method in the main activity */
                    MainActivity.setNewFudgeFactor(eventY);
                    figureFudgeFactor = false;
                    return true;
                }
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
