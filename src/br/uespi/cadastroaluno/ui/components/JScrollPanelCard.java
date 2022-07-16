package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class JScrollPanelCard extends JScrollPane {


	@Override
	protected void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();

		Point p1 = new Point(10, 10);
		Point p2 = new Point(getWidth(), getHeight());
		final GradientPaint gp = new GradientPaint(p2, new Color(255, 255, 255), p1, new Color(255, 255, 255), true);
		final Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(gp);
		g2.fill(new RoundRectangle2D.Double(0, 0, w, h, 100, 100));

	}


}
