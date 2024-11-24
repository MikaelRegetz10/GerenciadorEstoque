package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlertaEstoque implements Serializable, TableFormatRelatorio {
    Integer id;
    Integer produtoId;
    String messagem;
    String data;

    public AlertaEstoque() {
    }

    public AlertaEstoque(String data, Integer id, String messagem, Integer produtoId) {
        this.data = data;
        this.id = id;
        this.messagem = messagem;
        this.produtoId = produtoId;
    }

    public String getData(String dataAlerta) {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }


    @Override
    public String[] getTableFormatRelatorio() {
        List<Object> list = new ArrayList<>();
        list.add(id);
        list.add(produtoId);
        list.add(messagem);
        list.add(data);

        String[] stringArray = list.stream()
                .map(Object::toString)
                .toArray(String[]::new);

        return stringArray;
    }

    @Override
    public String toString() {
        return "AlertaEstoque{" +
                "data='" + data + '\'' +
                ", id=" + id +
                ", produtoId=" + produtoId +
                ", messagem='" + messagem + '\'' +
                '}';
    }
}
