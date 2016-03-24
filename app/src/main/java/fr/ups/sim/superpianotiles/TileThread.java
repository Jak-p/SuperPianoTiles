package fr.ups.sim.superpianotiles;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by IDA on 24/03/2016.
 */
public class TileThread extends Thread {


    class MonAction extends TimerTask {
        private PianoTiles game;

        public MonAction(PianoTiles game){
            this.game = game;
        }

        public void run() {
            System.err.println("Ajout Tuile lololololol");
            game.newTile();
        }
    }

    private PianoTiles game ;
    private TilesView t;
    private TilesStartActivity tt;


    public TileThread(PianoTiles game, TilesView t, TilesStartActivity tt)
    {
        super() ;
        this.t = t;
        this.game = game;
        this.tt = tt;
    }

    @Override
    public void run()
    {

    }




}
