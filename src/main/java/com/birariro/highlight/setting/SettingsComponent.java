package com.birariro.highlight.setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsComponent {
  private final AppSettings settings = AppSettings.getInstance();
  private final JPanel panel;
  private final JTextField bddColorField = new JTextField(10);
  private final JTextField questionColorField = new JTextField(10);
  private final JTextField exclamationColorField = new JTextField(10);

  private final JButton bddColorButton;
  private final JButton questionColorButton;
  private final JButton exclamationColorButton;

  public SettingsComponent() {
    panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = createGbc();

    bddColorButton = createColorButton(settings.getBddColor(), bddColorField);
    questionColorButton = createColorButton(settings.getQuestionColor(), questionColorField);
    exclamationColorButton = createColorButton(settings.getExclamationColor(), exclamationColorField);

    bddColorField.setText(colorToRGBString(settings.getBddColor()));
    questionColorField.setText(colorToRGBString(settings.getQuestionColor()));
    exclamationColorField.setText(colorToRGBString(settings.getExclamationColor()));

    addColorSetting(gbc, 0, "BDD ", bddColorField, bddColorButton);
    addColorSetting(gbc, 1, "? ", questionColorField, questionColorButton);
    addColorSetting(gbc, 2, "! ", exclamationColorField, exclamationColorButton);
  }

  private GridBagConstraints createGbc() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.CENTER;
    return gbc;
  }

  private JButton createColorButton(Color initialColor, JTextField colorField) {
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
          colorField.setText(colorToRGBString(chosenColor));
          colorButton.setBackground(chosenColor);
        }
      }
    });
    return colorButton;
  }

  private void addColorSetting(GridBagConstraints gbc, int gridY, String labelText, JTextField colorField, JButton colorButton) {
    gbc.gridx = 0;
    gbc.gridy = gridY;
    JLabel label = new JLabel(labelText);
    label.setEnabled(false);
    panel.add(label, gbc);

    gbc.gridx = 1;
    panel.add(colorField, gbc);

    gbc.gridx = 2;
    panel.add(colorButton, gbc);
  }

  private Color showColorChooser(Color initialColor) {
    return JColorChooser.showDialog(panel, "Choose Color", initialColor);
  }

  private String colorToRGBString(Color color) {
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }

  private Color stringToColor(String colorString) {
    String regex = "#([0-9a-fA-F]{2})([0-9a-fA-F]{2})([0-9a-fA-F]{2})";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(colorString);

    if (matcher.matches()) {
      int red = Integer.parseInt(matcher.group(1), 16);
      int green = Integer.parseInt(matcher.group(2), 16);
      int blue = Integer.parseInt(matcher.group(3), 16);
      return new Color(red, green, blue);
    } else {
      throw new IllegalArgumentException("Invalid color format");
    }
  }

  public JPanel getPanel() {
    return panel;
  }

  public Color getQuestionColor() {
    return stringToColor(questionColorField.getText());
  }

  public Color getExclamationColor() {
    return stringToColor(exclamationColorField.getText());
  }

  public Color getBddColor() {
    return stringToColor(bddColorField.getText());
  }

  public void setQuestionColor(Color color) {
    questionColorField.setText(colorToRGBString(color));
  }

  public void setExclamationColor(Color color) {
    exclamationColorField.setText(colorToRGBString(color));
  }

  public void setBddColor(Color color) {
    bddColorField.setText(colorToRGBString(color));
  }
}
