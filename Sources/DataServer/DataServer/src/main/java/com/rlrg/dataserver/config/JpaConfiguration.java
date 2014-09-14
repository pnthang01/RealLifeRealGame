package com.rlrg.dataserver.config;

import java.sql.Driver;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class JpaConfiguration implements EnvironmentAware {
    public static final String  CONFIGURATION_PREFIX                         = "spring.datasource";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT              = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL           = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY      = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL             = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";


    private RelaxedPropertyResolver environment;
    
    public JpaConfiguration(){
    	super();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = new RelaxedPropertyResolver(environment, CONFIGURATION_PREFIX + ".");
    }
    	
    @Bean
    @ConditionalOnExpression("${spring.datasource.pooled:false} == false")
    public DataSource simpleDataSource() {
        try {
            String driverClassName = environment == null ? null : environment
                    .getProperty("driverClassName");
            if (driverClassName == null)
                return null;

            @SuppressWarnings("unchecked")
            final Class<Driver> driverClass = (Class<Driver>) Class
                    .forName(driverClassName);
            return new SimpleDriverDataSource() {
                {
                    setDriverClass(driverClass);
                    setUsername(environment.getProperty("username"));
                    setUrl(environment.getProperty("url"));
                    setPassword(environment.getProperty("password"));
                }
            };
        } catch (ClassNotFoundException cnfe) {
            throw new IllegalStateException(cnfe);
        }
    }

    @Bean
    @ConditionalOnExpression("${spring.datasource.pooled:false} == true")
    public DataSource poolDataSource() {
        // try {
        final String driverClassName = environment == null ? null : environment
                .getProperty("driverClassName");
        if (driverClassName == null)
            return null;

        // @SuppressWarnings("unchecked")
        // final Class<Driver> driverClass = (Class<Driver>) Class
        // .forName(driverClassName);

        return new BoneCPDataSource() {
            {
                setDriverClass(driverClassName);
                setUsername(environment.getProperty("username", "root"));
                setJdbcUrl(environment.getProperty("url"));
                setPassword(environment.getProperty("password", ""));
                setIdleConnectionTestPeriodInMinutes(environment.getProperty(
                        "pool.idleConnectionTestPeriodInMinutes", Long.class,
                        60L));
                setIdleMaxAgeInMinutes(environment.getProperty(
                        "pool.idleMaxAgeInMinutes", Long.class, 240L));
                /*
                 * 
                 * <property name="maxConnectionsPerPartition" value="30"/>
                 * <property name="minConnectionsPerPartition" value="10"/>
                 * <property name="partitionCount" value="3"/> <property
                 * name="acquireIncrement" value="5"/> <property
                 * name="statementsCacheSize" value="100"/> <property
                 * name="releaseHelperThreads" value="3"/>
                 */
                setMaxConnectionsPerPartition(30);
                setMinConnectionsPerPartition(10);
                setPartitionCount(3);
                setAcquireIncrement(5);
                setStatementsCacheSize(100);
            }
        };
        // } catch (ClassNotFoundException cnfe) {
        // throw new IllegalStateException(cnfe);
        // }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

        Properties jpaProterties = new Properties() {
            {
                put(PROPERTY_NAME_HIBERNATE_DIALECT,
                        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
                put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
                        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
                put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
                        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
                put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
                put("hibernate.connection.charSet", "UTF-8");
                put("hibernate.connection.characterEncoding", "UTF-8");
                put("hibernate.connection.useUnicode", true);
            }
        };

        lef.setJpaProperties(jpaProterties);
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);

        return hibernateJpaVendorAdapter;
    }

    // @Bean
    // public PlatformTransactionManager transactionManager() {
    // return new JpaTransactionManager();
    // }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public TransactionTemplate txTemplate(PlatformTransactionManager txManager) {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);
        return txTemplate;
    }
}
