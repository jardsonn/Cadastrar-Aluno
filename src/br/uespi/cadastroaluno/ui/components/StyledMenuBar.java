package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JMenuBar;

public class StyledMenuBar extends JMenuBar {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();

		Point p1 = new Point(10, 10);
		Point p2 = new Point(getWidth(), getHeight());
		final GradientPaint gp = new GradientPaint(p1, new Color(250, 175, 123), p2, new Color(248, 201, 107), true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

}
