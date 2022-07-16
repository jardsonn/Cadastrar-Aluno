package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {

	private final Color color;

	private int radius = -1;

	public RoundedBorder() {
		this(new Color(195, 195, 195), -1);
	}

	public RoundedBorder(Color c) {
		this(c, -1);
	}

	public RoundedBorder(Color c, int radius) {
		if (radius == -1) {
			radius = 10;
		}
		color = c;
		this.radius = radius;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(color);
		g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
		g2d.dispose();
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return (getBorderInsets(c, new Insets(radius, radius, radius, radius)));
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.left = insets.top = insets.right = insets.bottom = radius / 2;
		return insets;
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}