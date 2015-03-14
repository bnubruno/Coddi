package coddi.com.br.controller;

import coddi.com.br.App.CoddiApplication;

/**
 * Created by Bruno on 02/02/2015.
 */
public class AbstractController {

    private CoddiApplication context;
    private String path;

    public AbstractController(CoddiApplication context, String path) {
        this.context = context;
        this.path = path;
    }

    protected String getHost(String... parameters) {
        StringBuilder host = new StringBuilder(context.getHost());
        if (parameters != null && parameters.length > 0) {
            for (String parameter : parameters) {
                host.append(parameter);
                host.append('/');
            }
        }
        return host.toString();
    }

    public String getPath() {
        return path;
    }

    public CoddiApplication getContext() {
        return context;
    }
}
