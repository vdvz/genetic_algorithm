package Gui;

import World.World;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;


public class Gui_Interface {

    private static Gui_Interface instance = new Gui_Interface();


    public static Gui_Interface getInstance() {
        return instance;
    }


    Window window;
    private Gui_Interface(){
        window = new Window();
    }

    public void draw(){
        window.paint();
        window.write_text();
    }

}

class Window extends JFrame{

    Field field;
    JScrollPane jsp;
    JScrollPane text_jsp;
    Info info;

    Window(){
    }

    @Override
    protected void frameInit() {
        super.frameInit();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        field = new Field();
        field.setLayout(null);
        field.setPreferredSize(field.getMaximumSize());

        jsp = new JScrollPane(field, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setSize(750,670);
        jsp.setViewportView(field);
        jsp.setLocation(new Point(50, 10));
        jsp.setPreferredSize(new Dimension(750,670));


        info = new Info();
        text_jsp = new JScrollPane( info, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        text_jsp.setSize(450,400);
        //text_jsp.setViewportView(info);
        text_jsp.setLocation(new Point(850, 10));
        text_jsp.setPreferredSize(new Dimension(450,400));

        add(jsp);
        add(text_jsp);


        setTitle("Genetic algorithm");
        setVisible(true);
    }

    public void paint(){
        field.getGraphics().clearRect(0, 0, field.getMaximumSize().width, field.getMaximumSize().height);
        //field.updateUI();
        field.repaint();
        //field.removeAll();
        //field.update(field.getGraphics());
    }

    public void write_text(){
        info.add_text_info();
        text_jsp.getVerticalScrollBar().setValue(text_jsp.getVerticalScrollBar().getMaximum());
    }

}


class Info extends JPanel{
    int size = 0;
    Info(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Start iteration"));
    }


    public void add_text_info(){
        JLabel jlb = new JLabel("Vados\nVV: " + "\n NN: " + getSize().height);
        size+=jlb.getFont().getSize();
        if(getSize().height < size){
            setSize(getWidth(),getHeight()+jlb.getFont().getSize());
        }
        add(jlb);
        repaint();
        revalidate();
    }

}


class Field extends JPanel {
    int OFFSET_BETWEEN_RECT = 2;
    int RECT_HEIGHT = 15;
    int RECT_WIDTH = 15;

    Field(){
        super();
        setMaximumSize(new Dimension(World.getInstance().getWorldSize()*(RECT_WIDTH+OFFSET_BETWEEN_RECT),
                World.getInstance().getWorldSize()*(RECT_HEIGHT+OFFSET_BETWEEN_RECT)));
        setAutoscrolls(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0; i<World.getInstance().getWorldSize();i++){
            for(int j = 0; j<World.getInstance().getWorldSize();j++) {
                switch(World.getInstance().getObject(i, j).getType()) {
                    case 1:
                        g2.setColor(new Color(125, 167, 116));
                        //g2.setColor(Color.CYAN);
                        break;
                    case 0:
                        g2.setColor(new Color(42, 179, 231));
                        break;
                    case 2:
                        g2.setColor(new Color(130, 100, 84));
                        break;
                    case 3:
                        g2.setColor(Color.RED);
                        break;
                    case -1:
                        g2.setColor(new Color(217, 146, 54));
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
                g2.fillRect(i*(RECT_WIDTH + OFFSET_BETWEEN_RECT),
                        j*(RECT_HEIGHT + OFFSET_BETWEEN_RECT),
                                RECT_WIDTH, RECT_HEIGHT);
            }
        }
    }
}
