/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.dao.RelatorioDao;
import model.dao.DaoFactory;
import model.entities.*;
import view.configuration.ConfigurationComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author mikae
 */
public class Relatorios extends javax.swing.JFrame implements Navegacao{

    public Relatorios() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelRelatorio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        relatoriosTable = new javax.swing.JTable();
        buttonRelatorioCadastro = new javax.swing.JButton();
        buttonMovimentacao = new javax.swing.JButton();
        buttonEstoque = new javax.swing.JButton();
        buttonVendas = new javax.swing.JButton();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Relatorios");

        relatoriosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(relatoriosTable);

        buttonRelatorioCadastro.setText("Relatorio de Cadastro");
        buttonRelatorioCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRelatorioCadastroActionPerformed(evt);
            }
        });

        buttonMovimentacao.setText("Movimentacao de Estoque");
        buttonMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMovimentacaoActionPerformed(evt);
            }
        });

        buttonEstoque.setText("Estoque Baixo");
        buttonEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEstoqueActionPerformed(evt);
            }
        });

        buttonVendas.setText("Relatorio de Vendas");
        buttonVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVendasActionPerformed(evt);
            }
        });

        buttonVoltar.setText("Voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt, Relatorios.this);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addComponent(buttonRelatorioCadastro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonMovimentacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonEstoque)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonVendas))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(325, 325, 325)
                                .addComponent(labelRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonVoltar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buttonRelatorioCadastro)
                    .addComponent(buttonMovimentacao)
                    .addComponent(buttonEstoque)
                    .addComponent(buttonVendas))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelRelatorio)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonVoltar)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }

    private void buttonRelatorioCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRelatorioCadastroActionPerformed
        labelRelatorio.setText("Relatorios Cadastro");
        String table = "RelatorioProdutos";
        List<ProdutoRelatorio> dados = DaoFactory.createRelatorioDao().findAll(table, ProdutoRelatorio.class);
        configurationTable(table, dados);

    }

    private void buttonMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {
        labelRelatorio.setText("Relatorios Movimentacao");
        String table = "Movimentacao";
        List<Movimentacao> dados = DaoFactory.createRelatorioDao().findAll(table, Movimentacao.class);
        configurationTable(table, dados);


    }

    private void buttonEstoqueActionPerformed(java.awt.event.ActionEvent evt) {
        labelRelatorio.setText("Relatorios Estoque");
        String table = "AlertaEstoque";
        List<AlertaEstoque> dados = DaoFactory.createRelatorioDao().findAll(table, AlertaEstoque.class);
        configurationTable(table, dados);

    }

    private void buttonVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVendasActionPerformed
        labelRelatorio.setText("Relatorios Venda");
        String table = "RelatorioVendas";
        List<Vendas> dados = DaoFactory.createRelatorioDao().findAll(table, Vendas.class);
        configurationTable(table, dados);

    }


    // Adiciona todos os dados da coluna
    private <T extends TableFormatRelatorio> void configurationTable(String table, List<T> dados){
        DefaultTableModel tableModel = (DefaultTableModel) relatoriosTable.getModel();
        RelatorioDao relatorioDao = DaoFactory.createRelatorioDao();

        List<String> colunas = relatorioDao.findAllColunas(table);
        ConfigurationComponents.createDinamicTable(tableModel, colunas, null);

        System.out.println(dados);

        for(T obj: dados){
            tableModel.addRow(obj.getTableFormatRelatorio());
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEstoque;
    private javax.swing.JButton buttonMovimentacao;
    private javax.swing.JButton buttonRelatorioCadastro;
    private javax.swing.JButton buttonVendas;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelRelatorio;
    private javax.swing.JTable relatoriosTable;
    // End of variables declaration//GEN-END:variables
}
