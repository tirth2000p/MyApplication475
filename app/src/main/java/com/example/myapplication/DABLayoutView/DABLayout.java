package com.example.myapplication.DABLayoutView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.resultPage;

import java.util.Arrays;
import java.util.Vector;

public class DABLayout extends View {

    private int n = 3, c = 0, number_of_players = 2, k = 0;
    private SoundPool sound_effects;
    private int undo_sound_int, box_sound_int, line_sound_int, winner_sound_int;
    private float[] circle_x, circle_y;
    private final float rad = 40f, sw = 10f;
    private final float r = 10f;
    private Paint circle_color;
    private Paint[] player_colors;
    private float[] sx ,sy ,fx ,fy;
    private float distance;
    private int repeat;
    Vibrator vibrator;
    private String[] names;
    private int[] scores, paint_colors;
    private int[] si, sj , fi , fj , winner_si, winner_sj, winner_fi, winner_fj;
    private int[][] a ,b;
    private int turn = 0;
    private TextView count,p1count,p2count;
    private Animation count_animation, turn_animation;
    private TextView turnText;
    private int total =0;
    private int p1Total =0;
    private int p2Total =0;

    Vector<Integer> winner_checker;
    Vector<Integer> turn_keeper;

    public DABLayout(Context context) {
        super(context);
        init_sounds();
        count_animation = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        turn_animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        vibrator = (Vibrator) (getContext().getSystemService(Context.VIBRATOR_SERVICE));
        init(null, n, number_of_players);
    }

    public DABLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init_sounds();
        count_animation = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        turn_animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        vibrator = (Vibrator) (getContext().getSystemService(Context.VIBRATOR_SERVICE));
        init(attrs,n,number_of_players);
    }

    public DABLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init_sounds();
        vibrator = (Vibrator) (getContext().getSystemService(Context.VIBRATOR_SERVICE));
        turn_animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        count_animation = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        init(attrs,n, number_of_players);
    }

    public DABLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init_sounds();
        count_animation = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        vibrator = (Vibrator) (getContext().getSystemService(Context.VIBRATOR_SERVICE));
        turn_animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        init(attrs,n,number_of_players);
    }

    public void init(@Nullable AttributeSet set, int grid_size, int players) {
        turn_animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        count_animation = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        count = ((Activity)getContext()).findViewById(R.id.counter);
        p1count= ((Activity)getContext()).findViewById(R.id.p1counter);
        p2count= ((Activity)getContext()).findViewById(R.id.p2counter);
        turnText = ((Activity)getContext()).findViewById(R.id.turn_text);
        n = grid_size;
        number_of_players = players;
        initN(n, number_of_players, names);
        paint_colors = new int[6];
        player_colors = new Paint[6];
        initialise_colors();
        for (int i = 0; i < number_of_players; i++) {
            player_colors[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
            player_colors[i].setColor(paint_colors[i]);
            player_colors[i].setStrokeWidth(sw);
        }
        circle_color = new Paint(Paint.ANTI_ALIAS_FLAG);
        circle_color.setColor(getResources().getColor(R.color.colorDots));
        scores = new int[number_of_players];
    }

    public void initN(int grid_size, int players, String[] player_names) {
        turn_animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        count_animation = AnimationUtils.loadAnimation(getContext(), R.anim.blink_anim);
        count = ((Activity)getContext()).findViewById(R.id.counter);
        p1count= ((Activity)getContext()).findViewById(R.id.p1counter);
        p2count= ((Activity)getContext()).findViewById(R.id.p2counter);
        turnText = ((Activity)getContext()).findViewById(R.id.turn_text);
        n = grid_size;
        names = player_names;
        number_of_players = players;
        paint_colors = new int[6];
        initialise_colors();
        player_colors = new Paint[6];
        for (int i = 0; i < number_of_players; i++) {
            player_colors[i] = new Paint(Paint.ANTI_ALIAS_FLAG);
            player_colors[i].setColor(paint_colors[i]);
            player_colors[i].setStrokeWidth(sw);
        }
        circle_x = new float[n];
        circle_y = new float[n];
        sx = new float[2*(n)*(n-1) + 2];
        sy = new float[2*(n)*(n-1) + 2];
        fx = new float[2*(n)*(n-1) + 2];
        fy = new float[2*(n)*(n-1) + 2];
        a = new int[n-1][n-1];
        b = new int[n-1][n-1];
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                b[i][j] = -1;
            }
        }
        si = new int[2*n*(n-1)];
        sj = new int[2*n*(n-1)];
        fi = new int[2*n*(n-1)];
        fj = new int[2*n*(n-1)];
        winner_checker = new Vector<>();
        turn_keeper = new Vector<>();
        winner_si = new int[n*n];
        winner_sj = new int[n*n];
        winner_fi = new int[n*n];
        winner_fj = new int[n*n];
        scores = new int[number_of_players];
        paint_colors = new int[6];
        //turnText.setText(names[turn] + "is turn");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        turnText.setText(names[turn] + this.getResources().getString(R.string.Turn));
        canvas.drawColor(getResources().getColor(R.color.colorCanvas)); // Canvas Color
        drawCircles(canvas);
        if (setFinal(fx[c],fy[c])) {
            is_end();
            drawLines(canvas);
        }
        else {
            canvas.drawLine(sx[c], sy[c], fx[c], fy[c], player_colors[turn]);
            drawLines(canvas);
            invalidate();
        }
        draw_boxes(canvas);
        drawLines(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value =  super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                setInitial(event.getX(), event.getY());
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                float x = event.getX(), y = event.getY();
                float d = sqd(x,y,sx[c],sy[c]);
                if (d<=distance*distance && d>rad*rad) {
                    fx[c] = x;
                    fy[c] = y;
                    invalidate();
                    return true;
                }
                return value;
            }
        }
        return value;
    }

    private void drawCircles (Canvas canvas) {
        int d = n+1;
        distance = getWidth()/d;
        float ref = getHeight()/2;
        int f = n/2;
        for (int i = 1; i<=n; i++) {
            circle_x[i-1] = getWidth()*i/d;
            for(int j = 0; j<n; j++) {
                circle_y[j] = ref + (j-f)*distance;
                canvas.drawCircle(circle_x[i-1], circle_y[j], r, circle_color);
            }
        }
    }


    private void setInitial(float x, float y) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sqd(x,y,circle_x[i], circle_y[j]) <= rad*rad) {
                    sx[c] = circle_x[i];
                    sy[c] = circle_y[j];
                    si[c] = i;
                    sj[c] = j;
                    vibrator.vibrate(20);
                }
            }
        }
    }

    private boolean setFinal(float x, float y) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (sqd(x,y,circle_x[i], circle_y[j]) <= rad*rad) {
                    fx[c] = circle_x[i];
                    fy[c] = circle_y[j];
                    fi[c] = i;
                    fj[c] = j;
                    for (int l = 0; l < c; l++) {
                        if ((fi[c] == fi[l] && fj[c] == fj[l] && si[c] == si[l] && sj[c] == sj[l]) || (fi[c] == si[l] && fj[c] == sj[l] && si[c] == fi[l] && sj[c] == fj[l])) {
                            fy[c] = sy[c];
                            fx[c] = sx[c];
                            return true;
                        }
                    }
                    change_a();
                    turn_keeper.add(turn);
                    if (!check_a()) {
                        turn++;
                        sound_effects.play(line_sound_int, 1, 1, 0, 0, 1);
                    }
                    if (turn == number_of_players)
                        turn = 0;
                    c++;
                    print_player_turn();
                    update_text();
                    vibrator.vibrate(20);
                    return true;
                }
            }
        }
        return false;
    }

    private float sqd (float x1, float y1, float x2, float y2) {
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

    private void drawLines(Canvas canvas) {
        /*
        boolean is_p1 = true;
        for(int i = 0; i<c; i++) {
            if (is_p1) {
                canvas.drawLine(sx[i], sy[i], fx[i], fy[i], p1_color);
                is_p1 = false;
            }
            else {
                canvas.drawLine(sx[i], sy[i], fx[i], fy[i], p2_color);
                is_p1 = true;
            }
        }
         */
        int i = 0;
        for (int x:turn_keeper) {
            canvas.drawLine(sx[i], sy[i], fx[i], fy[i], player_colors[x]);
            i++;
        }
    }

    private boolean check_a() {
        repeat = 0;
        boolean flag = false;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                if (a[i][j] == 4 && !winner_checker.contains(linearInt(i,j))) {
                    winner_checker.add(linearInt(i, j));
                    b[i][j] = turn;
                    winner_si[k] = si[c];
                    winner_sj[k] = sj[c];
                    winner_fi[k] = fi[c];
                    winner_fj[k] = fj[c];
                    repeat++;
                    k++;
                    if(names[turn].equalsIgnoreCase(this.getResources().getString(R.string.Score2))){
                        p2Total++;

                    }
                    else{
                        p1Total++;

                    }
                    total++;
                    sound_effects.play(box_sound_int, 1, 1, 0, 0, 1);
                    flag = true;
                }
            }
        }
        return flag;
    }

    private void change_a () {
        if (si[c] == fi[c]) {
            if(si[c] == n-1)
                a[(sj[c]+fj[c])/2][si[c]-1]++;
            else if (si[c] ==  0)
                a[(sj[c]+fj[c])/2][0]++;
            else {
                a[(sj[c]+fj[c])/2][si[c]]++;
                a[(sj[c]+fj[c])/2][si[c]-1]++;
            }
        }
        else {
            if(sj[c] == n-1)
                a[sj[c]-1][(si[c]+fi[c])/2]++;
            else if (sj[c] ==  0)
                a[0][(si[c]+fi[c])/2]++;
            else {
                a[sj[c]][(si[c]+fi[c])/2]++;
                a[sj[c]-1][(si[c]+fi[c])/2]++;
            }
        }
    }

    private void change_a_neg () {
        if (si[c] == fi[c]) {
            if(si[c] == n-1)
                a[(sj[c]+fj[c])/2][si[c]-1]--;
            else if (si[c] ==  0)
                a[(sj[c]+fj[c])/2][0]--;
            else {
                a[(sj[c]+fj[c])/2][si[c]]--;
                a[(sj[c]+fj[c])/2][si[c]-1]--;
            }
        }
        else {
            if(sj[c] == n-1)
                a[sj[c]-1][(si[c]+fi[c])/2]--;
            else if (sj[c] ==  0)
                a[0][(si[c]+fi[c])/2]--;
            else {
                a[sj[c]][(si[c]+fi[c])/2]--;
                a[sj[c]-1][(si[c]+fi[c])/2]--;
            }
        }
    }

    private int linearInt (int ra, int ca) {
        return ra*(n-1) + ca;
    }

    private void check_b() {
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                if (a[i][j]!=4)
                    b[i][j] = -1;
            }
        }
    }

    private void draw_boxes(Canvas canvas) {
        RectF square = new RectF();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                if (a[i][j] == 4) {
                    square.top = circle_y[i];
                    square.bottom = circle_y[i+1];
                    square.left = circle_x[j];
                    square.right = circle_x[j+1];
                    canvas.drawRect(square, player_colors[b[i][j]]);

                }
            }
        }
    }



    private void update_score() {

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                for (int k = 0; k < number_of_players; k++) {
                    if (b[i][j] == k)
                        scores[k]++;
                }
            }
        }
    }

    private void initialise_colors() {
        paint_colors[0] = Color.parseColor("#db5ca2");
        paint_colors[1] = Color.parseColor("#701cfc");

    }

    public void is_end() {
        if (c == 2*n*(n-1)) {
            sound_effects.play(winner_sound_int, 1,1,0,0,1);
            update_score();
            print_names();
            Intent i = new Intent(getContext(), resultPage.class);
            i.putExtra("scores", scores);
            i.putExtra("names", names);
            i.putExtra("players", number_of_players);
            getContext().startActivity(i);
            ((Activity)getContext()).finish();
        }
    }

    private void print_player_turn() {
        System.out.println("PLAYER TURN");
        for(int x : turn_keeper)
            System.out.print(x + " ");

    }

    private void print_names() {
        System.out.println("NAMES");
        for (int i = 0; i < number_of_players; i++) {
             System.out.print(names[i] + " ");
        }
        System.out.println();
    }

    private void update_text() {
        //count.startAnimation(count_animation);
        count.setText(String.valueOf(c));
        p1count.setText(String.valueOf(p1Total));
        p2count.setText(String.valueOf(p2Total));

        turnText.startAnimation(turn_animation);
        turnText.setText(names[turn] + this.getResources().getString(R.string.Turn));




    }

    private void init_sounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sound_effects = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            sound_effects = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        undo_sound_int = sound_effects.load(getContext(), R.raw.undo, 1);
        box_sound_int = sound_effects.load(getContext(), R.raw.square, 1);
        line_sound_int = sound_effects.load(getContext(), R.raw.connect, 1);
        winner_sound_int = sound_effects.load(getContext(), R.raw.winner, 1);
    }
}