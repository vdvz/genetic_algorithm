
import Entities.Bot;
import sun.text.resources.cs.JavaTimeSupplementary_cs;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
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
        frame.setBounds(400,200,400,200);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel();
        panel.setSize(500,500);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(int i = 0; i<5; i++){
            JComboBox box = new JComboBox();
            box.setRenderer(new ComboBoxRender());
            box.setEditor(new ComboBoxEditor(i));
            box.addItem(new Bot(100));
            box.setEditable(true);
            //box.setSize(500,30);
            panel.add(box);

        }

        frame.add(panel);
        frame.setTitle("Genetic algorithm");
        frame.setVisible(true);
    }

    public void pri(int i ){
        System.out.println("Itter: "+ i );
    }


}

class ComboBoxEditor extends BasicComboBoxEditor {
    private JPanel panel = new JPanel();
    private Object selectedItem;

    public ComboBoxEditor(int bot_number) {
        JLabel label = new JLabel();
        label.setOpaque(false);
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

class ComboBoxRender extends JLabel implements ListCellRenderer{

    ComboBoxRender(){
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Bot b = (Bot) value;
        setText(b.getDnaString());
        return this;
    }

}