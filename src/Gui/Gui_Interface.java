package Gui;

import Entities.Bot;
import Environment.Environment;
import Exceptions.NotConverge;
import World.World;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Gui_Interface implements InterfaceForGUI{

    Environment current_env = null;

    private Timer timer = new Timer(0, null);

    Window window;

    private Gui_Interface(){
        World.getInstance().clearWorld();
        //при созданиии нового окна отрисуется пустой мир
        window = new Window();

    }

    private static Gui_Interface instance = new Gui_Interface();

    public static Gui_Interface getInstance() {
        return instance;
    }



    public void newGeneration(){
        try {
            current_env.addNewGeneration();
        } catch (NotConverge notConverge) {
            notConverge.printStackTrace();
        }
    }

    @Override
    public Timer getTimer(){
        return timer;
    }

    public Environment getCurrent_env(){
        return current_env;
    }

    @Override
    public void clear(){
        current_env = null;
        timer.stop();
        window.clear();
    }

    @Override
    public void start(){
        current_env = new Environment();
        current_env.iteration();

    }

    @Override
    public void start(int population_size, float meal_prob, float poison_prob, int iter_count,
                      int size_dna, int health, int count_changed_command) throws Exception {
        current_env = new Environment(population_size, meal_prob, poison_prob, iter_count, size_dna, health, count_changed_command);
        current_env.iteration();


    }

    public void stop(){
        timer.stop();
    }

    public void write_message(String text){
        window.write_text(text);
    }

    @Override
    public void draw(){
        window.paint();
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
        info.setMaximumSize(text_jsp.getPreferredSize());

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
        field.clear();
        info.clear();
    }

    public void paint(){
        field.getGraphics().clearRect(0, 0, field.getMaximumSize().width, field.getMaximumSize().height);
        //field.updateUI();
        field.repaint();
        //field.removeAll();
        //field.update(field.getGraphics());
    }

    public void write_text(String text){
        info.add_text_info(text);
        text_jsp.getVerticalScrollBar().setValue(text_jsp.getVerticalScrollBar().getMaximum());
    }

}

class Info extends JPanel{
    int size = 0;
    Info(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Welcome to genetic algorithm!"));
        add(new JLabel("Press START for begin"));
        add(new JLabel("Press STOP for pause"));
        add(new JLabel("Press CLEAR for start all again"));
        add(new JLabel("Press BOT_INFO for find out info about bots"));
        add(new JLabel("Press START for continue"));
    }


    public void add_text_info(String text){
        JLabel jlb = new JLabel(text);
        jlb.setMaximumSize(new Dimension(getMaximumSize().width, jlb.getPreferredSize().height));
        //jlb.setMaximumSize(new Dimension(getMaximumSize()));
        size += jlb.getPreferredSize().height;
        if(getSize().height < size){
            setSize(getWidth(),getHeight()+jlb.getPreferredSize().height);
        }
        add(jlb);
        repaint();
        revalidate();
    }

    public void clear() {
        removeAll();
        add_text_info("Welcome to genetic algorithm!");
        add_text_info("Press START for begin");
        add_text_info("Press STOP for pause");
        add_text_info("Press CLEAR for start all again");
        add_text_info("Press START for continue");
        repaint();
        revalidate();
    }
}

class startFrame extends JFrame{

    startFrame(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JButton confirm_and_start = new JButton("START");
        JTextPane err_pane = new JTextPane();
        err_pane.setBackground(null);
        err_pane.setEditable(false);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(population_size_label)
                                .addComponent(meal_probability_label)
                                .addComponent(poison_probability_label)
                                .addComponent(iter_count_label)
                                .addComponent(size_dna_label)
                                .addComponent(change_commands_label)
                                .addComponent(health_label)
                                .addComponent(confirm_and_start))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(population_size)
                                .addComponent(meal_probability)
                                .addComponent(poison_probability)
                                .addComponent(iter_count)
                                .addComponent(size_dna)
                                .addComponent(change_commands)
                                .addComponent(health)
                                .addComponent(err_pane))

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
                        .addGroup(layout.createParallelGroup()
                                .addComponent(confirm_and_start)
                                .addComponent(err_pane))

        );


        confirm_and_start.setAlignmentX(60.0f);
        confirm_and_start.setAlignmentY(20.0f);

        confirm_and_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(Gui_Interface.getInstance().getCurrent_env()!=null){
                    err_pane.setText("Already run!");
                    err_pane.validate();
                    return;
                }
                try{
                Gui_Interface.getInstance().start(
                        Integer.parseInt(population_size.getText()),
                        Float.parseFloat(meal_probability.getText()),
                        Float.parseFloat(poison_probability.getText()),
                        Integer.parseInt(iter_count.getText()),
                        Integer.parseInt(size_dna.getText()),
                        Integer.parseInt(health.getText()),
                        Integer.parseInt(change_commands.getText())
                );
                } catch (NumberFormatException ex){
                    err_pane.setText("Incorrect number format, please write Float or Int");
                    err_pane.validate();
                    return;
                } catch (Exception ex) {
                    err_pane.setText(ex.getMessage());
                    //ex.printStackTrace();
                    err_pane.validate();
                    return;
                }
                dispose();
            }
        });

        add(panel);

        setVisible(true);
    }

}


class DnaInfo extends JFrame{

    DnaInfo(ArrayList<Bot> list){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(450, 100, 400,400);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane jsp = new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        ComboBoxRender render = new ComboBoxRender(getSize());

        for(int i = 0; i<list.size(); i++){
            JComboBox cmbox = new JComboBox();
            cmbox.setPreferredSize(new Dimension(panel.getPreferredSize().width, cmbox.getPreferredSize().height));
            cmbox.setEditor(new ComboBoxEditor(i));
            cmbox.setRenderer(render);
            cmbox.addItem(list.get(i));

            cmbox.setEditable(true);
            panel.add(cmbox);
        }

        add(jsp);
        setVisible(true);
    }

    class ComboBoxEditor extends BasicComboBoxEditor {
        private JPanel panel = new JPanel();
        private Object selectedItem;

        public ComboBoxEditor(int bot_number) {
            JLabel label = new JLabel();
            label.setOpaque(false);
            label.setSize(getPreferredSize());
            label.setText("Bot: " + bot_number);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.BLACK);

            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
            panel.add(label);
            //panel.setBackground(Color.GREEN);
        }

        public Component getEditorComponent() {
            return this.panel;
        }

        public Object getItem() {
            return this.selectedItem;
        }

        public void setItem(Object item) {
            this.selectedItem = item;
            //label.setText(((Bot) item).getName());
        }
    }

    class ComboBoxRender extends JTextArea implements ListCellRenderer{

        ComboBoxRender(Dimension dim){
            setEditable(false);
            setLineWrap(true);
            //setWrapStyleWord(true);
            setMaximumSize(new Dimension(dim.width-20, dim.height));
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
            Bot b = (Bot) value;
            setText(b.getDnaString());
            return this;
        }

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
                if(Gui_Interface.getInstance().getCurrent_env()==null){
                    new startFrame();
                    Gui_Interface.getInstance().write_message("Start iteration!");
                }else{
                    Gui_Interface.getInstance().getTimer().start();
                }
            }
        });

        JButton stop = new JButton("STOP");
        stop.setSize(100, 50);
        stop.setLocation(start.getWidth() + 20,0);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gui_Interface.getInstance().stop();
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

        JButton bots_info = new JButton("BOT INFO");
        bots_info.setSize(100, 50);
        bots_info.setLocation(0,start.getHeight() + 20);
        bots_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Gui_Interface.getInstance().getTimer().isRunning()){
                    Gui_Interface.getInstance().stop();
                }
                if(Gui_Interface.getInstance().getCurrent_env() == null){
                    Gui_Interface.getInstance().write_message("Env have not been start yet");
                    return;
                }
                new DnaInfo(Gui_Interface.getInstance().getCurrent_env().getBotList());
            }
        });


        add(bots_info);
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

    public void clear() {
        World.getInstance().clearWorld();
        getGraphics().clearRect(0, 0, getMaximumSize().width, getMaximumSize().height);
        repaint();
    }
}
