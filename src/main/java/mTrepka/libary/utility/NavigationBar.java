package mTrepka.libary.utility;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

	public String getNavigation(int i) {
		StringBuilder stringBuilder = new StringBuilder('<' + navigationType + '>');
		StringBuilder urlBuilder = new StringBuilder();
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
			if (url.get(j).equals("")) {
				stringBuilder.append("\n<").append(type).append(" class='").append(clas).append("' id='").append(id).append("'>").append(name.get(j)).append("</").append(type).append('>');
			} else {
				stringBuilder.append("\n<a href='").append(urlBuild).append(url.get(j)).append("' ").append("class='").append(hrefClass).append("' id='").append(hrefId).append("'>").append("<").append(menuType).append(" class='").append(menuClas).append("' id='").append(menuId).append("'>").append(name.get(j)).append("</").append(menuType).append("></a>");
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

	private void addMenu(List<String> menu, List<String> url) {
		this.name.addAll(menu);
		this.url.addAll(url);
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
}
