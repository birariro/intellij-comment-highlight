package com.birariro.highlight.setting;

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

  static class State {
    public Color questionColor;
    public Color exclamationColor;
    public Color bddColor;

    State() {
      questionColor = new Color(35, 45, 241);
      exclamationColor = new Color(245, 37, 100);
      bddColor = new Color(192, 252, 200);
    }
  }

  private State myState = new State();

  public Color getQuestionColor() {
    return getState().questionColor;
  }

  public Color getExclamationColor() {
    return getState().exclamationColor;
  }

  public Color getBddColor() {
    return getState().bddColor;
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