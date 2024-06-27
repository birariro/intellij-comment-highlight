package com.birariro.highlight.setting;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import java.util.Objects;

/**
 * Provides controller functionality for application settings.
 */
final class AppSettingsConfigurable implements Configurable {

  private CommentHighlighterSettingsComponent mySettingsComponent;

  // A default constructor with no arguments is required because
  // this implementation is registered as an applicationConfigurable

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "comment highlight setting";
  }


  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new CommentHighlighterSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    AppSettings.State state =
        Objects.requireNonNull(AppSettings.getInstance().getState());
    return !mySettingsComponent.getQuestionColor().equals(state.questionColor) ||
        mySettingsComponent.getExclamationColor() != state.exclamationColor ||
            mySettingsComponent.getBddColor() != state.bddColor;
  }

  @Override
  public void apply() {
    AppSettings.State state =
        Objects.requireNonNull(AppSettings.getInstance().getState());
    state.questionColor = mySettingsComponent.getQuestionColor();
    state.exclamationColor = mySettingsComponent.getExclamationColor();
    state.bddColor = mySettingsComponent.getBddColor();
  }

  @Override
  public void reset() {
    AppSettings.State state =
        Objects.requireNonNull(AppSettings.getInstance().getState());
    mySettingsComponent.setQuestionColor(state.questionColor);
    mySettingsComponent.setExclamationColor(state.exclamationColor);
    mySettingsComponent.setBddColor(state.bddColor);
  }

  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

}