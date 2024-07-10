package com.birariro.highlight.setting;

import com.birariro.highlight.KeywordColor;
import com.birariro.highlight.support.Colors;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@State(
    name = "com.birariro.highlight.setting.AppSettings",
    storages = @Storage("SdkSettingsPlugin.xml")
)
public final class AppSettings
    implements PersistentStateComponent<AppSettings.State> {

  private static Colors colors = new Colors();


  static class State {

    List keywordColors;

    public State() {
      keywordColors = new ArrayList<KeywordColor>();
      keywordColors.add(new KeywordColor("note","#ffff66"));
      keywordColors.add(new KeywordColor("info","#232df1"));
      keywordColors.add(new KeywordColor("given","#33c936"));
      keywordColors.add(new KeywordColor("when","#33dd00"));
      keywordColors.add(new KeywordColor("then","#33f57d"));
    }
  }

  private State myState = new State();

  public List<KeywordColor> getKeywordColors(){
    return this.getState().keywordColors;
  }
  public void setKeywordColors(List<KeywordColor> keywordColor){
    this.getState().keywordColors = keywordColor;
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