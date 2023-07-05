package fractalTree;

import javax.swing.*;
import java.awt.*;

public class arvoreFractal extends JPanel {

    private static double ANGULO = Math.PI / 10;
    private static float FATOR_ENCOLHIMENTO = 0.67f;
    private static int MAX_RECURSOES = 10;
    private static int recursaoAtual = 0;

    public void desenhaRamo(Graphics g, int pontoX, int pontoY, double direcaoX, double direcaoY, float tamanho, int numeroRecursao) {
        if (numeroRecursao > recursaoAtual) {
            return;
        }
        
        int x2 = (int) (pontoX + direcaoX * tamanho);
        int y2 = (int) (pontoY + direcaoY * tamanho);
        g.setColor(Color.BLACK);
        g.drawLine(pontoX, pontoY, x2, y2);

        float novoTamanho = tamanho * FATOR_ENCOLHIMENTO;
        double direcaoX2 = Math.sin(ANGULO) * direcaoY + Math.cos(ANGULO) * direcaoX;
        double direcaoY2 = -(Math.sin(ANGULO) * direcaoX) + Math.cos(ANGULO) * direcaoY;
        int n2 = numeroRecursao + 1;
        desenhaRamo(g, x2, y2, direcaoX2, direcaoY2, novoTamanho, n2);
        direcaoX2 = Math.cos(-ANGULO) * direcaoX + Math.sin(-ANGULO) * direcaoY;
        direcaoY2 = -Math.sin(-ANGULO) * direcaoX + Math.cos(-ANGULO) * direcaoY;
        desenhaRamo(g, x2, y2, direcaoX2, direcaoY2, novoTamanho, n2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenhaRamo(g, getWidth() / 2, getHeight(), 0, -1, getHeight() / 3, 0);
    }

    public static void main(String[] args) {
        JFrame quadro = new JFrame();
        arvoreFractal arvore = new arvoreFractal();
        quadro.add(arvore);
        quadro.setSize(new Dimension(800, 500));
        quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quadro.setVisible(true);

        new Timer(1000, e -> {
            if (recursaoAtual < MAX_RECURSOES) {
                recursaoAtual++;
                arvore.repaint();
            }
        }).start();
    }
}
