/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.mafer.campominado;

import java.awt.Color;

/**
 *
 * @author fefe
 */
public class Bomba extends Casa {
    public Bomba (CampoMinado campoMinado, int posicaoVetor) {
        super(campoMinado, posicaoVetor);
    }
    
    @Override
    public void abrir() {
        this.setBackground(Color.red);
        this.setText("B");
    }
    
    @Override
    public void handleRightClick() {
        if (!this.getCampoMinado().isJogando()) {
                return;
        } else {
            this.getCampoMinado().abrirRecursivo(this);
        }
        
    }
}
