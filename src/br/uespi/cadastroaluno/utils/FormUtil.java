package br.uespi.cadastroaluno.utils;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

public class FormUtil {

	public static MaskFormatter cpfFormat() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mask;
	}

	public static MaskFormatter phoneNumberFormat() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("(##) # ####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mask;
	}

	public static void formatName(JTextField textField) {

		((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
			boolean hasSpace = false;
			Pattern regEx = Pattern.compile("[ a-zA-Z]");

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if(text == null) {
					super.replace(fb, offset, length, "", attrs);
					return;
				}
				Matcher matcher = regEx.matcher(text);
				if (!matcher.matches()) {
					return;
				}

				if (offset == 0) {
					char[] chars = text.toCharArray();
					if (chars.length > 1) {
						for (int i = chars.length; i > 0; i--) {
							super.replace(fb, offset, length,
									i == 0 ? String.valueOf(chars[i]).toUpperCase() : String.valueOf(chars[i - 1]),
									attrs);
						}
					} else {
						super.replace(fb, offset, length, text.toUpperCase(), attrs);
					}

				} else {
					if (hasSpace) {
						super.replace(fb, offset, length, text.toUpperCase(), attrs);
					} else {
						super.replace(fb, offset, length, text, attrs);

					}
					hasSpace = text.equals(" ");

				}
			}
		});
	}

	public static void registrationNumberFormat(JTextField textField) {
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
			Pattern regEx = Pattern.compile("\\d*");

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if(text == null) {
					super.replace(fb, offset, length, "", attrs);
					return;
				}
				Matcher matcher = regEx.matcher(text);
				if (!matcher.matches()) {
					return;
				}
				if (offset == 7) {
					return;
				}
				super.replace(fb, offset, length, text, attrs);
			}
		});
	}

	public static MaskFormatter dateNumberFormat() {
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return mask;
	}

	public static Border getBorder(JComponent field) {
		return BorderFactory.createCompoundBorder(field.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

}
