package kiat.anhhong.game2048;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import sp.view.SwipeDetector;

public class MainActivity extends Activity {
    private TableLayout tbl;
    private GestureDetector gestureDetector;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = initGestureDetector();
        TableRow tr1 = new TableRow(this);
        tbl = (TableLayout)findViewById(R.id.table);
        tr1.setLayoutParams(new TableRow.LayoutParams( 30, 30));
        tr1.setBackgroundColor(Color.YELLOW);
        tbl.addView(tr1, new TableLayout.LayoutParams(30, 30));
        tbl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        tbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn = (Button)findViewById(R.id.lock);
    }


    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        showToast("Down Swipe");
                        //action swipe dơ
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        funUP();
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        showToast("Left Swipe");
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        showToast("Right Swipe");
                    }
                }  catch(Exception e) {} //for now, ignore
                update();
                return false;
            }

        });
    }
    private void update(){

    }
    private void funUP(){
        showToast("Up Swipe");
    }

    private void showToast(String phrase){
        Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
    }


}
