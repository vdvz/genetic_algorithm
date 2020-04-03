package Gui;

import Environment.Environment;
import Exceptions.UnknownWorldObject;
import World.World;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui_Interface {
    Environment current_env = new Environment();

    private boolean PushStop = false;
    private boolean PushStart = false;
    private boolean PushClear = false;

    private static Gui_Interface instance = new Gui_Interface();

    public void setStopPush(boolean isTrue){
        PushStop = isTrue;
    }

    public boolean getStopPush(){
        return PushStop;
    }

    public void setStartPush(boolean isTrue){
        PushStart = isTrue;
    }

    public boolean getStartPush(){
        return PushStart;
    }


    public void setClearPush(boolean isTrue){
        PushClear = isTrue;
    }

    public boolean getClearPush(){
        return PushClear;
    }


    public static Gui_Interface getInstance() {
        return instance;
    }


    Window window;
    private Gui_Interface(){
        World.getInstance().clearWorld();
        window = new Window();

    }

    public void clear(){
        World.getInstance().clearWorld();
        setStartPush(false);
        setStopPush(false);
        window.clear();
    }

    public void start(){
        current_env.iteration();
        //setStartPush(true);
    }

    public boolean draw(){
        if(PushStop){
            return false;
        }
        window.paint();
        window.write_text();
        return true;
    }

}

class Window extends JFrame{

    Field field;
    JScrollPane jsp;
    JScrollPane text_jsp;
    Info info;
    Buttons buttons;
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

        JButton but = new JButton("HI");
        but.setLocation(850, 430);
        but.setSize(450,300);

        buttons = new Buttons();
        buttons.setLocation(850, 430);
        buttons.setSize(450,200);

        add(buttons);
        add(jsp);
        add(text_jsp);

        setTitle("Genetic algorithm");
        setVisible(true);
    }

    public void clear(){
        paint();
        info.clear();
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

    public void clear() {
        removeAll();
        repaint();
        revalidate();
    }
}

class startFrame extends JFrame{

    startFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 100, 400,400);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);


        JTextField population_size = new JTextField("10");
        JLabel population_size_label = new JLabel("Population size:");

        JLabel meal_probability_label = new JLabel("Meal probability:");
        JTextField meal_probability = new JTextField("0.2");

        JLabel poison_probability_label = new JLabel("Poison probability:");
        JTextField poison_probability = new JTextField("0.1");

        JLabel iter_count_label = new JLabel("Number of iterations:");
        JTextField iter_count = new JTextField("1000");

        JLabel size_dna_label = new JLabel("Bots dna size:");
        JTextField size_dna = new JTextField("60");

        JLabel health_label = new JLabel("Bot health:");
        JTextField health = new JTextField("100");

        JLabel change_commands_label = new JLabel("Count mutate commands:");
        JTextField change_commands = new JTextField("60");


        population_size_label.setPreferredSize(new Dimension(200, population_size.getFont().getSize()+20));
        population_size_label.setAlignmentY(10.0f);
        population_size.setMaximumSize(new Dimension(getMaximumSize().width, population_size.getFont().getSize()+20));

        meal_probability_label.setPreferredSize(new Dimension(200, population_size.getFont().getSize()+20));
        meal_probability_label.setAlignmentY(10.0f);
        meal_probability.setMaximumSize(new Dimension(getMaximumSize().width, population_size.getFont().getSize()+20));;

        iter_count_label.setPreferredSize(new Dimension(200, population_size.getFont().getSize()+20));
        iter_count_label.setAlignmentY(10.0f);
        iter_count.setMaximumSize(new Dimension(getMaximumSize().width, population_size.getFont().getSize()+20));

        poison_probability_label.setPreferredSize(new Dimension(200, population_size.getFont().getSize()+20));
        poison_probability_label.setAlignmentY(10f);
        poison_probability.setMaximumSize(new Dimension(getMaximumSize().width, population_size.getFont().getSize()+20));

        size_dna_label.setPreferredSize(new Dimension(200, population_size.getFont().getSize()+20));
        size_dna_label.setAlignmentY(10.0f);
        size_dna.setMaximumSize(new Dimension(getMaximumSize().width, population_size.getFont().getSize()+20));;

        change_commands_label.setPreferredSize(new Dimension(200, population_size.getFont().getSize()+20));
        change_commands_label.setAlignmentY(10.0f);
        change_commands.setMaximumSize(new Dimension(getMaximumSize().width, population_size.getFont().getSize()+20));

        health_label.setPreferredSize(new Dimension(200, population_size.getFont().getSize()+20));
        health_label.setAlignmentY(10f);
        health.setMaximumSize(new Dimension(getMaximumSize().width, population_size.getFont().getSize()+20));




        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(population_size_label)
                                .addComponent(meal_probability_label)
                                .addComponent(poison_probability_label)
                                .addComponent(iter_count_label)
                                .addComponent(size_dna_label)
                                .addComponent(change_commands_label)
                                .addComponent(health_label))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(population_size)
                                .addComponent(meal_probability)
                                .addComponent(poison_probability)
                                .addComponent(iter_count)
                                .addComponent(size_dna)
                                .addComponent(change_commands)
                                .addComponent(health))

        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(population_size_label)
                                .addComponent(population_size))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(meal_probability_label)
                                .addComponent(meal_probability))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(poison_probability_label)
                                .addComponent(poison_probability))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(iter_count_label)
                                .addComponent(iter_count))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(size_dna_label)
                                .addComponent(size_dna))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(change_commands_label)
                                .addComponent(change_commands))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(health_label)
                                .addComponent(health))

        );




        JButton confirm_and_start = new JButton("START");

        confirm_and_start.setAlignmentX(60.0f);
        confirm_and_start.setAlignmentY(20.0f);

        confirm_and_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String text = population_size.getText();
                //TODO Проверить на ошибки
                dispose();
                Gui_Interface.getInstance().start();
            }
        });

        add(panel);
        add(confirm_and_start);

        setVisible(true);

    }

}

class Buttons extends JPanel{

    Buttons(){
        setLayout(null);

        JButton start = new JButton("START");
        start.setSize(100, 50);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new startFrame();
            }
        });

        JButton stop = new JButton("STOP");
        stop.setSize(100, 50);
        stop.setLocation(start.getWidth() + 20,0);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui_Interface.getInstance().start();
            }
        });

        JButton clear = new JButton("CLEAR");
        clear.setSize(100, 50);
        clear.setLocation(stop.getX() + stop.getWidth() + 20,0);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui_Interface.getInstance().clear();
            }
        });

        add(clear);
        add(start);
        add(stop);

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
                        System.out.println("Unknown object in the World");
                        System.exit(-1);
                        break;
                }
                g2.fillRect(i*(RECT_WIDTH + OFFSET_BETWEEN_RECT),
                        j*(RECT_HEIGHT + OFFSET_BETWEEN_RECT),
                                RECT_WIDTH, RECT_HEIGHT);
            }
        }
    }
}
