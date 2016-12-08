package kiat.anhhong.game2048;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {
    private int[] pow = {1,2,4,8,16,32,64,128,256,521,1024,2048,4096,8192};
    private int  MAX=4;
    int mang[][];
    private View tbl;
    private GestureDetector gestureDetector;
    private LinearLayout ln;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "ccccc", Toast.LENGTH_SHORT).show();
        gestureDetector = initGestureDetector();
        event();
        start();
    }
    private void start(){
        mang= new int[MAX][MAX];
        newNum();
        update();
        Toast.makeText(this, ""+ mang[2][2], Toast.LENGTH_SHORT).show();
    }
    private void newNum(){
        Random rand = new Random();
        int a =rand.nextInt(MAX);
        int b =rand.nextInt(MAX);
        while (mang[a][b]!=0){
            a =rand.nextInt(MAX);
            b =rand.nextInt(MAX);
        }
        if(rand.nextInt(5)==0){
            mang[a][b] =2;
        }else{
            mang[a][b] =1;
        }
    }
    private void event(){
        ln=(LinearLayout)findViewById(R.id.vieww);
        tbl = (View)findViewById(R.id.table);
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
    }


    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    int[][] old;
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        mang=funDown(mang);
                        update();
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        mang=funUP(mang);
                        update();
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        mang=funLeft(mang);
                        update();
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        mang=funRight(mang);
                        update();
                    }
                }  catch(Exception e) {} //for now, ignore
                return false;
            }

        });
    }
    private void update(){

        newNum();
        ln.removeAllViews();
        for (int i=0;i<MAX;i++){
            LinearLayout lll = new LinearLayout(this);
            lll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1));
            for (int j=0;j<MAX;j++){
                Button btnTeam = new Button(this);
                btnTeam.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
                if(mang[i][j]==0){
                    btnTeam.setBackgroundColor(Color.TRANSPARENT);
                    btnTeam.setText("");
                } else {
                    btnTeam.setText(""+pow[mang[i][j]]);
                }
                lll.addView(btnTeam);
            }
            ln.addView(lll);
        }
//        if(checkEnd()){
//            Intent i = new Intent(MainActivity.this,GameOver.class);
//            startActivity(i);
//        } else {
//            newNum();
//        }
    }
    private boolean checkEnd(){
        if(mang!=funDown(mang)){
            return false;
        }
        if(mang!=funLeft(mang)){
            return false;
        }
        if(mang!=funRight(mang)){
            return false;
        }
        if(mang!=funUP(mang)){
            return false;
        }
        return true;
    }
    private int[][] funUP(int[][] mangC){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                for(int k=j+1;k<4;k++){
                    if(mangC[j][i] ==0){
                        mangC[j][i] = mangC[k][i];
                        mangC[k][i] = 0;
                    }
                    else{
                        if(mangC[j][i] == mangC[k][i]){
                            mangC[j][i] = mangC[j][i]+1;
                            mangC[k][i] = 0;
                            break;
                        }
                        if(mangC[k][i] !=0){
                            break;
                        }
                    }
                }
            }
        }
        return mangC;
    }
    private int[][] funDown(int[][] mangC){
        for(int i=0;i<4;i++){
            for(int j=3;j>=0;j--){
                for(int k=j-1;k>=0;k--){
                    if(mangC[j][i] ==0){
                        mangC[j][i] = mangC[k][i];
                        mangC[k][i] = 0;
                    }
                    else{
                        if(mangC[j][i] == mangC[k][i]){
                            mangC[j][i] = mangC[j][i]+1;
                            mangC[k][i] = 0;
                            break;
                        }
                        if(mangC[k][i] !=0){
                            break;
                        }
                    }
                }
            }
        }
        return mangC;
    }
    private int[][] funRight(int[][] mangC){
        for(int i=0;i<4;i++){
            for(int j=3;j>=0;j--){
                for(int k=j-1;k>=0;k--){
                    if(mangC[i][j] ==0){
                        mangC[i][j] = mangC[i][k];
                        mangC[i][k] = 0;
                    }
                    else{
                        if(mangC[i][j] == mangC[i][k]){
                            mangC[i][j] = mangC[i][j]+1;
                            mangC[i][k] = 0;
                            break;
                        }
                        if(mangC[i][k] !=0){
                            break;
                        }
                    }
                }
            }
        }
        return mangC;
    }
    private int[][] funLeft(int[][] mangC){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                for(int k=j+1;k<4;k++){
                    if(mangC[i][j] ==0){
                        mangC[i][j] = mangC[i][k];
                        mangC[i][k] = 0;
                    }
                    else{
                        if(mangC[i][j] == mangC[i][k]){
                            mangC[i][j] = mangC[i][j]+1;
                            mangC[i][k] = 0;
                            break;
                        }
                        if(mangC[i][k] !=0){
                            break;
                        }
                    }
                }
            }
        }
        return mangC;
    }
}
