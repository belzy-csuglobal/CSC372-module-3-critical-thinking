
package components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class App extends JFrame implements MenuListener {
  int frameWidth = 280;
  int frameHeight = 340;
  int frameX = (1920 / 2) - (this.frameWidth / 2);
  int frameY = 1080 - (this.frameHeight / 2);
  MenuBar menuBar = new MenuBar(this);
  JPanel contentPanel = new JPanel();
  JPanel innerPanel = new JPanel();
  JTextField datetimeField = new JTextField(10);
  int greenValue = 255;
  String colorHex = "#FFFFFF";

  public App() {
    super("UI App");

    this.innerPanel.add(this.datetimeField);
    this.contentPanel.setLayout(new BoxLayout(this.contentPanel, BoxLayout.Y_AXIS));
    this.contentPanel.add(Box.createVerticalGlue());
    this.contentPanel.add(Box.createVerticalStrut(10));
    this.contentPanel.add(this.innerPanel);
    this.contentPanel.add(Box.createVerticalGlue());

    this.add(this.contentPanel);
    this.setJMenuBar(this.menuBar);
    this.setResizable(false);
    this.setSize(this.frameWidth, this.frameHeight);
    this.setLocation(this.frameX, this.frameY);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.generateRandomHue();
    this.menuBar.setHueText(this.colorHex);
  }

  public void onClick(MenuItem itemClicked) {
    switch (itemClicked) {
      case DISPLAY_DATETIME:
        this.handleDisplayDatetime();
        break;
      case EXPORT_DATETIME:
        this.handleExportDatetime();
        break;
      case CHANGE_HUE:
        this.handleChangeHue();
        break;
      default:
      case EXIT:
        this.handleExit();
        break;
    }
  }

  private void handleDisplayDatetime() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    this.datetimeField.setText(dateFormat.format(new Date()));
  }

  private void handleExportDatetime() {
    try {
      File file = new File("log.txt");
      BufferedWriter writer = 
        new BufferedWriter(new FileWriter(file));
      writer.write(this.datetimeField.getText());      
      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private void handleChangeHue() {
    Color green = new Color(0, this.greenValue, 0, 255);

    this.contentPanel.setBackground(green);
    this.innerPanel.setBackground(green);

    this.generateRandomHue();
    this.menuBar.setHueText(this.colorHex);
  }

  private void generateRandomHue() {
    Random random = new Random();
    this.greenValue = random.nextInt(205) + 50;

    this.colorHex = String.format("#%s%s%s", 
      Integer.toHexString(255), 
      Integer.toHexString(this.greenValue), 
      Integer.toHexString(255))
      .toUpperCase();
  }

  private void handleExit() {
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}