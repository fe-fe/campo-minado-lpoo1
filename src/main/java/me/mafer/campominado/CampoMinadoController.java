/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.mafer.campominado;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author fefe
 */
public class CampoMinadoController implements CampoMinadoListener {
    private final CampoMinado campoMinado;
    private final MenuInicial menu;
    
    public CampoMinadoController(CampoMinado campoMinado, MenuInicial menu) {
        this.campoMinado = campoMinado;
        this.menu = menu;
    }
    
    @Override
    public void onWin() {
        System.out.println("ONWIN");
        SwingUtilities.invokeLater(() -> {
             JDialog dialogoFimJogo = menu.getDialogoFimJogo();
            JTextPane mensagemFimJogo = menu.getMensagemFimJogo();
            JLabel labelFimJogo = menu.getLabelFimJogo();
            dialogoFimJogo.setTitle("Parabens! Voce venceu!");
            labelFimJogo.setText("Parabens!");
            mensagemFimJogo.setText("Voce encontrou todas as bombas e venceu!");
            dialogoFimJogo.pack();
            dialogoFimJogo.setVisible(true);
        });
    }
    
    @Override
    public void onLose() {
        System.out.println("ONLOSE");
        SwingUtilities.invokeLater(() -> {
            JDialog dialogoFimJogo = menu.getDialogoFimJogo();
            JTextPane mensagemFimJogo = menu.getMensagemFimJogo();
            JLabel labelFimJogo = menu.getLabelFimJogo();
            dialogoFimJogo.setTitle("Ah! Voce perdeu!");
            labelFimJogo.setText("Que pena!");
            mensagemFimJogo.setText(String.format("Voce cavou uma bomba e explodiu!\n%d de %d bombas foram encontradas", campoMinado.getFlagsCorretas(), campoMinado.getQuantidadeBombas()));
            dialogoFimJogo.pack();
            dialogoFimJogo.setVisible(true);
        });
    }
    
    @Override
    public void onFlagChange() {
        System.out.println("ONFLAG");
        SwingUtilities.invokeLater(() -> {
            JTextField displayBandeiras = menu.getDisplayBandeiras();
            displayBandeiras.setText(String.format("Bandeiras Usadas: %d de %d", campoMinado.getFlagsUsadas(), campoMinado.getQuantidadeBombas()));
        });
    }
}
