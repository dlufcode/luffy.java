package com.luffy.view.config.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    /**
     * 数据源类型
     **/
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 设置主库数据源
     *
     * @return 返回主库数据源
     */
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 设置从库数据源 从库1
     *
     * @return 返回从库1数据源
     */
    @Bean(name = "slaveDataSource1")
    @ConfigurationProperties(prefix = "spring.slave")
    public DataSource slaveDataSourceOne() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }


    @Bean(name = "slaveDataSource2")
    @ConfigurationProperties(prefix = "spring.read2")
    public DataSource slaveDataSourceTwo() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
}
