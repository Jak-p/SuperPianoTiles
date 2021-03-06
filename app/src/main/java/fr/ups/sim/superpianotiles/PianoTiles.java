package fr.ups.sim.superpianotiles;

import android.media.MediaPlayer;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by mathieukostiuk on 22/03/2016.
 */
public class PianoTiles {

    private Set<Tiles> tilesList = new LinkedHashSet<>();
    private int score;
    private Tiles nextTile;
    private int lastAdded;
    protected Random rand;
    private Difficulte dif;
    private int idMusic;

    public PianoTiles() {
        this.score = 0;
        this.rand = new Random();
        this.nextTile = null;
        this.lastAdded = 0;
        this.dif = Difficulte.MOYEN;
        //this.idMusic = R.raw.cloud_atlas;
    }

    public void newTile() {

        boolean ajoute = false;

        Integer num = this.lastAdded;

        while(!ajoute) {


            int top = this.rand.nextInt(3+1);
            int left = this.rand.nextInt(4+1);
            Tiles a = new Tiles(num.toString(), top, left);
            if (!this.tilesList.contains(a))
                ajoute = this.tilesList.add(a);

        }

        this.nextTile = this.tilesList.iterator().next();

        this.lastAdded++;
    }

    public boolean isCorrectTileTouched(float x, float y, float bottom, float width) {

        if (this.nextTile == null)
            return true;
        else{
            int[] tab = this.nextTile.getPos();

            float left = width*tab[0]/5;
            float top = bottom* tab[1]/4;
            float right = width-width *tab[2]/5;
            float bot = bottom - bottom*tab[3]/4;

            System.err.println("x: "+x+" y: "+y+"left: "+left+" right: "+right+
            " top : "+top+" bot: "+bot);

            this.removeNextTile();
            if (!this.tilesList.isEmpty())
                this.nextTile = this.tilesList.iterator().next();
            else
                this.nextTile = null;


            //Coucou ma pute
            return (x >= (left) &&
                    x <= (right) &&
                    y >= (top) &&
                    y <= (bot));

           // return true;
        }
    }

    public Set<Tiles> getTiles() {
        return this.tilesList;
    }

    private void removeNextTile() {
        if (this.nextTile != null)
            this.tilesList.remove(this.nextTile);
    }

    public int getScore() {
        return this.score;
    }

    public void incrementeScore() {
        this.score++;
    }

    public void setDifficulte(Difficulte d) {
        this.dif = d;
        System.err.println("Difficulte changée!!!");
    }

    public int getDifficulte() {
        return this.dif.ordinal();
    }

    public int getIdMusic(){
        return idMusic;
    }

    public void setIdMusic(int musique){
        this.idMusic = musique;
    }

}
