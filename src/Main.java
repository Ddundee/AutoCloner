import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame){
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JButton button = new JButton("Click Me!");
        final JLabel label = new JLabel();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String)JOptionPane.showInputDialog(
                        frame,
                        "Select one of the color",
                        "Swing Tester",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "https://github.com/Ddundee/Ddundee.git"
                );

//                if(result != null && result.length() > 0){
//                    label.setText("You selected:" + result);
//                }else {
//                    label.setText("None selected");
//                }
                if(result.split("/").length == 1) result = "https://github.com/Ddundee/" + result + ".git";
//                label.setText("Repo: " + result);
                try {
                    Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\dhanu\\OneDrive\\Desktop\\Coding && git clone " + result + " && taskkill /f /im cmd.exe\"");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel.add(button);
        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}