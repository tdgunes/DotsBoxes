import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame {



    public GUI(){

        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dot&Boxes");

        List<String> states = readFile();
        CreatePanel panel = new CreatePanel(states.get(0));
        add(panel);
        setVisible(true);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {}
        System.out.println("waiting 2 seconds");
        for (int i = 1; i < states.size(); i++) {
            panel.updateState(states.get(i));
            panel.repaint();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            System.out.println("waiting 2 seconds");
        }
    }

    private List<String> readFile(){
        ArrayList<String> states = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("2x2-randomagents.csv"));
        } catch (FileNotFoundException e) { e.printStackTrace();}

        while(scanner.hasNext())
            states.add(scanner.nextLine());
        return states;
    }

    public class CreatePanel extends JPanel{

        private double rows;
        private double cols;
        private State state;

        public CreatePanel(String state){
            updateState(state);
        }

        public void updateState(String str){
            this.state = new State(str);
            this.rows = (state.rows + 1)*1.0;
            this.cols = (state.cols + 1)*1.0;

        }

        private int getX(int i){
            return (int) ((480.0/rows)*(i*1.0)+20);
        }

        private int getY(int j){
            return (int) ((480.0/cols)*(j*1.0)+20);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0,  dots = 0; i < state.rows+1; i++){
                for(int j = 0; j < state.cols + 1; j++, dots++){
                    g.setColor(Color.BLACK);
                    g.fillOval(getX(i),  getY(j), 10, 10);
                    g.setColor(Color.RED);
                    StringTokenizer st = new StringTokenizer(state.dots[dots].toString(), ",");
                    System.out.println(state.dots[dots].toString());
                    if(Integer.parseInt(st.nextToken()) == 1) {
                        g.drawLine(getX(i), getY(j), getX(i), getY(j - 1));
                        System.out.println("Entro 1");
                    }
                    if(Integer.parseInt(st.nextToken()) == 1) {
                        g.drawLine(getX(i), getY(j), getX(i + 1), getY(j));
                        System.out.println("Entro 2");
                    }
                    if(Integer.parseInt(st.nextToken()) == 1) {
                        g.drawLine(getX(i), getY(j), getX(i), getY(j + 1));
                        System.out.println("Entro 3");
                    }
                    if(Integer.parseInt(st.nextToken()) == 1) {
                        g.drawLine(getX(i), getY(j), getX(i - 1), getY(j));
                        System.out.println("Entro 4");
                    }
                }
            }

        }
    }
}
