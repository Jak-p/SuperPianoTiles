package fr.ups.sim.superpianotiles.events;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mathieukostiuk on 26/03/2016.
 */
public class TileCounter {
    // Conteneur pour tous les observateurs sur cet objet.
    private final List listenerList = new LinkedList();

    /**
     * Enregistre un écouteur de température.
     * @param listener L’écouteur.
     */
    public void addTemperatureListener(TileListener listener) {
        if (listener == null) {
            return;
        }
        // On ajoute le listener et son type.
        // Cela permet de stocker plusieurs types de listeners dans le même conteneur.
        listenerList.add(TileListener.class);
        listenerList.add(listener);
    }

    /**
     * Désenregistre un écouteur de température.
     * @param listener L’écouteur.
     */
    public void removeTemperatureListener(TileListener listener) {
        if (listener == null) {
            return;
        }
        removeListener(TileListener.class, listener);
    }

    /**
     * Méthode générique pour désenregistrer un écouteur.
     * @param listenerClass Le type de l’écouteur.
     * @param listener L’écouteur.
     */
    private void removeListener(Class listenerClass, Object listener) {
        int listSize = listenerList.size();
        for (int index = 0 ; index < listSize ; index += 2) {
            // Comme un listener peut implémenter plusieurs interfaces et s'enregistrer plusieurs fois,
            // on vérifie qu'on enlève le premier rencontré qui porte le bon type.
            if (listenerList.get(index) == listenerClass && listenerList.get(index + 1) == listener) {
                listenerList.remove(index + 1);
                listenerList.remove(index);
                break;
            }
        }
    }


    public void fireNbTileChanged(int nbTile) {
        TileEvent event = null;
        int listSize = listenerList.size();
        // On parcourt la liste à rebours.
        for (int index = listSize - 2 ; index >= 0 ; index -= 2) {
            // Seules les instances de TemperatureListener nous intéressent.
            if (listenerList.get(index) == TileListener.class) {
                // On crée l'objet event de manière "lazy" (paresseuse), uniquement quand on en a vraiment besoin.
                if (event == null) {
                    event = new TileEvent(this, nbTile);
                }
                TileListener  listener = (TileListener)listenerList.get(index + 1);
                // On invoque la notification appropriée sur le listener.
                listener.nbTileChanged(event);
            }
        }
    }
}
