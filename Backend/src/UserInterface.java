import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UserInterface {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
}


class MyPanel extends JPanel {

    JButton button;
    JButton button2;
    List<State> states = new ArrayList<>();
    private double rows;
    private double cols;
    int stateCounter = 0;

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
        button = new JButton(">");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(">");
                if (stateCounter  != states.size())
                    stateCounter++;
                repaint();
            }
        });
//
//        button2 = new JButton("<");
//        button2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("<");
//                if (stateCounter > 0)
//                    stateCounter--;
//                repaint();
//            }
//        });

        this.add(button, BorderLayout.PAGE_END);
//        this.add(button2, BorderLayout.PAGE_END);

        getStates();
        rows = states.get(0).rows;
        cols = states.get(0).cols;
    }

    private void getStates(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("../data/3.csv"));
        } catch (FileNotFoundException e) { e.printStackTrace();}

        while(scanner.hasNext()){
            states.add(new State(scanner.nextLine()));
        }
    }


    private int getX(int i){
        return (int) ((480.0/rows)*(i*1.0)+20);
    }
    private int getY(int j){
        return (int) ((480.0/cols)*(j*1.0)+20);
    }


    public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        State state = states.get(stateCounter);
        for (int i = 0,  dots = 0; i < state.rows+1; i++){
            for(int j = 0; j < state.cols + 1; j++, dots++){
                g.setColor(Color.BLACK);
                g.fillOval(getX(i),  getY(j), 10, 10);
                g.setColor(Color.RED);
                g.drawString("P0: " + state.score[0], 10, 600);
                g.drawString("P1: " + state.score[1], 50, 600);

                StringTokenizer st = new StringTokenizer(state.dots[dots].toString(), ",");
                System.out.println(state.dots[dots].toString());
                if(Integer.parseInt(st.nextToken()) == 1) {
                    g.drawLine(getX(i), getY(j), getX(i - 1), getY(j ));
                    System.out.println("Entro 1");
                }
                if(Integer.parseInt(st.nextToken()) == 1) {
                    g.drawLine(getX(i), getY(j), getX(i), getY(j + 1));
                    System.out.println("Entro 2");
                }
                if(Integer.parseInt(st.nextToken()) == 1) {
                    g.drawLine(getX(i), getY(j), getX(i+1), getY(j));
                    System.out.println("Entro 3");
                }
                if(Integer.parseInt(st.nextToken()) == 1) {
                    g.drawLine(getX(i), getY(j), getX(i), getY(j-1));
                    System.out.println("Entro 4");
                }
            }
        }

    }
}