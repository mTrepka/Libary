package mTrepka.libary.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NavigationBar implements Cloneable {
    private String menuClas;
    private String menuId;
    private String menuType;
    private String hrefClass;
    private String hrefId;
    private String clas;
    private String id;
    private String type;
    private String navigationType;
    private ArrayList<String> name;
    private ArrayList<String> url;

    public String getNavigation(int i) {
        StringBuilder stringBuilder = new StringBuilder('<' + navigationType + '>');
        StringBuilder urlBuilder = new StringBuilder("");
        String urlBuild;
        if (i != 0) {
            while (i != 0) {
                urlBuilder.append("../");
                --i;
            }
        }
        urlBuild = urlBuilder.toString();
        String build = "";
        int j = 0;
        int tableSize = url.size();
        while (j != tableSize) {
            if (url.get(j) == "") {
                stringBuilder.append("\n<" + type + " class='" + clas + "' id='" + id + "'>" + name.get(j) + "</" + type + '>');
            } else {
                stringBuilder.append("\n<a href='" + urlBuild + url.get(j) + "' " + "class='" + hrefClass + "' id='" + hrefId + "'>" + "<" + menuType + " class='" + menuClas + "' id='" + menuId + "'>" + name.get(j) + "</" + menuType + "></a>");
            }
            j++;
        }
        stringBuilder.append("\n</" + navigationType + '>');
        return stringBuilder.toString();
    }

    public void addMenu(String name, String url) {
        this.name.add(name);
        this.url.add(url);
    }

    public void addMenu(List<String> menu, List<String> url) {
        this.name.addAll(menu);
        this.url.addAll(url);
    }

    public NavigationBar(List<String> name, List<String> url) {
        this.name = new ArrayList<>();
        this.name.addAll(name);
        this.url = new ArrayList<>();
        this.url.addAll(url);

        menuClas = "";
        menuId = "";
        menuType = "li";
        clas = "";
        id = "";
        type = "h2";
        hrefClass = "";
        hrefId = "";
        navigationType = "ol";
    }

    public NavigationBar() {
        name = new ArrayList<>();
        url = new ArrayList<>();
        menuClas = "";
        menuId = "";
        menuType = "li";
        clas = "";
        id = "";
        type = "h2";
        hrefClass = "";
        hrefId = "";
        navigationType = "ol";
    }

    public NavigationBar clone() {
        NavigationBar navigationBar = new NavigationBar();
        navigationBar.setClas(this.getClas());
        navigationBar.setHrefClass(this.getHrefClass());
        navigationBar.setHrefId(this.getHrefId());
        navigationBar.setId(this.getId());
        navigationBar.setMenuClas(this.getMenuClas());
        navigationBar.setMenuId(this.getMenuId());
        navigationBar.setMenuType(this.getMenuType());
        navigationBar.addMenu((List) name.clone(), (List) url.clone());
        return navigationBar;
    }

    public String getMenuClas() {
        return menuClas;
    }

    public void setMenuClas(String menuClas) {
        this.menuClas = menuClas;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getHrefClass() {
        return hrefClass;
    }

    public void setHrefClass(String hrefClass) {
        this.hrefClass = hrefClass;
    }

    public String getHrefId() {
        return hrefId;
    }

    public void setHrefId(String hrefId) {
        this.hrefId = hrefId;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
