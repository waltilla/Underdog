package waltilla.sebastian.underdog.appConfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "postgres")
public class PostgresConfiguration {
    private  String host;
    private  int port;
    private  String database;
    private  String username;
    private  String password;
    private  String table;
    private String driver;
}
