package com.birariro.highlight.setting;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentHighlighterSettingsComponent {
  private JPanel panel;

  private JTextField bddColorField = new JTextField(10);
  private JTextField questionColorField = new JTextField(10);
  private JTextField exclamationColorField = new JTextField(10);


  private JButton bddColorButton;
  private JTextField blueColorField;
  private JButton questionColorButton;
  private JButton exclamationColorButton;

  public CommentHighlighterSettingsComponent() {
    panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.NORTHWEST;

    // bdd color settings
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("BDD :"), gbc);

    gbc.gridx = 1;
    panel.add(bddColorField, gbc);

    gbc.gridx = 2;
    bddColorButton = new JButton();
    bddColorButton.setPreferredSize(new Dimension(30, 30));
    bddColorButton.setBackground(Color.RED);
    bddColorButton.setOpaque(true);
    bddColorButton.setBorderPainted(false);
    bddColorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color chosenColor = showColorChooser(bddColorButton.getText());
        if (chosenColor != null) {
          bddColorField.setText(colorToRGBString(chosenColor));
          bddColorButton.setBackground(chosenColor);
        }
      }
    });
    panel.add(bddColorButton, gbc);

    // question color settings
    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JLabel("? :"), gbc);

    gbc.gridx = 1;
    panel.add(questionColorField, gbc);

    gbc.gridx = 2;
    questionColorButton = new JButton();
    questionColorButton.setPreferredSize(new Dimension(30, 30));
    questionColorButton.setBackground(Color.BLUE);
    questionColorButton.setOpaque(true);
    questionColorButton.setBorderPainted(false);
    questionColorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color chosenColor = showColorChooser(questionColorButton.getText());
        if (chosenColor != null) {
          questionColorField.setText(colorToRGBString(chosenColor));
          questionColorButton.setBackground(chosenColor);
        }
      }
    });
    panel.add(questionColorButton, gbc);


    // exclamation color settings
    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(new JLabel("! :"), gbc);

    gbc.gridx = 1;
    panel.add(exclamationColorField, gbc);

    gbc.gridx = 2;
    exclamationColorButton = new JButton();
    exclamationColorButton.setPreferredSize(new Dimension(30, 30));
    exclamationColorButton.setBackground(Color.BLUE);
    exclamationColorButton.setOpaque(true);
    exclamationColorButton.setBorderPainted(false);
    exclamationColorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color chosenColor = showColorChooser(exclamationColorButton.getText());
        if (chosenColor != null) {
          exclamationColorField.setText(colorToRGBString(chosenColor));
          exclamationColorButton.setBackground(chosenColor);
        }
      }
    });
    panel.add(exclamationColorButton, gbc);

  }

  private Color showColorChooser(String initialColor) {
    try {
      return Color.decode(initialColor);
    } catch (NumberFormatException e) {
      return JColorChooser.showDialog(panel, "Choose Color", null);
    }
  }

  private String colorToRGBString(Color color) {
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }
  private Color stringToColor(String colorString){
    String regex = "#([0-9a-fA-F]{2})([0-9a-fA-F]{2})([0-9a-fA-F]{2})";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(colorString);

    // 정규식에 맞는지 확인합니다.
    if (matcher.matches()) {
      // 매칭된 그룹에서 색상 값을 추출합니다.
      int red = Integer.parseInt(matcher.group(1), 16);
      int green = Integer.parseInt(matcher.group(2), 16);
      int blue = Integer.parseInt(matcher.group(3), 16);

      // Color 객체를 생성하여 반환합니다.
      return new Color(red, green, blue);
    } else {
      // 정규식에 맞지 않는 경우 예외를 던집니다.
      throw new IllegalArgumentException("Invalid color format");
    }
  }

  public JPanel getPanel() {
    return panel;
  }

  public Color getQuestionColor(){
    return stringToColor(questionColorField.getText());
  }
  public Color getExclamationColor(){
    return stringToColor(exclamationColorField.getText());
  }
  public Color getBddColor(){
    return stringToColor(bddColorField.getText());
  }
  public void setQuestionColor(Color color){
    questionColorField.setText(colorToRGBString(color));

  }
  public void setExclamationColor(Color color){
    exclamationColorField.setText(colorToRGBString(color));
  }
  public void setBddColor(Color color){
    bddColorField.setText(colorToRGBString(color));
  }

}
