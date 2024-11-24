package model.impl;

import model.dao.ProdutoDao;
import model.entities.Categoria;
import model.entities.Produto;
import util.DB;
import util.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insert(Produto obj) {
        PreparedStatement st = null;
        boolean isInserted = false;

        try{
            st = conn.prepareStatement("CALL cadastro_produto(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getDescricao());
            st.setDouble(3, obj.getPrecoCompra());
            st.setDouble(4, obj.getPrecoVenda());
            st.setInt(5, obj.getQuantidade());
            st.setString(6, obj.getCategoria().getNome());
            st.setString(7, obj.getCategoria().getDescricao());

            int rowAffected = st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                obj.setId(id);
            }
            DB.closeResultSet(rs);

            isInserted = true;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

        return isInserted;

    }

    @Override
    public Integer update(String nome, String valorCompra, String valorVenda, String quantidade) {
        PreparedStatement st = null;
        StringBuilder sql = new StringBuilder("UPDATE Produto SET ");

        int rowsAffected = 0;
        double valorCompraDouble = 0;
        double valorVendaDouble = 0;
        int quantidadeInt = 0;

        if (!valorCompra.isEmpty()) {
            valorCompraDouble = Double.parseDouble(valorCompra);
            sql.append("preco_compra").append(" = ?, ");
        }
        if (!valorVenda.isEmpty()) {
            valorVendaDouble = Double.parseDouble(valorVenda);
            sql.append("preco_venda").append(" = ?, ");
        }
        if (!quantidade.isEmpty()) {
            quantidadeInt = Integer.parseInt(quantidade);
            sql.append("quantidade_estoque").append(" = ?, ");
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE nome = ?");

        try{
            int index = 1;
            st = conn.prepareStatement(sql.toString());
            if (valorCompraDouble != 0) {
                st.setDouble(index++, valorCompraDouble);
            }
            if (valorVendaDouble != 0) {
                st.setDouble(index++, valorVendaDouble);
            }
            if (quantidadeInt != 0) {
                st.setInt(index++, quantidadeInt);
            }

            st.setString(index, nome);

            rowsAffected = st.executeUpdate();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

        return rowsAffected;
    }

    @Override
    public Integer updateQuantidade(Integer quantidade, String nome) {
        PreparedStatement st = null;
        int rowsAffected = 0;
        try{
            st = conn.prepareStatement("UPDATE Produto "
                                            + "SET quantidade_estoque = ? "
                                            + "WHERE UPPER(nome) = UPPER(?)");

            st.setInt(1, quantidade);
            st.setString(2, nome);

            rowsAffected = st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
        return rowsAffected;

    }

    @Override
    public Integer deleteByName(String name) {
        PreparedStatement st = null;
        int rowsAffected = 0;
        try{
            st = conn.prepareStatement(
                    "UPDATE Produto "
                    + "INNER JOIN Categoria "
                    + "ON Produto.categoria_id = Categoria.id "
                    + "SET Produto.deleted = 1 "
                    + "WHERE UPPER(Produto.nome) = UPPER(?) AND Produto.deleted = 0");

            st.setString(1, name);
            rowsAffected = st.executeUpdate();

        } catch (SQLException e){
            DB.closeStatement(st);
        } finally {
            DB.closeStatement(st);
        }

        return rowsAffected;
    }

    @Override
    public Produto findByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT Produto.*, Categoria.nome as nome_categoria, Categoria.descricao as descricao_categoria "
                    + "FROM Produto INNER JOIN Categoria "
                    + "ON Produto.categoria_id = Categoria.id "
                    + "WHERE Produto.nome = ? AND Produto.deleted = 0");

            st.setString(1, name);
            rs = st.executeQuery();

            if (!rs.next()) return null;

            Categoria cat = instantiateCategoria(rs);
            Produto prod = instantiateProduto(rs, cat);

            return prod;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }


    }

    @Override
    public Produto findByNameAndCategory(String name, Categoria categoria) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT Produto.*, Categoria.nome as nome_categoria, Categoria.descricao as descricao_categoria "
                            + "FROM Produto INNER JOIN Categoria "
                            + "ON Produto.categoria_id = Categoria.id "
                            + "WHERE UPPER(Produto.nome) = UPPER(?) AND Produto.deleted = 0 AND UPPER(Categoria.nome) = UPPER(?)");

            st.setString(1, name);
            st.setString(2, categoria.getNome());
            rs = st.executeQuery();

            if (!rs.next()) return null;

            Categoria cat = instantiateCategoria(rs);
            Produto prod = instantiateProduto(rs, cat);

            return prod;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Produto instantiateProduto(ResultSet rs, Categoria cat) throws SQLException {
        Produto prod = new Produto();
        prod.setId(rs.getInt("id"));
        prod.setNome(rs.getString("nome"));
        prod.setDescricao(rs.getString("descricao"));
        prod.setPrecoCompra(rs.getDouble("preco_compra"));
        prod.setprecoVenda(rs.getDouble("preco_venda"));
        prod.setQuantidade(rs.getInt("quantidade_estoque"));
        prod.setCategoria(cat);
        return prod;
    }

    private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(rs.getInt("categoria_id"));
        cat.setNome(rs.getString("nome_categoria"));
        cat.setDescricao(rs.getString("descricao_categoria"));
        return cat;
    }

    @Override
    public List<Produto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT Produto.*, Categoria.nome as nome_categoria, Categoria.descricao as descricao_categoria "
                        + "FROM Produto INNER JOIN Categoria "
                        + "ON Produto.categoria_id = Categoria.id "
                        + "WHERE Produto.deleted = 0 "
                        + "ORDER BY nome;"
            );

            rs = st.executeQuery();

            List<Produto> list = new ArrayList<>();
            Map<Integer, Categoria> map = new HashMap<>();

            while (rs.next()){

                Categoria cat = map.get(rs.getInt("categoria_id"));

                if (cat == null){
                    cat = instantiateCategoria(rs);
                    map.put(rs.getInt("categoria_id"), cat);
                }

                Produto prod = instantiateProduto(rs, cat);
                list.add(prod);
            }
            return list;

        } catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Produto> findAllByCategoria(Categoria categoria) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT Produto.*, Categoria.nome as nome_categoria, Categoria.descricao as descricao_categoria "
                    + "FROM Produto INNER JOIN Categoria "
                    + "ON Produto.categoria_id = Categoria.id "
                    + "WHERE UPPER(Categoria.nome) = UPPER(?) AND Produto.deleted = 0 "
                    + "ORDER BY nome;"
            );

            st.setString(1, categoria.getNome());

            rs = st.executeQuery();

            List<Produto> list = new ArrayList<>();
            Map<Integer, Categoria> map = new HashMap<>();

            while (rs.next()){

                Categoria cat = map.get(rs.getInt("categoria_id"));

                if (cat == null){
                    cat = instantiateCategoria(rs);
                    map.put(rs.getInt("categoria_id"), cat);
                }

                Produto prod = instantiateProduto(rs, cat);
                list.add(prod);
            }
            return list;

        } catch (SQLException e ){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }
}
