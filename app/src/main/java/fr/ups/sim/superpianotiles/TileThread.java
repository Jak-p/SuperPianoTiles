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
    private TilesView tilesView;


    public TileThread(PianoTiles game, TilesView t)
    {
        super() ;
        this.game = game ;
        this.tilesView = t;
    }

    @Override
    public void run()
    {
        while (true) {
            System.err.println("Affiche");
            this.tilesView.setGame(this.game);
            this.tilesView.invalidate();
        }

    }




}
