package com.luffy.view.config.datasource;


import com.google.common.collect.Lists;
import io.shardingsphere.api.algorithm.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.NoneShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/***
 * 文件名称: MybatisConfiguration.java
 * 文件描述: 多数据源 注入
 * 公 司: 
 * 内容摘要: 通过MybatisAutoConfiguration来实现多数据源注入的
 * 其他说明:
 * 完成日期:2017年04月24日 
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
@PropertySource({"classpath:mybatis.properties"})
@MapperScan(basePackages = {"com.luffy.view.dao"})
@Slf4j
public class MybatisConfiguration {
    private static final String TEST = "test";
    private static final String TEST_MASTER = "test_master";
    private static final String TEST_SLAVE_1 = "test_slave_1";
    private static final String TEST_SLAVE_2 = "test_slave_2";
    /**
     * 主库数据源
     **/
    @Resource(name = "masterDataSource")
    private DataSource masterDataSource;

    @Resource(name = "slaveDataSource1")
    private DataSource slaveDataSource1;

    @Resource(name = "slaveDataSource2")
    private DataSource slaveDataSource2;

    private static final String MYBATIS_CONFIG = "mybatis-config.xml";
    private static final String MAPPER_LOCATIONS = "classpath:mapper/**/*.xml";


    /**
     * 获取源的时候通过threadlocal中不同的标识给出不同的sqlSession
     *
     * @return
     * @throws Exception
     */
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getShardingDataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATIONS));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "masterSlaveDataSource")
    public DataSource getShardingDataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(3);
        dataSourceMap.put(TEST_MASTER, masterDataSource);
        dataSourceMap.put(TEST_SLAVE_1, slaveDataSource1);
        dataSourceMap.put(TEST_SLAVE_2, slaveDataSource2);

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 分表规则
        shardingRuleConfig.getTableRuleConfigs().addAll(buildTableRules());
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        shardingRuleConfig.setDefaultDataSourceName(TEST_MASTER);

        shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());

        try {
            return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap<>(), new Properties());
        } catch (SQLException e) {
            log.error("sharding exception");
            return null;
        }

    }

    private List<TableRuleConfiguration> buildTableRules() {
        List<TableRuleConfiguration> tableRuleConfigurations = new ArrayList<>();
        tableRuleConfigurations.add(getTableRuleConfiguration("fulfill_order", "order_id", 128));
        tableRuleConfigurations.add(getTableRuleConfiguration("fulfill_express", "order_id", 128));
        tableRuleConfigurations.add(getTableRuleConfiguration("fulfill_oversea", "order_id", 128));
        tableRuleConfigurations.add(getTableRuleConfiguration("fulfill_order_sku", "order_id", 128));
        return tableRuleConfigurations;
    }

    private List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration(TEST,
                TEST_MASTER, Arrays.asList(TEST_SLAVE_1, TEST_SLAVE_2), new RoundRobinMasterSlaveLoadBalanceAlgorithm());

        return Lists.newArrayList(masterSlaveRuleConfig);
    }

    private TableRuleConfiguration getTableRuleConfiguration(String table, String shardingColumn, Integer num) {
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        // 不分库
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new NoneShardingStrategyConfiguration());
        // 逻辑表名
        tableRuleConfiguration.setLogicTable(table);
        // 分表规则
        tableRuleConfiguration.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration(
                        shardingColumn,
                        String.format("%s_${%s %s %d}", table, shardingColumn, "%", num)
                )
        );
        // 实际节点
        tableRuleConfiguration.setActualDataNodes(
                String.format(
                        "%s.%s_${0..%d}",
                        TEST, table, num - 1
                ));

        return tableRuleConfiguration;
    }
}
