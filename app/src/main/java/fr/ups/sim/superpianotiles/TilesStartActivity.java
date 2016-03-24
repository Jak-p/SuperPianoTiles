package fr.ups.sim.superpianotiles;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class TilesStartActivity extends Activity {


    class MonAction extends TimerTask {
        private PianoTiles game;

        public MonAction(PianoTiles game){
            this.game = game;
        }

        public void run() {
            System.err.println("Ajout Tuile lololololol");
            this.game.newTile();

        }


    }

    private PianoTiles game;
    private TilesView tilesView;
    private Timer t;
    private Thread th;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiles_start);

        this.game = new PianoTiles();



        //On récupère la view (JFrame en SWING) du jeu
        this.tilesView = (TilesView) findViewById(R.id.view);


        //On met en place un listener qui réagira lorsque l'on touchera l'écran tactile
        this.tilesView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchEventHandler(event);

            }
        });

        this.th = new TileThread(this.game,this.tilesView);
        this.th.run();

        this.t = new Timer() ;
        int delay = 0 ;
        long period = 3000;

        t.schedule(
                new MonAction(this.game),
                delay,
                period) ;




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tiles_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // ICI - A compléter pour déclencher l'ouverture de l'écran de paramétrage
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * Handler utilisé lorsque le joueur touche l'écran
     */
    private boolean onTouchEventHandler (MotionEvent evt){
        Log.i("TilesView", "Touch event handled");

        switch (evt.getAction()) {
            case MotionEvent.ACTION_DOWN:


                if (this.game.isCorrectTileTouched(evt.getX(), evt.getY(), this.tilesView.getBottom(),
                            this.tilesView.getWidth()))
                {

                    //this.game.newTile();
                    //this.tilesView.setGame(this.game);

                    //this.tilesView.invalidate();
                 }
                else {
                    this.t.cancel();
                    this.th.interrupt();
                    setContentView(R.layout.game_over);
                }
                break;
        }





        return true;
    }
}


/*yes java's timer can be used, but as the question asks for better way (for mobile). Which is explained Here.

For the sake of StackOverflow:

Since Timer creates a new thread it may be considered heavy,

if all you need is to get is a call back while the activity is running a Handler can be used in conjunction with a

Runnable:

private final int interval = 1000; // 1 Second
private Handler handler = new Handler();
private Runnable runnable = new Runnable(){
    public void run() {
        Toast.makeText(MyActivity.this, "C'Mom no hands!", Toast.LENGTH_SHORT).show();
    }
};
...
handler.postAtTime(runnable, System.currentTimeMillis()+interval);
handler.postDelayed(runnable, interval);
or a Mesage

private final int EVENT1 = 1;
private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
        case Event1:
            Toast.makeText(MyActivity.this, "Event 1", Toast.LENGTH_SHORT).show();
            break;

        default:
            Toast.makeText(MyActivity.this, "Unhandled", Toast.LENGTH_SHORT).show();
            break;
        }
    }
};

...

Message msg = handler.obtainMessage(EVENT1);
handler.sendMessageAtTime(msg, System.currentTimeMillis()+interval);
handler.sendMessageDelayed(msg, interval);
on a side note this approach can be used, if you want to run a piece of code in the UI thread from an another thread.

if you need to get a call back even if your activity is not running then, you can use an AlarmManager*/