/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package me.mafer.campominado;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Maria Fernanda Zandona Casagrande
 */

public class CampoMinado {
    private int linhas = 0;
    private int colunas = 0;
    private final List<Casa> casas;
    private int quantidadeBombas;
    private int flagsUsadas = 0;
    private int flagsCorretas = 0;
    private int casasAbertas;
    
    public CampoMinado(int linhas, int colunas, int quantidadeBombas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.casas = new ArrayList<>();
        this.casasAbertas = 0;
        this.quantidadeBombas = quantidadeBombas;
    }
    
    public  int getLinhas() {
        return linhas;
    }
    
    public int getColunas() {
        return colunas;
    }
    
    private List<Integer> gerarBombas() {
        List<Integer> posicoesEscolhidas = new ArrayList<>();
        int totalPosicoes = this.linhas * this.colunas;
        Random random = new Random();
        int numeroGerado;
        for (int i = 0; i < this.quantidadeBombas; i++) {
            do {
                numeroGerado = random.nextInt(totalPosicoes);
            } while (posicoesEscolhidas.contains(numeroGerado));
            posicoesEscolhidas.add(numeroGerado);
        }
        return posicoesEscolhidas;
    }
    
    public void gerarJogo() {
        List<Integer> bombas = this.gerarBombas();
        for (int i = 0; i < this.linhas * this.colunas; i++) {
            Casa novaCasa;
            if (bombas.contains(i)) {
                novaCasa = new Bomba(this, i);
            } else {
                novaCasa = new CasaLivre(this, i);
            }
            casas.add(novaCasa);
        }
        for (Casa c : casas) {
            if (c instanceof CasaLivre) {
                ((CasaLivre) c).contarBombasProximas();
            }
        }
    }
    
    public void abrirRecursivo(CasaLivre casa) {
        System.out.println("------> ABRINDO CASA = " + casa.getPosicaoVetor());
        for (Casa casaAdj : casa.buscarCasasAdjacentes()) {
            if (casaAdj instanceof CasaLivre && casaAdj.getStatusCasa() == StatusCasa.FECHADA) {
                CasaLivre casaLivre = (CasaLivre) casaAdj;
                casaLivre.abrir();
                if (casaLivre.getBombasProximas() == 0) {
                    this.abrirRecursivo(casaLivre);
                }
            }
        }
        System.out.println("------> FIM ABRINDO CASA = " + casa.getPosicaoVetor());
    }
    
    public List<Casa> getCasas() {
        return casas;
    }
    
    public Casa getCasa(int posicaoVetor) {
        return this.casas.get(posicaoVetor);
    }
    
    public boolean isBomba(int posicaoVetor) {
        return casas.get(posicaoVetor) instanceof Bomba;
    }
    
    public void adicionarFlag(int posicaoVetor) {
        flagsUsadas++;
        if (isBomba(posicaoVetor)) {
            flagsCorretas++;
        }
        if (flagsCorretas == quantidadeBombas) {
            System.out.println("GANHOUUUU");
        }
    }
    
    public void removerFlag(int posicaoVetor) {
        flagsUsadas--;
        if (isBomba(posicaoVetor)) {
            flagsCorretas--;
        }
    }
    
    public static void main(String[] args) {
        MenuInicial.main();
    }
}
