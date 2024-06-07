package org.app.versioncontrol.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "org.app.versioncontrol.repositories")
public class JpaConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("org.app.versioncontrol.entities");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        entityManagerFactoryBean.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory managerFactory) {
        return new JpaTransactionManager(managerFactory);
    }
}
