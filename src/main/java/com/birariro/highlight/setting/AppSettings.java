package com.birariro.highlight.setting;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
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
    @NonNls
    public Color questionColor =  new Color(35, 45, 241);
    public Color exclamationColor =  new Color(245, 37, 100);
    public Color bddColor =  new Color(192, 252, 200,99);
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