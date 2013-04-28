package com.codahale.dropwizard.migrations;

import com.codahale.dropwizard.Configuration;
import com.codahale.dropwizard.db.ConfigurationStrategy;
import liquibase.Liquibase;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class DbGenerateDocsCommand<T extends Configuration> extends AbstractLiquibaseCommand<T> {
    public DbGenerateDocsCommand(ConfigurationStrategy<T> strategy, Class<T> configurationClass) {
        super("generate-docs", "Generate documentation about the database state.", strategy, configurationClass);
    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);

        subparser.addArgument("output").nargs(1).help("output directory");
    }

    @Override
    public void run(Namespace namespace, Liquibase liquibase) throws Exception {
        liquibase.generateDocumentation(namespace.<String>getList("output").get(0));
    }
}