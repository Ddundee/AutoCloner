import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {

        String jsonStr = new String(Files.readAllBytes(Paths.get("src/config/config.json")));
        JSONObject json = new JSONObject(jsonStr);

        JFrame frame = new JFrame("Auto Cloner");

        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);
        if(!json.getString("defaultOwner").equals("") || !json.getString("customizedPath").equals("")) {
            JButton button = new JButton("Add Repo!");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String result = (String)JOptionPane.showInputDialog(
                            frame,
                            "Name of the Git Repo",
                            "Name of the Git Repo",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "https://github.com/Ddundee/Ddundee.git"
                    );
                    if(result.split("/").length == 1) result = "https://github.com/Ddundee/" + result + ".git";
                    try {
                        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\dhanu\\OneDrive\\Desktop\\Coding && git clone " + result + " && taskkill /f /im cmd.exe\"");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            panel.add(button);
            frame.getContentPane().add(panel, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setIconImage(new ImageIcon("public/icon.png").getImage());
            frame.setSize(200, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setVisible(true);
        }
        else {
            JButton button1 = new JButton("Add Default Owner");
            JButton button2 = new JButton("Add Customized Path");
            JButton button3 = new JButton("Done!");

            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    json.put("defaultOwner",
                            (String)JOptionPane.showInputDialog(
                            frame,
                            "Github Default Owner",
                            "Github Username",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Ddundee"
                    ));
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    json.put("customizedPath",
                            (String)JOptionPane.showInputDialog(
                            frame,
                            "Customized Path",
                            "Customized Path",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "C:\\Users\\dhanu\\OneDrive\\Desktop\\Coding"
                    ));
                }
            });

            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(json.getString("defaultOwner"));
                    System.out.println(json.getString("customizedPath"));
                    System.out.println(json.toString());
                    try {
                        FileWriter file = new FileWriter("src/config/config.json");
                        file.write(json.toString());
                        file.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                }
            });
            panel.add(button1);
            panel.add(button2);
            panel.add(button3);
            frame.getContentPane().add(panel, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setIconImage(new ImageIcon("public/icon.png").getImage());
            frame.setSize(200, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setVisible(true);
        }
    }
}