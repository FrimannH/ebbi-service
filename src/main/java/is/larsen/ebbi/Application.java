package is.larsen.ebbi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	public static void main(final String[] args) {

		SpringApplication.run(Application.class, args);
	}

    @Autowired
    JdbcTemplate jdbcTemplate;
}
