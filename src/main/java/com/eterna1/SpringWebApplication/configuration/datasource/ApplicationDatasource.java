package com.eterna1.SpringWebApplication.configuration.datasource;

import com.eterna1.SpringWebApplication.domain.entity.SampleData;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.eterna1.SpringWebApplication.repository.application",
    transactionManagerRef = "applicationTransactionManager",
    entityManagerFactoryRef = "applicationEntityManagerFactory"
)
public class ApplicationDatasource {

    @Bean
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties applicationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource1.configuration")
    public DataSource applicationDataSource() {
        return applicationDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(BasicDataSource.class)
            .build();
    }

    @Bean(name = "applicationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean applicationEntityManagerFactory(
        EntityManagerFactoryBuilder builder) {
        return builder
            .dataSource(applicationDataSource())
            .packages(SampleData.class)
            .build();
    }

    @Bean
    public PlatformTransactionManager applicationTransactionManager(
        final @Qualifier("applicationEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}
