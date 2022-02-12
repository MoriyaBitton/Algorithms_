package src;

public class Sufgania {

    private static int time = 2;

    //O(1)
    public static int getTime(int numOfSuf, int numPfPlace) {
        if (numPfPlace >= numOfSuf) return time;
        if ((numOfSuf * time) % numPfPlace == 0) return ((numOfSuf * time) / numPfPlace);
        return (((numOfSuf * time) / numPfPlace) + 1);
    }

    public static void main(String[] args) {
        int d = 12, p = 3;
        System.out.println("Time: " + getTime(d, p));
    }
}
