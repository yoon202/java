package Lesson6;


public class Player {
    private String name;
    private int favorite;
    private int score;
    private boolean out;

    public Player(String name, int favorite) {
        this.name = name;
        this.favorite = favorite;
        this.score = 0;
        this.out = false;
    }

    public String getName() { return name; }
    public int getFavorite() { return favorite; }

    public void addScore() { score++; }
    public int getScore() { return score; }
    public void resetScore() { score = 0; }

    public boolean isOut() { return out; }
    public void eliminate() { out = true; }
}
