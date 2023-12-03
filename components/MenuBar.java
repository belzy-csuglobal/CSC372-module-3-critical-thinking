
package components;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar implements ActionListener {
  JMenu menu = new JMenu("View");
  JMenuItem displayDatetimeItem = new JMenuItem("Display Datetime");
  JMenuItem exportDatetimeItem = new JMenuItem("Export Datetime");
  JMenuItem randomGreenItem = new JMenuItem("Change Hue: ");
  JMenuItem exitItem = new JMenuItem("Exit");
  MenuListener menuListener;

  public MenuBar(MenuListener menuListener) {
    this.menuListener = menuListener;

    this.displayDatetimeItem.addActionListener(this);
    this.exportDatetimeItem.addActionListener(this);
    this.randomGreenItem.addActionListener(this);
    this.exitItem.addActionListener(this);

    this.menu.add(this.displayDatetimeItem);
    this.menu.add(this.exportDatetimeItem);
    this.menu.add(this.randomGreenItem);
    this.menu.addSeparator();
    this.menu.add(this.exitItem);
    this.add(this.menu);
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    MenuItem itemClicked = null;

    if (source == this.displayDatetimeItem) {
      itemClicked = MenuItem.DISPLAY_DATETIME;
    }

    else if (source == this.exportDatetimeItem) {
      itemClicked = MenuItem.EXPORT_DATETIME;
    }

    else if (source == this.randomGreenItem) {
      itemClicked = MenuItem.CHANGE_HUE;
    }

    else if (source == this.exitItem) {
      itemClicked = MenuItem.EXIT;
    }

    this.menuListener.onClick(itemClicked);
  }

  public void setHueText(String hueText) {
    this.randomGreenItem.setText(String.format("Change Hue: %s", hueText));
  }
}