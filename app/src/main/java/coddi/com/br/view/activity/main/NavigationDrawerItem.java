package coddi.com.br.view.activity.main;


/**
 * @author bruno
 */
public class NavigationDrawerItem {

    private String titulo;
    private boolean isSection;

    public NavigationDrawerItem() {
    }

    public NavigationDrawerItem(String title) {
        this.titulo = title;
        this.isSection = false;
    }

    public NavigationDrawerItem(String titulo, boolean isSection) {
        this.titulo = titulo;
        this.isSection = isSection;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isSection() {
        return isSection;
    }

    public void setSection(boolean isSection) {
        this.isSection = isSection;
    }
}
