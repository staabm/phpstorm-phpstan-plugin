package com.jetbrains.php.tools.quality.phpstan;

import com.jetbrains.php.lang.inspections.PhpInspection;
import com.jetbrains.php.tools.quality.QualityToolAnnotator;
import com.jetbrains.php.tools.quality.QualityToolValidationInspection;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PhpStanValidationInspection extends QualityToolValidationInspection {

  public final static String DISPLAY_NAME = "PHPStan Validation";

  @NotNull
  @Override
  public String[] getGroupPath() {
    return PhpInspection.GROUP_PATH_GENERAL;
  }

  @NotNull
  @Override
  public String getShortName() {
    return getClass().getSimpleName();
  }

  @Nls
  @NotNull
  @Override
  public String getDisplayName() {
    return DISPLAY_NAME;
  }

  @Override
  public JComponent createOptionsPanel() {
    final PhpStanOptionsPanel optionsPanel = new PhpStanOptionsPanel(this);
    optionsPanel.init();
    return optionsPanel.getOptionsPanel();
  }

  @NotNull
  @Override
  protected QualityToolAnnotator getAnnotator() {
    return PhpStanAnnotatorProxy.INSTANCE;
  }

  @Override
  public String getToolName() {
    return "PHPStan";
  }

  public List<String> getCommandLineOptions(List<String> filePath) {
    ArrayList<String> options = new ArrayList<>();
    options.add("analyze");
    options.add("--level=max");
    options.add("--memory-limit=2G");
    options.add("--error-format=checkstyle");
    options.addAll(filePath);
    return options;
  }
}
