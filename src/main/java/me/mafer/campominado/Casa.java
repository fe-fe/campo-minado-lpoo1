/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package me.mafer.campominado;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author fefe
 */
public abstract class Casa extends JButton{
    private final CampoMinado campoMinado;
    private final int posicaoVetor;
    private StatusCasa status = StatusCasa.FECHADA;
    
    public Casa(CampoMinado campoMinado, int posicaoVetor) {
        super("");
        this.campoMinado = campoMinado;
        this.posicaoVetor = posicaoVetor;
        this.addMouseListener(handleMouseClick);
        this.setFont(this.getFont().deriveFont(18f)); 
        this.setSize(10, 10);
        this.setMargin(new Insets(0,0,0,0));
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setFocusPainted(false);        // remove a borda de foco azul/cinza
        if (this.posicaoVetor % 2 == 0) {
            this.setBackground(new Color(162, 209, 73));
        } else {
            this.setBackground(new Color(170, 215, 81));
        }  
    }
    
    private final MouseListener handleMouseClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                handleLeftClick();
            }
            else if (SwingUtilities.isRightMouseButton(e)) {
                handleRightClick();
            }
        }
    };
            
    public void setStatusCasa(StatusCasa status) {
        this.status = status;
    }
    
    public StatusCasa getStatusCasa() {
        return this.status;
    }
    
    public void handleRightClick() {}
    
    public void handleLeftClick() {
        if (this.status == StatusCasa.FECHADA) {
            this.setText("F");
            this.status = StatusCasa.BANDEIRA;
            this.campoMinado.adicionarFlag(this.posicaoVetor);
        } else if (this.status == StatusCasa.BANDEIRA) {
            this.status = StatusCasa.FECHADA;
            this.campoMinado.removerFlag(this.posicaoVetor);
            this.setText("");
        }
    }   
    
    public CampoMinado getCampoMinado() {
        return this.campoMinado;
    }
    
    public int getPosicaoVetor() {
        return this.posicaoVetor;
    }

}
