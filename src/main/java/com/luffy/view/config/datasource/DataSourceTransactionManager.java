package com.luffy.view.config.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {

	@Resource(name="masterSlaveDataSource")
    private DataSource masterSlaveDataSource;

    @Bean(name = "transactionManager")
    @Order(200)
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {  
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(masterSlaveDataSource);
    }  
}
