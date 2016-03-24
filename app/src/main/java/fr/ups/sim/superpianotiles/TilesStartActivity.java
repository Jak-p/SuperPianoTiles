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
        private TilesView t;

        public MonAction(PianoTiles game, TilesView t){
            this.game = game;
            this.t = t;
        }

        public void run() {
            System.err.println("Ajout Tuile lololololol");
            this.game.newTile();
            this.t.setGame(this.game);
            this.t.postInvalidate();
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



        this.t = new Timer() ;
        int delay = 0 ;
        long period = 500;

        t.schedule(
                new MonAction(this.game,this.tilesView),
                delay,
                period) ;


        //this.th = new TileThread(this.game,this.tilesView,this);
        //this.th.run();

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
                    this.tilesView.setGame(this.game);

                    this.tilesView.invalidate();
                 }
                else {
                    this.t.cancel();
                    //this.th.interrupt();
                    setContentView(R.layout.game_over);
                }
                break;
        }





        return true;
    }
}
