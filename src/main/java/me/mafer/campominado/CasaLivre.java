/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.mafer.campominado;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Fernanda Zandona Casagrande
 */
public class CasaLivre extends Casa {
    private int bombasProximas;
    
    public CasaLivre(CampoMinado campoMinado, int posicaoVetor) {
        super(campoMinado, posicaoVetor);
    }
    
    public List<Casa> buscarCasasAdjacentes() {
        List<Casa> casasAdjacentes = new ArrayList<>();
        int posicao = this.getPosicaoVetor();
        int colunas = this.getCampoMinado().getColunas();
        int linhaCasa = posicao / colunas;
        int colunaCasa = posicao % colunas;
        int inicioColuna = colunaCasa - 1;
        int fimColuna = colunaCasa + 1;

        if (colunaCasa == 0) { // se estiver na primeira coluna, nao tem coluna a esquerda, entao muda o inicio para a coluna atual
            inicioColuna++;
        } else if (colunaCasa == this.getCampoMinado().getColunas() - 1) { // o mesmo para na ultima coluna, pois nao teria coluna a direita
            fimColuna--;
        }
        
        // o mesmo de cima, mas para linhas e na vertical
        int inicioLinha = linhaCasa - 1;
        int fimLinha = linhaCasa + 1;
        
        if (linhaCasa == 0) {
            inicioLinha++;
        } else if (linhaCasa == this.getCampoMinado().getLinhas() - 1) {
            fimLinha--;
        } 
        
        for (int linha = inicioLinha; linha <= fimLinha; linha++) {
            for (int coluna = inicioColuna; coluna <= fimColuna; coluna++) {
                int casaAdj = (colunas*linha) + coluna;
                casasAdjacentes.add(this.getCampoMinado().getCasa(casaAdj));
            }
        }
        
        return casasAdjacentes;
    }
    
    public void contarBombasProximas() { // verifica o quadrado de 3 por 3
        int bombas = 0;
        for (Casa c : this.buscarCasasAdjacentes()) {
            if (c instanceof Bomba) {
                bombas++;
            }
        }
        this.bombasProximas = bombas;
    }
    
    public int getBombasProximas() {
        return this.bombasProximas;
    }
    
    @Override
    public void abrir() {
        if (this.bombasProximas != 0) {
            this.setText(String.valueOf(this.bombasProximas));
        } else {
            this.setText("");
        }
        if (this.getPosicaoVetor() % 2 == 0) {
            this.setBackground(new Color(215, 184, 153));
        } else {
            this.setBackground(new Color(232, 196, 156));
        }
        this.setStatusCasa(StatusCasa.ABERTA);
    }
    
    @Override
    public void handleRightClick() {
        if (!this.getCampoMinado().isJogando()) {
            return;
        }
        this.getCampoMinado().abrirRecursivo(this);
    }
}
