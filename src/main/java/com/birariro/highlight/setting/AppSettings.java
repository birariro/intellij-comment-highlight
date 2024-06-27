package com.birariro.highlight.setting;

import com.birariro.highlight.support.Colors;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

@State(
    name = "com.birariro.highlight.setting.AppSettings",
    storages = @Storage("SdkSettingsPlugin.xml")
)
public final class AppSettings
    implements PersistentStateComponent<AppSettings.State> {

  private static Colors colors = new Colors();

  static class State {
    private String questionColor;
    private String exclamationColor;
    private String bddColor;

    State() {
      questionColor = "#232df1";
      exclamationColor = "#f52564";
      bddColor = "#33cc00";
    }
  }

  private State myState = new State();

  public Color getQuestionColor() {
    return colors.stringToColor(getState().questionColor);
  }
  public Color getExclamationColor() {
    return colors.stringToColor(getState().exclamationColor);
  }
  public Color getBddColor() {
    return colors.stringToColor(getState().bddColor);
  }

  public void setQuestionColor(String color) {
    getState().questionColor = color;
  }
  public void setExclamationColor(String color) {
    getState().exclamationColor = color;
  }
  public void setBddColor(String color) {
    getState().bddColor = color;
  }

  public static AppSettings getInstance() {
    return ApplicationManager.getApplication()
        .getService(AppSettings.class);
  }

  @Override
  public State getState() {
    return myState;
  }

  @Override
  public void loadState(@NotNull State state) {
    myState = state;
  }

}