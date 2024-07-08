package com.birariro.highlight.setting;

import com.birariro.highlight.support.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsComponent {
  private final Colors colors = new Colors();
  private final AppSettings settings = AppSettings.getInstance();
  private final JPanel panel;
  private final JLabel bddColorLabel = new JLabel();
  private final JLabel questionColorLabel = new JLabel();
  private final JLabel exclamationColorLabel = new JLabel();

  private final JButton bddColorButton;
  private final JButton questionColorButton;
  private final JButton exclamationColorButton;

  public SettingsComponent() {
    panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = createGbc();

    bddColorButton = createColorButton(settings.getBddColor(), bddColorLabel);
    questionColorButton = createColorButton(settings.getQuestionColor(), questionColorLabel);
    exclamationColorButton = createColorButton(settings.getExclamationColor(), exclamationColorLabel);

    bddColorLabel.setText(colorToRGBString(settings.getBddColor()));
    questionColorLabel.setText(colorToRGBString(settings.getQuestionColor()));
    exclamationColorLabel.setText(colorToRGBString(settings.getExclamationColor()));

    addColorSetting(gbc, 0, "BDD ", bddColorLabel, bddColorButton);
    addColorSetting(gbc, 1, "? ", questionColorLabel, questionColorButton);
    addColorSetting(gbc, 2, "! ", exclamationColorLabel, exclamationColorButton);
  }

  private GridBagConstraints createGbc() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.CENTER;
    return gbc;
  }

  private JButton createColorButton(Color initialColor, JLabel label) {
    JButton colorButton = new JButton();
    colorButton.setPreferredSize(new Dimension(30, 30));
    colorButton.setBackground(initialColor);
    colorButton.setOpaque(true);
    colorButton.setBorderPainted(false);
    colorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color chosenColor = showColorChooser(colorButton.getBackground());
        if (chosenColor != null) {
          label.setText(colorToRGBString(chosenColor));
          colorButton.setBackground(chosenColor);
          colorButton.setForeground(chosenColor);
        }
      }
    });
    return colorButton;
  }

  private void addColorSetting(GridBagConstraints gbc, int gridY, String labelText, JLabel colorLabel, JButton colorButton) {
    gbc.gridx = 0;
    gbc.gridy = gridY;
    JLabel label = new JLabel(labelText);
    label.setEnabled(false);
    panel.add(label, gbc);

    gbc.gridx = 1;
    panel.add(colorLabel, gbc);

    gbc.gridx = 2;
    panel.add(colorButton, gbc);
  }

  private Color showColorChooser(Color initialColor) {
    return JColorChooser.showDialog(panel, "Choose Color", initialColor);
  }

  private String colorToRGBString(Color color) {
    return colors.colorToString(color);
  }

  public JPanel getPanel() {
    return panel;
  }

  public String getQuestionColorString() {
    return questionColorLabel.getText();
  }

  public String getExclamationColorString() {
    return exclamationColorLabel.getText();
  }

  public String getBddColorString() {
    return bddColorLabel.getText();
  }

  public void setQuestionColor(Color color) {
    questionColorLabel.setText(colorToRGBString(color));
  }

  public void setExclamationColor(Color color) {
    exclamationColorLabel.setText(colorToRGBString(color));
  }

  public void setBddColor(Color color) {
    bddColorLabel.setText(colorToRGBString(color));
  }
}
