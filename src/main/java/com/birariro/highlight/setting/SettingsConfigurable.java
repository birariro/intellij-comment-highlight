package com.birariro.highlight.setting;

import com.birariro.highlight.support.Colors;
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
  private Colors colors = new Colors();

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
    AppSettings appSettings =
            Objects.requireNonNull(AppSettings.getInstance());

    return colors.stringToColor(mySettingsComponent.getQuestionColorString()) != appSettings.getQuestionColor() ||
            colors.stringToColor(mySettingsComponent.getExclamationColorString()) != appSettings.getExclamationColor() ||
            colors.stringToColor(mySettingsComponent.getBddColorString()) != appSettings.getBddColor();
  }

  @Override
  public void apply() {
    AppSettings appSettings =
            Objects.requireNonNull(AppSettings.getInstance());
    appSettings.setQuestionColor(mySettingsComponent.getQuestionColorString());
    appSettings.setExclamationColor(mySettingsComponent.getExclamationColorString());
    appSettings.setBddColor(mySettingsComponent.getBddColorString());

  }

  @Override
  public void reset() {
    AppSettings appSettings =
        Objects.requireNonNull(AppSettings.getInstance());
    mySettingsComponent.setQuestionColor(appSettings.getQuestionColor());
    mySettingsComponent.setExclamationColor(appSettings.getExclamationColor());
    mySettingsComponent.setBddColor(appSettings.getBddColor());
  }

  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }

}