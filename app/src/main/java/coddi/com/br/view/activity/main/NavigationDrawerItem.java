package coddi.com.br.view.activity.main;


/**
 * @author bruno
 */
public class NavigationDrawerItem {

    private int icone;
    private String titulo;
    private boolean isSection;

    public NavigationDrawerItem() {
    }

    public NavigationDrawerItem(String title, int icone) {
        this.titulo = title;
        this.isSection = false;
        this.icone = icone;
    }

    public NavigationDrawerItem(String titulo, boolean isSection) {
        this.titulo = titulo;
        this.isSection = isSection;
        this.icone = 0;
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

    public void setIcone(int icone) {
        this.icone = icone;
    }

    public int getIcone() {
        return icone;
    }
}
