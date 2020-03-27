import World.World;

import javax.swing.*;
import java.awt.*;

public class Gui_Interface {

    private static Gui_Interface instance = new Gui_Interface();


    public static Gui_Interface getInstance() {
        return instance;
    }

    final int WINDOW_SIZE_WIDTH = 1000;
    final int WINDOW_SIZE_HEIGHT = 1000;
    JFrame frame;
    JPanel field;

    private Gui_Interface(){
        frame = new JFrame(){};
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(0/*dimension.width/2  - WINDOW_SIZE_WIDTH/2*/ , 0 /*dimension.height/2 - WINDOW_SIZE_HEIGHT/2*/,
                WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT);
        field = new Field();
        frame.add(field);
        frame.setTitle("Genetic algorithm");
        frame.setVisible(true);
    }

    public void draw(){
        field.repaint();
        //field.update(field.getGraphics());
    }

}

class Field extends JPanel{

    int RECT_WIDTH = 20;
    int RECT_HEIGHT = 20;
    int OFFSET_WIDTH = 200;
    int OFFSET_HEIGHT = 75;
    int OFFSET_BETWEEN_RECT = 3;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0; i<World.getInstance().getWorldSize();i++){
            for(int j = 0; j<World.getInstance().getWorldSize();j++) {
                switch(World.getInstance().getObject(i, j).getType()) {
                    case 1:
                        //g2.setColor(new Color(125, 167, 116));
                        g2.setColor(Color.CYAN);
                        break;
                    case 0:
                        g2.setColor(new Color(42, 179, 231));
                        break;
                    case 2:
                        g2.setColor(new Color(130, 100, 84));
                        break;
                    default:
                        g2.setColor(new Color(217, 146, 54));
                        break;
                }
                g2.fillRect(OFFSET_WIDTH + i*(RECT_WIDTH + OFFSET_BETWEEN_RECT),
                            OFFSET_HEIGHT + j*(RECT_HEIGHT + OFFSET_BETWEEN_RECT),
                                RECT_WIDTH, RECT_HEIGHT);
            }
        }
    }

}
