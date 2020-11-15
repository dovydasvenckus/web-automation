package dev.dovydasvenckus.webautomation;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration> {

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public String getName() {
    return "web-automation-service";
  }

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(
        new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
            new EnvironmentVariableSubstitutor(false)
        )
    );
  }

  @Override
  public void run(AppConfiguration appConfiguration, Environment environment) {
  }

}
