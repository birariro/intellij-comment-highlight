package com.birariro.highlight.setting;

import com.birariro.highlight.BddHighlighter;
import com.birariro.highlight.CommentHighlighter;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import java.util.Objects;

/**
 * Provides controller functionality for application settings.
 */
final class SettingsConfigurable implements Configurable {

  private SettingsComponent mySettingsComponent;

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
    mySettingsComponent = new SettingsComponent();
    return mySettingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    AppSettings.State state =
        Objects.requireNonNull(AppSettings.getInstance().getState());
    return mySettingsComponent.getQuestionColor() != state.questionColor ||
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

    BddHighlighter.update();
    CommentHighlighter.update();
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