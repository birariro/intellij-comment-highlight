package com.birariro.highlight.setting;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import java.util.Objects;

/**
 * Provides controller functionality for application settings.
 */
final class SettingsConfigurable implements Configurable {

  private SettingsComponent component;

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
    component = new SettingsComponent();
    return component.getPanel();
  }

  @Override
  public boolean isModified() {
    return true;
  }

  @Override
  public void apply() {
    AppSettings appSettings =
            Objects.requireNonNull(AppSettings.getInstance());
    appSettings.setKeywordColors(component.getContents());
  }

  @Override
  public void reset() {
    AppSettings appSettings =
        Objects.requireNonNull(AppSettings.getInstance());
    component.setContents(appSettings.getKeywordColors());
  }

  @Override
  public void disposeUIResources() {
    component = null;
  }
}