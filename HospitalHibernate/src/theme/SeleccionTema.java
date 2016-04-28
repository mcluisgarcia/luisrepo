package theme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name="tema")
@ApplicationScoped
public class SeleccionTema implements Serializable{

	private List<String> themes;
	private String selectedTheme = "bootstrap";
	
	
	public String getSelectedTheme() {
		return selectedTheme;
	}

	public void setSelectedTheme(String selectedTheme) {
		this.selectedTheme = selectedTheme;
	}

	public void setThemes(List<String> themes) {
		this.themes = themes;
	}

	public SeleccionTema()
	 {
		 themes = new ArrayList<String>();
		 themes.add("afterdark");
		 themes.add("afternoon");
		 themes.add("afterwork");
		 themes.add("aristo");
		 themes.add("black-tie");
		 themes.add("blitzer");
		 themes.add("bluesky");
		 themes.add("bootstrap");
		 themes.add("ui-darkness");
		 themes.add("ui-lightness");
		 themes.add("vader");
	 }
	 
	 public List<String> getThemes()
	 {
		 return themes;
	 }

}
