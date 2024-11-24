package model.impl;

import model.dao.RelatorioDao;
import model.entities.*;
import util.DB;
import util.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDaoJDBC implements RelatorioDao {
    private Connection conn;

    public RelatorioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void insertRelatorioVenda(Produto prod, Integer quantidade, Double lucro){
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("call relatorio_vendas(?, ?, ?, ?)");
            st.setString(1, prod.getNome());
            st.setInt(2, prod.getId());
            st.setInt(3, quantidade);
            st.setDouble(4, lucro);

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }


    public List<String> findAllColunas(String table){
        PreparedStatement st = null;
        ResultSet rs = null;
        List<String> colunas = new ArrayList<>();
        try{
            st = conn.prepareStatement("SELECT * FROM " + table);
            rs = st.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                colunas.add(metaData.getColumnName(i));
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return colunas;
    }

    public <T> List<T> findAll(String table, Class<T> clazz) {
        PreparedStatement st = null;
        ResultSet rs = null;

        List<T> list = new ArrayList<>();
        try{
            
            st = conn.prepareStatement("SELECT * FROM " + table);
            rs = st.executeQuery();

            while (rs.next()) {
                T entity = insantiateByTable(rs, clazz);
                System.out.println();
                list.add(entity);
            }

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return list;
    }

    private <T> T insantiateByTable(ResultSet rs, Class<T> clazz) throws SQLException{
        try{
            if (clazz == AlertaEstoque.class){
                AlertaEstoque obj = instantiateAlertaEstoque(rs);
                return (T) obj;
            } else if (clazz == Movimentacao.class) {
                Movimentacao obj = instantiateMovimentacao(rs);
                return (T) obj;
            } else if (clazz == ProdutoRelatorio.class){
                ProdutoRelatorio obj = instantiateProdutoRelatorio(rs);
                return (T) obj;
            } else if (clazz == Vendas.class){
                Vendas obj = instantiateVendas(rs);
                return (T) obj;
            } else{
                throw new IllegalArgumentException("Classe não suportada");
            }
        } catch (Exception e){
            throw new SQLException("Erro ao instanciar a entidade: " + e.getMessage(), e);
        }
    }

    private AlertaEstoque instantiateAlertaEstoque(ResultSet rs) throws SQLException {
        AlertaEstoque obj = new AlertaEstoque();
        obj.setId(rs.getInt("id"));
        obj.setProdutoId(rs.getInt("produto_id"));
        obj.setMessagem(rs.getString("mensagem"));
        obj.setData(String.valueOf(rs.getDate("data_alerta")));
        return obj;
    }

    private Vendas instantiateVendas(ResultSet rs) throws SQLException {
        Vendas obj = new Vendas();
        obj.setId(rs.getInt("id"));
        obj.setProdutoId(rs.getInt("produto_id"));
        obj.setNomeProduto(rs.getString("nome_produto"));
        obj.setQuantidade(rs.getInt("quantidade_vendida"));
        obj.setLucroTotal(rs.getDouble("lucro_total"));
        return obj;
    }

    private Movimentacao instantiateMovimentacao(ResultSet rs) throws SQLException {
        Movimentacao obj = new Movimentacao();
        obj.setId(rs.getInt("id"));
        obj.setProdutoId(rs.getInt("produto_id"));
        obj.setTipoMovimentacao(rs.getString("tipo_movimentacao"));
        obj.setQuantidade(rs.getInt("quantidade"));
        obj.setData(String.valueOf(rs.getDate("data_movimentacao")));
        return obj;
    }

    private ProdutoRelatorio instantiateProdutoRelatorio(ResultSet rs) throws SQLException {
        ProdutoRelatorio prod = new ProdutoRelatorio();
        prod.setId(rs.getInt("id"));
        prod.setProdutoId(rs.getInt("produto_id"));
        prod.setNome(rs.getString("nome"));
        prod.setDescricao(rs.getString("descricao"));
        prod.setQuantidade(rs.getInt("quantidade_estoque"));
        prod.setPrecoCompra(rs.getDouble("preco_compra"));
        prod.setPrecoVenda(rs.getDouble("preco_venda"));
        prod.setCategoriaNome(rs.getString("categoria_nome"));
        prod.setData(String.valueOf(rs.getDate("data_movimentacao")));
        return prod;
    }
}
