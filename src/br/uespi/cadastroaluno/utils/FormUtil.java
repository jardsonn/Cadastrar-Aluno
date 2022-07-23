package br.uespi.cadastroaluno.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import br.uespi.cadastroaluno.interfaces.OnDialogDeleteListener;
import br.uespi.cadastroaluno.model.Aluno;

public class FormUtil {

	public static final String PATTERN_FORMAT_DATE_BR = "dd 'de' MMM 'de' yyyy";
	public static final String PATTERN_FORMAT_DATE_CSV = "dd/MM/yyyy";

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
			Pattern regEx = Pattern.compile("[a-zA-ZáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ´^~` ]");

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text == null) {
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
				if (text == null) {
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

	public static Border getBorder(JComponent field, int top, int left, int bottom, int right) {
		return BorderFactory.createCompoundBorder(field.getBorder(),
				BorderFactory.createEmptyBorder(top, left, bottom, right));
	}

	public static Border getBorder(JComponent field) {
		return BorderFactory.createCompoundBorder(field.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	public static Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	public static ImageIcon getScaledImageIcon(ImageIcon icon, int w, int h) {
		return new ImageIcon(getScaledImage(icon.getImage(), w, h));
	}

	public static ImageIcon getScaledImageIcon(Object obj, String path, int w, int h) {
		return new ImageIcon(getScaledImage(new ImageIcon(obj.getClass().getResource(path)).getImage(), w, h));
	}

	public static String dateToString(Date date, String format) {
		Locale localeBr = new Locale("pt", "BR");
		SimpleDateFormat sdf = new SimpleDateFormat(format, localeBr);
		return sdf.format(date);
	}

	public static String dateToString(Date date) {
		return dateToString(date, PATTERN_FORMAT_DATE_BR);
	}

	public static Font getFontBold() {
		return new Font("Verdana", Font.BOLD, 14);
	}

	public static Font getFontBold(int fontSize) {
		return new Font("Verdana", Font.BOLD, fontSize);
	}

	public static Font getFontNormal(int fontSize) {
		return new Font("Verdana", Font.PLAIN, fontSize);
	}

	public static Font getFontNormal() {
		return new Font("Verdana", Font.PLAIN, 14);
	}
	
	public static Date stringToDate(String date) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void showErrorEmptyFieldMessage(Object obj, String msg) {
		showErrorMessage(obj, "Campo Obrigatório", msg);
	}

	public static void showErrorMessage(Object obj, String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE,
				FormUtil.getScaledImageIcon(obj, "img/error_icon.png", 40, 39));
	}

	public static void showSuccessMessage(Object obj, String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE,
				FormUtil.getScaledImageIcon(obj, "img/check_icon.png", 40, 39));
	}

	public static void deleteWithMessageDialog(Object obj, Aluno aluno, OnDialogDeleteListener callback) {
		Object[] options = { "CANCELAR", "EXCLUIR" };
		int option = JOptionPane.showOptionDialog(null,
				String.format("<html><body>Você realmente deseja exluir o aluno <b>%s</b>?</html></body>",
						aluno.getNome()),
				"Tem certeza?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				FormUtil.getScaledImageIcon(obj, "img/ic_delete.png", 35, 35), options, options[0]);
		if (option == 1) {
			callback.onDeleted(aluno);
		}

	}

}
