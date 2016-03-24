package fr.ups.sim.superpianotiles;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by IDA on 24/03/2016.
 */
public class TileThread extends Thread {

    private PianoTiles game ;


    public TileThread(PianoTiles game)
    {
        super() ;
        this.game = game ;
    }

    public void newTile()
    {
        Timer timer = new Timer() ;
        int delay = 3000 ;

        timer.schedule(
                new TimerTask()
                {   public void run() {
                        game.newTile();
                    }
                },
                delay) ;

    }



}
