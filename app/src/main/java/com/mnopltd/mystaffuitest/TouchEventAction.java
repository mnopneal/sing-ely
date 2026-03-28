package com.mnopltd.mystaffuitest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;



/**
 * Created by neal on 12/3/2017.
 */

public class TouchEventAction extends ImageView {
        Context context;
    private GestureDetector gestureDetector;
    public static int lastX;
    public static int lastY;
    public static char lastAccidental;
    public static boolean figureFudgeFactor;

    public TouchEventAction(Context context, AttributeSet attrs) {
        super(context);
        this.context = context;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float deltaY = e2.getY() - e1.getY();
                if (Math.abs(deltaY) > 100) {  // threshold to distinguish from tap
                    if (deltaY < 0) {
                        MainActivity.changeStaff(-1);   // swipe up = go lower
                    } else {
                        MainActivity.changeStaff(1);  // swipe down = go higher
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        float eventRawY = event.getRawY();
        int pNote;

        gestureDetector.onTouchEvent(event);
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

                if (eventX < 50) lastAccidental = 'b';

                if (eventX > (MainActivity.getMyWidth() - 140))  /* Sharp Accidental */  lastAccidental = '#';
                pNote = StaffTable.SearchStaffTableForPianoKey((int)eventY, lastAccidental);
                Log.e("Action", "pNote: " + pNote);
                MainActivity.playSound(pNote);
                return true;
            case 1: /* This appears to be the back button;  What can we do at this point to restore control to the primary startup screen?
                Neither of the below calls will work due to the static/non-static boundary.  Nor is it clear that they could work.
                MainActivity.RestoreOriginalContentView();
                setContentView(R.layout.activity_main);
                */
                Log.e("Action", "Other Action: Back " + event.getAction() );
                return true;
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
