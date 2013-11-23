/* Calculadora.java
 * Copyright 2000-2012 Prof. Rosvelter J. Coelho da Costa
 *
 * Observar correspondencia com o diagrama de estados (cf. aula/transparencias).
 */

import javax.swing.*;

import java.awt.event.*;

class Main {
	public static void main(String[] args) {
		new Calculadora();
	}
}

interface Operacao {
	double op(double[] x) throws Exception;
}

interface Estado {
	void eval();

	void eval(char x);

	void eval(Operacao op, boolean bin);

	void mem(char x);

	void clr(boolean e);

	void pct();

	void con(char x);
}

public class Calculadora implements ActionListener {
	private JTextField visor;
	String digitos = "";
	double acumulado = 0.0, valor = 0.0, mem = 0.0;
	Operacao op = null;
	Estado[] estados;
	int atual = 0;

	private final Operacao sinal = new Operacao() {
		public double op(double[] x) {
			return -x[1];
		}
	}, raiz = new Operacao() {
		public double op(double[] x) throws Exception {
			if (x[1] >= 0) {
				return Math.sqrt(x[1]);
			}
			throw new Exception();
		}
	}, quadrado = new Operacao() {
		public double op(double[] x) {
			return Math.pow(x[1], 2);
		}
	}, potencia = new Operacao() {
		public double op(double[] x) {
			return Math.pow(x[0], x[1]);
		}
	}, fatorial = new Operacao() {
		public double op(double[] x) throws Exception {
			if (x[1] >= 0) {
				int fatorial = 1;
				for (int i = 2; i <= x[1]; ++i) {
					fatorial *= i;
				}
				return fatorial;
			}
			throw new Exception();
		}
	}, umporx = new Operacao() {
		public double op(double[] x) throws Exception {
			if (x[1] != 0) {
				return 1 / x[1];
			}
			throw new Exception();
		}
	}, seno = new Operacao() {
		public double op(double[] x) {
			return Math.sin(x[1]);
		}
	}, cosseno = new Operacao() {
		public double op(double[] x) {
			return Math.cos(x[1]);
		}
	}, tangente = new Operacao() {
		public double op(double[] x) throws Exception {
			if (x[1] % 180 == 90) {
				return Math.tan(x[1]);
			}
			throw new Exception();
		}
	}, soma = new Operacao() {
		public double op(double[] x) {
			return x[0] + x[1];
		}
	}, menos = new Operacao() {
		public double op(double[] x) {
			return x[0] - x[1];
		}
	}, produto = new Operacao() {
		public double op(double[] x) {
			return x[0] * x[1];
		}
	}, divisao = new Operacao() {
		public double op(double[] x) throws Exception {
			if (x[1] != 0) {
				return x[0] / x[1];
			}
			throw new Exception();
		}
	};

	Calculadora() {
		visor = new GUI(this).visor;
		op = soma;

		estados = new Estado[] { new CalculandoResultado(),
				new DigitandoNumero(), new EscolhendoOperacao(),
				new DigitandoDecimal() };
	}

	void executar() {
		try {
			valor = op.op(new double[] { acumulado, valor });
			digitos = Double.toString(valor);
			show(digitos);
		} catch (Exception e) {
			estados[atual].clr(true);
			show("Erro");
		}
	}

	void show(double val) {
		visor.setText("" + val);
	}

	void show(String val) {
		visor.setText(val);
	}

	class CalculandoResultado implements Estado {
		public void eval() {
			executar();
		}

		public void eval(char x) {
			acumulado = 0.0;
			op = soma;
			digitos = Character.toString(x);
			atual = 1;
			switch (x) {
			case '.':
				digitos = '0' + digitos;
				atual = 3;
				break;
			case '0':
				atual = 0;
			}
			show(digitos);
		}

		public void eval(Operacao s, boolean bin) {
			if (bin)
				acumulado = valor;
			op = s;
			atual = 2;
			if (!bin) {
				executar();
			}
		}

		public void mem(char x) {
			if (x == 'S') {
				mem = Double.parseDouble(visor.getText());
			} else if (x == 'R') {
				visor.setText(Double.toString(mem));
			} else if (x == 'C') {
				mem = 0.0;
			} else if (x == 'A') {
				mem += Double.parseDouble(visor.getText());
			}
		}

		@Override
		public void pct() {
			valor = acumulado * Double.parseDouble(visor.getText()) / 100;
			digitos = Double.toString(valor);
			show(digitos);
		}

		public void con(char x) {
			switch (x) {
			case 'P':
				show(Double.toString(Math.PI));
				break;
			case 'E':
				show(Double.toString(Math.E));
			}
		}

		public void clr(boolean e) {
			valor = 0.0;
			digitos = "0";
			show(digitos);

			if (e)
				acumulado = 0.0;

			atual = 0;
		}
	}

	class DigitandoNumero implements Estado {
		public void eval() {
			try {
				valor = Double.parseDouble(digitos);
			} catch (Exception ignorada) {
			}
			executar();
			atual = 0;
		}

		public void eval(char x) {
			digitos += x;
			show(digitos);
			if (x == '.')
				atual = 3;
		}

		public void eval(Operacao s, boolean bin) {
			try {
				valor = Double.parseDouble(digitos);
			} catch (Exception ignorada) {
			}
			if (bin) {
				executar();
				acumulado = valor;
			}
			op = s;
			atual = 2;
			if (!bin) {
				executar();
				atual = 0;
				op = soma;
			}
		}

		public void mem(char x) {
			if (x == 'S') {
				mem = Double.parseDouble(digitos);
			} else if (x == 'R') {
				digitos = Double.toString(mem);
				show(digitos);
			} else if (x == 'C') {
				mem = 0.0;
			} else if (x == 'A') {
				mem += Double.parseDouble(digitos);
			}
			atual = 0;
		}

		public void pct() {
			System.out.println(acumulado + "-" + valor);
			valor = acumulado * Double.parseDouble(visor.getText()) / 100;
			digitos = Double.toString(valor);
			show(digitos);
		}

		public void con(char x) {
			switch (x) {
			case 'P':
				show(Double.toString(Math.PI));
				break;
			case 'E':
				show(Double.toString(Math.E));
			}
		}

		public void clr(boolean e) {
			valor = 0.0;
			digitos = "0";
			show(digitos);

			if (e)
				acumulado = 0.0;

			atual = 0;
		}
	}

	class EscolhendoOperacao implements Estado {
		public void eval() {
			executar();
			atual = 0;
		}

		public void eval(char x) {
			digitos = Character.toString(x);
			atual = 1;
			switch (x) {
			case '.':
				digitos = '0' + digitos;
				atual = 3;
				break;
			case '0':
				atual = 0;
			}
			show(digitos);
		}

		public void eval(Operacao s, boolean bin) {
			op = s;
			if (!bin) {
				executar();
			}
		}

		public void mem(char x) {
			if (x == 'S')
				mem = Double.parseDouble(visor.getText());
			else if (x == 'R') {
				digitos = Double.toString(mem);
				show(digitos);
			} else if (x == 'C')
				mem = 0.0;
			else if (x == 'A')
				mem += Double.parseDouble(visor.getText());
		}

		public void pct() {
			System.out.println(acumulado + "-" + valor);
			valor = acumulado * Double.parseDouble(visor.getText()) / 100;
			digitos = Double.toString(valor);
			show(digitos);
		}

		public void con(char x) {
			switch (x) {
			case 'P':
				show(Double.toString(Math.PI));
				break;
			case 'E':
				show(Double.toString(Math.E));
			}
		}

		public void clr(boolean e) {
			valor = 0.0;
			digitos = "0";
			show(digitos);

			if (e)
				acumulado = 0.0;

			atual = 0;
		}
	}

	class DigitandoDecimal extends DigitandoNumero implements Estado {
		@Override
		public void eval(char x) {
			if (x == '.')
				return;
			digitos += x;
			show(digitos);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String tecla = e.getActionCommand();
		System.out.println(tecla);
		if (tecla.charAt(0) == '$')
			estados[atual].eval(tecla.charAt(1));
		else if (tecla.charAt(0) == '#')
			estados[atual].mem(tecla.charAt(1));
		else if (tecla.equals("SEPARADOR_DECIMAL")) {
			estados[atual].eval('.');
		} else if (tecla.equals("SOMA")) {
			estados[atual].eval(soma, true);
		} else if (tecla.equals("MENOS")) {
			estados[atual].eval(menos, true);
		} else if (tecla.equals("PRODUTO")) {
			estados[atual].eval(produto, true);
		} else if (tecla.equals("DIVISAO")) {
			estados[atual].eval(divisao, true);
		} else if (tecla.equals("POTENCIA")) {
			estados[atual].eval(potencia, true);
		} else if (tecla.equals("SINAL")) {
			estados[atual].eval(sinal, false);
		} else if (tecla.equals("RAIZ_QUADRADA")) {
			estados[atual].eval(raiz, false);
		} else if (tecla.equals("QUADRADO")) {
			estados[atual].eval(quadrado, false);
		} else if (tecla.equals("UMPORX")) {
			estados[atual].eval(umporx, false);
		} else if (tecla.equals("SENO")) {
			estados[atual].eval(seno, false);
		} else if (tecla.equals("COSSENO")) {
			estados[atual].eval(cosseno, false);
		} else if (tecla.equals("TANGENTE")) {
			estados[atual].eval(tangente, false);
		} else if (tecla.equals("FATORIAL")) {
			estados[atual].eval(fatorial, false);
		} else if (tecla.equals("PORCENTAGEM")) {
			estados[atual].pct();
		} else if (tecla.equals("RESULTADO")) {
			estados[atual].eval();
		} else if (tecla.charAt(0) == 'C') {
			estados[atual].clr(tecla.equals("CE"));
		}
	}
}