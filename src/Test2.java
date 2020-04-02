
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class Test2 {

    public static void main(String[] args) {
        Frames.getInstance();

        for(int i = 0;i<10;i++){
            Frames.getInstance().pri(i);
        }

    }

}


class Frames{

    private static Frames instance = new Frames();


    public static Frames getInstance() {
        return instance;
    }

    JFrame frame;
    JPanel field;
    JScrollPane jsp;

    private Frames(){
        frame = new JFrame(){};
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setSize(100,100);
        panel.setLayout(new GridLayout(20, 20));
        for(int i =0; i<20; i++){
            for(int j =0; j<20; j++){
                panel.add(new JButton("Button" + i + j));
            }
        }
        jsp = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setSize(100,100);
        jsp.setPreferredSize(new Dimension(100,100));
        frame.add(jsp);

        frame.setTitle("Genetic algorithm");
        frame.setVisible(true);
    }

    public void pri(int i ){
        System.out.println("Itter: "+ i );
    }

}