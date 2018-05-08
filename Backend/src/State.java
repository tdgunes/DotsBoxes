import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class State {

    public int turn = 0;
    public int[] score;
    public Dot[] dots;
    public int rows;
    public int cols;

    public class Dot {
        public Line[] lines;

        public Dot(Line north, Line east, Line south, Line west) {
            lines = new Line[4];
            lines[0] = north;
            lines[1] = east;
            lines[2] = south;
            lines[3] = west;
        }


        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(lines[0].getValue());
            str.append(",");
            str.append(lines[1].getValue());
            str.append(",");
            str.append(lines[2].getValue());
            str.append(",");
            str.append(lines[3].getValue());

            return str.toString();
        }
    }

    public State(State s) {
        this.rows = s.rows;
        this.cols = s.cols;
        score = new int[2];
        score[0] = s.score[0];
        score[1] = s.score[1];
        dots = new Dot[(rows + 1)*(cols + 1)];
        for (int i = 0; i < rows+1; i++) {
            for (int j = 0; j < cols+1; j++) {
                Dot d = s.dots[i*(rows + 1) + j];
                Dot dot = new Dot(d.lines[0], d.lines[1], d.lines[2], d.lines[3]);
                dots[i*(rows + 1) + j] = dot;
            }
        }
    }

    public State(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        score = new int[2];
        score[0] = 0;
        score[1] = 0;
        dots = new Dot[(rows + 1)*(cols + 1)];
        for (int i = 0; i < rows+1; i++) {
            for (int j = 0; j < cols+1; j++) {
                Line north = (i == 0)? Line.EDGE: Line.NONE;
                Line east = (j == cols)? Line.EDGE: Line.NONE;
                Line south = (i == rows)? Line.EDGE: Line.NONE;
                Line west = (j == 0)? Line.EDGE: Line.NONE;
                Dot dot = new Dot(north, east, south, west);
                dots[i*(rows + 1) + j] = dot;
            }
        }
    }


    public List<State> getChildren() {
        List<State> children = new ArrayList<State>();
        boolean[] flags = new boolean[dots.length];
        for (int i = 0; i < dots.length; i++) {
            Dot dot = dots[i];
            for (Line line : dot.lines) {
                if (line == Line.FULL) {
                    flags[i] = true;
                    break;
                }
            }
        }

        int[] array = new int[2];
        array[0] = -cols-1;
        array[1] = 1;

        for (int i = 0; i < dots.length; i++) {
            for (int j = 0; j < 2; j++) {
                int target = i + array[j];
                if (flags[i] || flags[target]) {
                    State child = new State(this);
                    child.dots[i].lines[j] = Line.FULL;
                    child.dots[target].lines[j+2] = Line.FULL;
                    boolean flag = child.updateScore(i, j == 1);
                    if (!flag) {
                        child.turn = 1 - child.turn;
                    }
                    children.add(child);
                }
            }
        }

        return children;
    }

    public State(String str) {
        StringTokenizer tokenizer = new StringTokenizer(str,",");

        rows = Integer.valueOf(tokenizer.nextToken());
        cols = Integer.valueOf(tokenizer.nextToken());
        turn = Integer.valueOf(tokenizer.nextToken());
        score[0] = Integer.valueOf(tokenizer.nextToken());
        score[1] = Integer.valueOf(tokenizer.nextToken());
        dots = new Dot[(rows + 1)*(cols + 1)];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            Line north = Line.create(Integer.valueOf(tokenizer.nextToken()));
            Line east = Line.create(Integer.valueOf(tokenizer.nextToken()));
            Line south = Line.create(Integer.valueOf(tokenizer.nextToken()));
            Line west = Line.create(Integer.valueOf(tokenizer.nextToken()));
            dots[i] = new Dot(north, east, south, west);
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        str.append(rows);
        str.append(",");
        str.append(cols);
        str.append(",");
        str.append(turn);
        str.append(",");
        str.append(score[0]);
        str.append(",");
        str.append(score[1]);
        for (Dot dot : dots) {
            str.append(",");
            str.append(dot.toString());
        }

        return str.toString();
    }


    public boolean updateScore (int dotId, boolean horizontal) {
        int[] ray = new int[4];
        ray[0] = -cols-1;
        ray[1] = 1;
        ray[2] = cols+1;
        ray[3] = -1;

        boolean flag = true;
        boolean flag2 = true;

        int i = dotId;
        int j = 0;
        do {
            if (dots[i].lines[j] != Line.FULL) {
                flag = false;
                break;
            }
            i += ray[j];
            j = (j + 1) % 4;
        } while (i != dotId);

        if (flag) {
            score[turn]++;
        }

        i = dotId;
        j = (horizontal)? 1: 3;
        do {
            if (dots[i].lines[j] != Line.FULL) {
                flag2 = false;
                break;
            }
            i += ray[j];
            j = (j + 1) % 4;
        } while (i != dotId);

        if (flag2) {
            score[turn]++;
        }

        return flag || flag2;
    }


}
