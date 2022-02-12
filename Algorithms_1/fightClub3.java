package src;

public class fightClub3 {

    static class Player {
        String name;
        boolean Alive = true;
        double p;

        public Player(String name, double p) {
            this.name = name;
            this.p = p;
        }
    }

    //  Fight between 3 soldiers each one have his strategy
    //  C - 100%, B - 80%, D - 50%
    public static void fight3(Player p1, Player p2, Player p3) {
        int turn = (int) (Math.random() * 3);
        int steps = 0;
        Player target;
        while (p1.Alive && p2.Alive && p3.Alive) {
            if (steps > 200) {
                System.out.println("Time is over - no winner");
                return;
            }
            steps++;
            turn++;
            turn = (turn % 3) + 1;
            switch (turn) {
                case 1:
                    target = strategy(p1, p2, p3);
                    if ((target != null) && (Math.random() < p1.p)) {
                        target.Alive = false;
                        System.out.println(target.name + " was killed by " + p1.name);
                    } else {
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2:
                    target = strategy(p2, p1, p3);
                    if ((target != null) && (Math.random() < p2.p)) {
                        target.Alive = false;
                        System.out.println(target.name + " was killed by " + p2.name);
                    } else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
                case 3:
                    target = strategy(p3, p1, p2);
                    if ((target != null) && (Math.random() < p3.p)) {
                        target.Alive = false;
                        System.out.println(target.name + " was killed by " + p3.name);
                    } else {
                        System.out.println(p3.name + " missed");
                    }
                    break;
            }
        }
        if (!p1.Alive) {
            if (turn == 2) fight2(p3, p2, steps);
            else fight2(p3, p2, steps);
        } else if (!p2.Alive) {
            if (turn == 3) fight2(p1, p3, steps);
            else fight2(p3, p1, steps);
        } else { // p3 isn't a live
            if (turn == 2) fight2(p1, p2, steps);
            else fight2(p2, p1, steps);
        }
    }

    private static Player strategy(Player p1, Player p2, Player p3) {
        if ((p1.p < p2.p) && (p1.p < p3.p)) {
            return null;
        }
        if (p2.p > p3.p) return p2;
        return p3;
    }

    // 2 players who left fight between them
    public static void fight2(Player p1, Player p2, int steps) {
        int turn = 1;
        while (p1.Alive && p2.Alive) {
            if (steps > 200) {
                System.out.println("Time is over - no winner");
                return;
            }
            steps++;
            switch (turn) {
                case 1:
                    if (Math.random() < p1.p) {
                        p2.Alive = false;
                        System.out.println(p2.name + " was killed by " + p1.name);
                    } else {
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2:
                    if (Math.random() < p2.p) {
                        p1.Alive = false;
                        System.out.println(p1.name + " was killed by " + p2.name);
                    } else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
            }
            turn = 3 - turn;
        }
        System.out.println("The winner is: " + (p1.Alive ? p1.name : p2.name));
        System.out.println("Steps = " + steps);
    }

    public static void main(String[] args) {
        Player b = new Player("B", 1.0), c = new Player("C", 0.8), d = new Player("D", 0.5);
        fight3(b, c, d);
    }
}

