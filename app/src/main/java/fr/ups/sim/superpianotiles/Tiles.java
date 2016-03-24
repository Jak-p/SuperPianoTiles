package fr.ups.sim.superpianotiles;


/**
 * Created by mathieukostiuk on 22/03/2016.
 */
public class Tiles {
    private String order;
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Tiles(String order, int top, int left) {
        this.order = order;
        this.left = left;
        this.top = top;
        this.right = 4-left;
        this.bottom = 3-top;
    }

    @Override
    public String toString() {
        return this.order;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Tiles){
            Tiles compare = (Tiles) o;
            int[] posThis = this.getPos();
            int[] posComp = compare.getPos();
            return ((posThis[1] == posComp[1]) && (posThis[2] == posComp[2]) &&
                    (posThis[0] == posComp[0]) && (posThis[3] == posComp[3]));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (19);
    }



    public int[] getPos() {
        return new int[]{this.left, this.top, this.right, this.bottom};
    }
}
