package com.jetbrains.php.tools.quality.phpstan;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.jetbrains.php.tools.quality.QualityToolConfigurationManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PhpStanConfigurationManager extends QualityToolConfigurationManager<PhpStanConfiguration> {

  public static final int DEFAULT_MAX_MESSAGES_PER_FILE = 50;

  public PhpStanConfigurationManager(@Nullable Project project) {
    if (project != null) {
      myProjectManager = ServiceManager.getService(project, PhpStanConfigurationManager.PhpStanProjectConfigurationManager.class);
    }
    myApplicationManager = ServiceManager.getService(PhpStanConfigurationManager.PhpStanAppConfigurationManager.class);
  }

  @NotNull
  @Override
  protected List<PhpStanConfiguration> getDefaultProjectSettings() {
    return ServiceManager.getService(ProjectManager.getInstance().getDefaultProject(),
                                     PhpStanConfigurationManager.PhpStanProjectConfigurationManager.class).getSettings();
  }

  public static PhpStanConfigurationManager getInstance(@NotNull Project project) {
    return ServiceManager.getService(project, PhpStanConfigurationManager.class);
  }

  @State(name = "PhpStan", storages = @Storage("php.xml"))
  static class PhpStanProjectConfigurationManager extends PhpStanConfigurationBaseManager {
  }

  @State(name = "PhpStan", storages = @Storage("php.xml"))
  static class PhpStanAppConfigurationManager extends PhpStanConfigurationBaseManager {
  }
}