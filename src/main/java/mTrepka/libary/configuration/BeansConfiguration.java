package mTrepka.libary.configuration;

import mTrepka.libary.utility.NavigationBar;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        navigation.addMenu("Strona Glowna", "../");
        navigation.addMenu("Ksiazki", "book/");
        navigation.addMenu("Kontakt", "contact");
        return navigation;
    }

    @Bean
    NavigationBar userNavigatonBar() {
        NavigationBar navigation = defaultNavigationBar().clone();
        navigation.addMenu("Panel Użytkownika", "");
        navigation.addMenu("Moje Wypożyczenia", "user/books");
        navigation.addMenu("Historia Wypożyczeń", "user/history");
        navigation.addMenu("Ustawienia", "user/settings");
        navigation.addMenu("Wyloguj", "logout");
        return navigation;
    }

    @Bean
    NavigationBar adminNavigationBar() {
        NavigationBar navigation = defaultNavigationBar().clone();
        navigation.addMenu("Wyświetl Wszystkich", "admin/users/");
        navigation.addMenu("Dodaj użytkownika", "admin/users/add");
        navigation.addMenu("Edytuj użytkowników", "admin/users/edit/");
        navigation.addMenu("Książki", "");
        navigation.addMenu("Wyswietlij Wszystkie", "admin/book/");
        navigation.addMenu("Dodaj książke", "admin/book/add");
        navigation.addMenu("Edytuj książke", "admin/book/edit/");
        navigation.addMenu("Pokaż wypożyczone", "admin/book/lend");
        navigation.addMenu("Wyloguj", "logout");
        return navigation;
    }
}
