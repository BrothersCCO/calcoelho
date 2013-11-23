import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends JFrame implements KeyListener {
	protected String msgInicial = "0", separadorDecimal = ".";
	protected JTextField visor;

	public GUI(ActionListener ouvinte) {
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout(5, 5));

		visor = new JTextField(msgInicial);
		visor.setEditable(false);
		visor.setHorizontalAlignment(JTextField.RIGHT);
		visor.setBorder(new javax.swing.border.LineBorder(java.awt.Color.GRAY, 2));
		visor.setBackground(java.awt.Color.WHITE);

		pane.add(visor, BorderLayout.NORTH);

		JPanel teclado = new JPanel(new GridLayout(7, 5, 8, 8));
		JButton tecla;
		
		tecla = new JButton("MS");
		tecla.setActionCommand("#S");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("MR");
		tecla.setActionCommand("#R");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("MC");
		tecla.setActionCommand("#C");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("M+");
		tecla.setActionCommand("#A");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("DEL");
		tecla.setActionCommand("DEL");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("CE");
		tecla.setActionCommand("CE");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("C");
		tecla.setActionCommand("CL");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("+/-");
		tecla.setActionCommand("SINAL");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("sqrt");
		tecla.setActionCommand("RAIZ_QUADRADA");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("x²");
		tecla.setActionCommand("QUADRADO");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("1/x");
		tecla.setActionCommand("UMPORX");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("sen");
		tecla.setActionCommand("SENO");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("cos");
		tecla.setActionCommand("COSSENO");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("tan");
		tecla.setActionCommand("TANGENTE");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("%");
		tecla.setActionCommand("PORCENTAGEM");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("7");
		tecla.setActionCommand("$7");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("8");
		tecla.setActionCommand("$8");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("9");
		tecla.setActionCommand("$9");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("/");
		tecla.setActionCommand("DIVISAO");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("y^x");
		tecla.setActionCommand("POTENCIA");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("4");
		tecla.setActionCommand("$4");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("5");
		tecla.setActionCommand("$5");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("6");
		tecla.setActionCommand("$6");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("*");
		tecla.setActionCommand("PRODUTO");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("n!");
		tecla.setActionCommand("FATORIAL");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("1");
		tecla.setActionCommand("$1");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("2");
		tecla.setActionCommand("$2");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("3");
		tecla.setActionCommand("$3");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("-");
		tecla.setActionCommand("MENOS");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);
		
		tecla = new JButton("pi");
		tecla.setActionCommand("@P");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		tecla.setFocusable(false);

		tecla = new JButton("0");
		tecla.setActionCommand("$0");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);

		tecla = new JButton(separadorDecimal);
		tecla.setActionCommand("SEPARADOR_DECIMAL");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);

		tecla = new JButton("=");
		tecla.setActionCommand("RESULTADO");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);

		tecla = new JButton("+");
		tecla.setActionCommand("SOMA");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);
		
		tecla = new JButton("e");
		tecla.setActionCommand("@E");
		tecla.addActionListener(ouvinte);
		teclado.add(tecla);

		pane.add(teclado, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});

		setTitle("Calculadora");
		pack();
		setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Stub de método gerado automaticamente

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Stub de método gerado automaticamente

	}

}