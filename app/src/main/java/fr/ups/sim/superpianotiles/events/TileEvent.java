package fr.ups.sim.superpianotiles.events;

import java.util.EventObject;

/**
 * Created by mathieukostiuk on 26/03/2016.
 */
public final class TileEvent extends EventObject {
    private final int nbTiles;

    /**
     * Constructs a new instance of this class.
     *
     * @param source the object which fired the event.
     */
    public TileEvent(Object source, int nbTiles) {
        super(source);
        this.nbTiles = nbTiles;
    }

    public int getNbTiles() {
        return this.nbTiles;
    }



}
