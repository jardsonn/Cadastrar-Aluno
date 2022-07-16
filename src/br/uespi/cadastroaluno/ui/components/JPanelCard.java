package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class JPanelCard extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		Point p1 = new Point(10, 10);
		Point p2 = new Point(getWidth(), getHeight());
		final GradientPaint gp = new GradientPaint(p2, new Color(255, 255, 255), p1, new Color(255, 255, 255), true);
		final Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(gp);
		g2.fill(new RoundRectangle2D.Double(0, 0, w, h, 20, 20));

	}

}
