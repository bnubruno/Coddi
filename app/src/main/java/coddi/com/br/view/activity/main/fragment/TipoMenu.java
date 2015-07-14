package coddi.com.br.view.activity.main.fragment;

/**
 * Created by Bruno on 15/04/2015.
 */
public enum TipoMenu {
    CATEGORIA(1, "Categoria", 0),
    CONTA(2, "Conta", 1),
    PAGAMENTO(4, "Pagamento", 2),
    SAQUE(5, "Saque", 2),
    RECEBIMENTO(6, "Recebimento", 3),
    TRANSFERENCIA(7, "Transferência", 4),
    RESULTADO(9, "Resultado", 5);

    private TipoMenu(int id, String descricao, int posIcone) {
        this.id = id;
        this.descricao = descricao;
        this.posicaoIcone = posIcone;
    }

    private int id;
    private String descricao;
    private int posicaoIcone;

    public int getPosicaoIcone() {
        return posicaoIcone;
    }

    public void setPosicaoIcone(int posicaoIcone) {
        this.posicaoIcone = posicaoIcone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
