// TODOs 11 à 14
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CalculatriceSwing extends JFrame {


    public CalculatriceSwing() {
        super("Calculatrice");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(350, 250);
        this.setLocationRelativeTo(null);
        
        JPanel clacJPanel = (JPanel) this.getContentPane();

        JTextField claculesField = new JTextField("");
        claculesField.setEditable(false);
        claculesField.setFocusable(false);
        claculesField.setHorizontalAlignment(JTextField.CENTER);
        clacJPanel.add(claculesField, BorderLayout.NORTH);

        JButton calculerButton = new JButton("Calculer");
        calculerButton.setEnabled(false);
        calculerButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                claculesField.setText(evaluateExpression(claculesField.getText().toString()));
            }});
        

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Optrions");
        JMenuItem NettoieCalcul = new JMenuItem("Nettoie et Calcul");
        NettoieCalcul.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                claculesField.setText(nettoie(claculesField.getText().toString()));
                claculesField.setText(evaluateExpression(claculesField.getText().toString()));
                calculerButton.setEnabled(true);
            }});
        
        fileMenu.add(NettoieCalcul);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
        
        JButton nettoyageExpretoionBJButton = new JButton("Nettoyage de l'expression affichiée");
        nettoyageExpretoionBJButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                claculesField.setText(nettoie(claculesField.getText().toString()));
                calculerButton.setEnabled(true);
            }});
        clacJPanel.add( nettoyageExpretoionBJButton, BorderLayout.SOUTH );




        clacJPanel.add(calculerButton, BorderLayout.WEST);

        JPanel panel = new JPanel(new GridLayout(4, 4));
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(""+i);
            button.addActionListener(new ActionListener() {
            
                public void actionPerformed(ActionEvent e) {
                    String buttonValue = button.getText();
                    claculesField.setText(claculesField.getText() + buttonValue);
                    calculerButton.setEnabled(true);
                }});
            panel.add(button);
        }
        JButton but0 = new JButton("0");
        but0.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String buttonValue = but0.getText();
                claculesField.setText(claculesField.getText() + buttonValue);
                calculerButton.setEnabled(true);
            }});
        panel.add(but0);
        JButton butFois = new JButton("*");
        butFois.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String buttonValue = butFois.getText();
                claculesField.setText(claculesField.getText() + buttonValue);
                calculerButton.setEnabled(false);
            }});
        panel.add(butFois);
        JButton butPlus = new JButton("+");
        butPlus.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String buttonValue = butPlus.getText();
                claculesField.setText(claculesField.getText() + buttonValue);
                calculerButton.setEnabled(false);
            }});
        panel.add(butPlus);
        this.add(panel);
        for (int i = 1; i <= 3; i++) {
            JButton button = new JButton("");
            panel.add(button);
        }
        JButton butClear = new JButton("C");
        butClear.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                claculesField.setText("");
                calculerButton.setEnabled(false);
            }});
        panel.add(butClear);
    
        this.setVisible(true);
    }

    private static String evaluateExpression(String expression) {
        String[] tokens = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");

        if (tokens.length == 1) {
            return (tokens[0].trim()).toString();
        }
        int result = Integer.parseInt(tokens[0].trim());
        String operator = "";

        for (int i = 1; i < tokens.length; i += 2) {
            int operand = Integer.parseInt(tokens[i + 1].trim());
            operator = tokens[i].trim();

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                default:
                    break;
            }
        }
        return Integer.toString(result);
    }
    
    private static String nettoie(String string) {
        String[] operators = {"+", "-", "*", "/"};
        String cleanedString = string.trim();
    
        for (String operator : operators) {
            if (cleanedString.endsWith(operator)) {
                cleanedString = cleanedString.substring(0, cleanedString.length() - 1);
            }
        }
    
        return cleanedString;
    }
    
}
