package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.beans.ConstructorProperties;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class StyledButton extends JButton {

	public StyledButton() {
		super(null, null);
		init();
	}

	public StyledButton(boolean hasBorder) {
		super(null, null);
		this.hasBorder = hasBorder;
		init();
	}

	public StyledButton(Icon icon) {
		super(null, icon);
		init();
	}

	@ConstructorProperties({ "text" })
	public StyledButton(String text) {
		super(text, null);
		init();
	}

	public StyledButton(Action a) {
		super(a);
		init();
	}

	public StyledButton(String text, Icon icon) {
		super(text, icon);
		init();
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	private boolean isHover = false;
	private boolean isClicked = false;

	private Color borderColor;

	private int radius = -1;

	private Color startColor;
	private Color endColor;
	private boolean hasBorder;

	@ConstructorProperties({ "text" })
	public StyledButton(String text, boolean isWhite) {
		super(text, null);
		this.hasBorder = isWhite;
		init();
	}

	@Override
	protected void paintComponent(Graphics g) {
		final Graphics2D g2 = (Graphics2D) g.create();
		int w = getWidth();
		int h = getHeight();
		g2.setPaint(setupGradientPaint());
		g2.fill(new RoundRectangle2D.Double(0, 0, w, h, radius, radius));
		g2.dispose();
		super.paintComponent(g);

	}

	private GradientPaint setupGradientPaint() {
		final GradientPaint gp;

		if (startColor != null && endColor != null) {
			gp = getGradientPaint(startColor, endColor);
		} else if (startColor != null) {
			gp = getGradientPaint(startColor, startColor);
		} else {
			gp = getGradientPaint(new Color(250, 175, 123), new Color(248, 201, 107));
		}
		return gp;
	}

	private GradientPaint getGradientPaint(Color start, Color end) {
		final GradientPaint gp;
		Point p1 = new Point(10, 10);
		Point p2 = new Point(getWidth(), getHeight());
		if (isClicked) {
			gp = new GradientPaint(p1, end.darker(), p2, start.darker(), true);
		} else if (isHover) {
			boolean isWhite = (end == null && start == Color.white) || (start == Color.white && end == Color.white);
			if(isWhite)
				gp = new GradientPaint(p1, new Color(245, 245, 245), p2, new Color(245, 245, 245), true);
			else
				gp = new GradientPaint(p1, end, p2, start, true);
		} else {
			gp = new GradientPaint(p1, start, p2, end, true);
		}

		isHover = false;
		// isClicked = false;
		return gp;
	}

	private void init() {
		setOpaque(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		if (radius == -1) {
			radius = 10;
		}

		if (borderColor == null) {
			borderColor = Color.white;
		}

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				isClicked = true;
			}

			public void mouseReleased(MouseEvent e) {
				isClicked = false;
			}

			public void mouseEntered(MouseEvent evt) {
				isHover = true;
			}

			public void mouseExited(MouseEvent evt) {
				isHover = false;
			}
		});

		if (hasBorder) {
			setBorderPainted(true);
			this.setBorder(new RoundedBorder());
		}

	}

	public void setBorderRadius(int radius) {
		this.radius = radius;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		this.setBorder(new RoundedBorder(borderColor));
	}

	public void setButtonColor(Color buttonColor) {
		this.startColor = buttonColor;
	}

	public void setButtonColor(Color startColor, Color endColor) {
		this.startColor = startColor;
		this.endColor = endColor;
	}

}
