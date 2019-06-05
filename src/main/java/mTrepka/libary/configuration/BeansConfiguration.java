package mTrepka.libary.configuration;

import mTrepka.libary.utility.NavigationBar;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    Logger logger(){
        BasicConfigurator.configure();
        return Logger.getRootLogger();
    }

    @Bean
    NavigationBar defaultNavigationBar() {
        NavigationBar navigation = new NavigationBar();
	    navigation.addMenu("Index", "../");
	    navigation.addMenu("Books ", "book/");
	    navigation.addMenu("Contact", "contact");
        return navigation;
    }

    @Bean
    NavigationBar userNavigatonBar() {
        NavigationBar navigation = defaultNavigationBar().clone();
	    navigation.addMenu("User panel", "");
	    navigation.addMenu("My borrows", "user/books");
	    navigation.addMenu("Borrows history", "user/history");
	    navigation.addMenu("Settings", "user/settings");
	    navigation.addMenu("Logout", "logout");
        return navigation;
    }

    @Bean
    NavigationBar adminNavigationBar() {
        NavigationBar navigation = defaultNavigationBar().clone();
	    navigation.addMenu("All users", "admin/users/");
	    navigation.addMenu("Add user", "admin/users/add");
	    navigation.addMenu("Books", "");
	    navigation.addMenu("All books", "admin/book/");
	    navigation.addMenu("Add book", "admin/book/add");
	    navigation.addMenu("Show lend book", "admin/book/lend");
	    navigation.addMenu("logout", "logout");
        return navigation;
    }
}
