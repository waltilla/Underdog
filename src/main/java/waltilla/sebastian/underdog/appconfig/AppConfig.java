package waltilla.sebastian.underdog.appconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    PostgresConfiguration postgresConfiguration;

    public AppConfig(PostgresConfiguration postgresConfiguration) {
        this.postgresConfiguration = postgresConfiguration;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgresConfiguration.getDriver());
        dataSource.setUrl(postgresConfiguration.getHost() + ":" + postgresConfiguration.getPort());
        dataSource.setUsername(postgresConfiguration.getUsername());
        dataSource.setPassword(postgresConfiguration.getPassword());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
