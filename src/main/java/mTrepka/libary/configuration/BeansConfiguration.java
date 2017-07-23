package mTrepka.libary.configuration;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Mario on 2017-07-23.
 */
@Configuration
public class BeansConfiguration {
    @Bean
    Logger logger(){
        BasicConfigurator.configure();
        return Logger.getRootLogger();
    }
}
