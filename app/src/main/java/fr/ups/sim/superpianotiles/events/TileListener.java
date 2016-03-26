package fr.ups.sim.superpianotiles.events;

import java.util.EventListener;

/**
 * Created by mathieukostiuk on 26/03/2016.
 */
public interface TileListener extends EventListener {

    public void nbTileChanged(TileEvent event);
}
