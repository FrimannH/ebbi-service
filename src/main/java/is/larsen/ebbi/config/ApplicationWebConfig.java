package is.larsen.ebbi.config;

import is.larsen.ebbi.Model.Customer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.http.HttpStatus;

import is.larsen.ebbi.Dao.CustomerDao;
import is.larsen.ebbi.Dao.Impl.CustomerDaoImpl;

import is.larsen.ebbi.Service.Impl.EbbiServiceImpl;

import java.util.List;

@Configuration
public class ApplicationWebConfig extends WebMvcConfigurerAdapter{

    private final String URL = "url";

	@Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"));
            }
        };
    }

    @Bean
    public EbbiServiceImpl ebbiService() {
        return new EbbiServiceImpl();
    }

    @Bean
    public CustomerDao customerDao() { return new CustomerDaoImpl(); }



}
