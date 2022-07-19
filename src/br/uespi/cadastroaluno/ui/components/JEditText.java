package br.uespi.cadastroaluno.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.DefaultFormatterFactory;

public class JEditText extends JFormattedTextField {

	private String placeholder;

	private AbstractFormatter formatter;

	public JEditText() {

	}

	public JEditText(final String placeholder) {
		this.placeholder = placeholder;
	}

	public JEditText(AbstractFormatter formatter, String placeholder) {
		super(new DefaultFormatterFactory(formatter));
		this.placeholder = placeholder;
		this.formatter = formatter;
	}

	public JEditText(AbstractFormatter formatter) {
		super(new DefaultFormatterFactory(formatter));
		this.formatter = formatter;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);

		if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
			if (formatter == null)
				return;
			else {
				if(getText().replaceAll("[ ()-.]", "").length() > 0 || placeholder == null) {
					return;
				}
			}
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(180, 180, 180));
		g.drawString(placeholder, getInsets().left, (this.getHeight() / 2) + pG.getFontMetrics().getMaxAscent() / 2);
	}

	public void setPlaceholder(final String s) {
		placeholder = s;
	}

}
