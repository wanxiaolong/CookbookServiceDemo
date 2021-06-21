package com.demo.test.config;

import com.demo.test.mapper.IngredientMapper;
import com.demo.test.mapper.RecipeIngredientMapper;
import com.demo.test.mapper.RecipeMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class DbDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbDataSource.class);

    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    private static DataSource dataSource = null;
    private static SqlSessionFactory sqlSessionFactory = null;
    private static Configuration configuration = null;
    private static SqlSession sqlSession = null;

    public DbDataSource(DbConfig config) {
        driver = config.getDriver();
        url = config.getUrl();
        username = config.getUsername();
        password = config.getPassword();
        initDataSource();
    }

    public static void initDataSource() {
        LOGGER.info("init datasource using driver: " + driver);
        try {
            if (dataSource == null) {
                dataSource = new PooledDataSource(driver, url, username, password);
            }
        } catch (Exception e) {
            LOGGER.error("init data source error.", e);
            return;
        }
        initSqlSession();
    }

    private static SqlSessionFactory initSqlSession() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);

        // register mappers into configuration
        configuration = new Configuration(environment);
        configuration.addMapper(RecipeMapper.class);
        configuration.addMapper(IngredientMapper.class);
        configuration.addMapper(RecipeIngredientMapper.class);

        // create sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        sqlSession = sqlSessionFactory.openSession();
        return sqlSessionFactory;
    }

    public static <T> T getMapper(Class type) {
        if (configuration == null) {
            initSqlSession();
        }
        return (T) configuration.getMapper(type, sqlSession);
    }
}
