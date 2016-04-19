package br.com.zup;

public class Rover {

	public int x = 0;
	public int y = 0;
	public static int cSuperiorX = 0;
	public static int cSuperiorY = 0;
	public int pontoCardealOndeEsta = 0;
	public String destinoFinal = "";

	public Rover(int x, int y, String pontoCardeal) {
		defineCodigoParaPontosCardeais(pontoCardeal);
		this.x = x;
		this.y = y;

	}

	// Traduz o código do ponto cardeal para uma descrição
	public String traduzDestinoFinal(int ondeEsta) {
		if (ondeEsta == 1) {
			this.destinoFinal = "N";
		}
		if (ondeEsta == 2) {
			this.destinoFinal = "L";
		}
		if (ondeEsta == 3) {
			this.destinoFinal = "S";
		}
		if (ondeEsta == 4) {
			this.destinoFinal = "O";
		}
		return this.destinoFinal;
	}

	// Define um código para cada ponto cardeal, necesssário para cálculo da locomoção
	public void defineCodigoParaPontosCardeais(String n) {
		if (n.equals("N")) {
			this.pontoCardealOndeEsta = 1;
		}
		if (n.equals("S")) {
			this.pontoCardealOndeEsta = 3;
		}
		if (n.equals("L")) {
			this.pontoCardealOndeEsta = 2;
		}
		if (n.equals("O")) {
			this.pontoCardealOndeEsta = 4;
		}
	}

	public void coordenadas(String c) {
		// Verifica cada uma das coordenadas e realiza os cálculos de locomoção
		for (int i = 0; i < c.length(); i++) {
			char f = c.charAt(i);
			switch (f) {
				case 'L':
					if (pontoCardealOndeEsta == 1) {
						pontoCardealOndeEsta = 4;
					} else {
						pontoCardealOndeEsta = pontoCardealOndeEsta - 1;
					}
					break;
				case 'R':
					if (pontoCardealOndeEsta == 4) {
						pontoCardealOndeEsta = 1;
					} else {
						pontoCardealOndeEsta = pontoCardealOndeEsta + 1;
					}
					break;
				case 'M':
					if (pontoCardealOndeEsta == 1) {
						y = y + 1;
					}
					if (pontoCardealOndeEsta == 2) {
						x = x + 1;
					}
					if (pontoCardealOndeEsta == 3) {
						y = y - 1;
					}
					if (this.pontoCardealOndeEsta == 4) {
						x = x - 1;
					}
					break;
			}
			// valida se ultrapassou o limite do quadrante
			boolean valida = (((x > cSuperiorX) || (y > cSuperiorY)) ? true : false);
			// De acordo com a validação exibe uma mensagem mostrando qual foi a coordenada que ultrapassou o limite e a
			// posição da mesma no conjunto de coordenadas passado
			if (valida) {
				System.out.println("A coordenada: " + c.charAt(i) + " da posição: " + i
						+ " ultrapassou os limites do quadrante");
				return;
			}

		}
		// De acordo com o cálculo traduz o código do destino final para uma das descrições L - leste, N - norte, S -
		// sul, O - oeste
		traduzDestinoFinal(pontoCardealOndeEsta);

		// Exibição do destino final
		System.out.println("Coordenada final do rover: " + x + "-" + y + "-" + destinoFinal);

	}

	public static void main(String[] args) {
		// 1 - Entrada com as coodenadas do canto superior direito coordenadasLimiteCantoSupDireito(int x, int y)
		coordenadasLimiteCantoSupDireito(4, 5);

		// 2 - Posição do rover
		Rover r = new Rover(1, 2, "N");
		// 3 - Coordenadas a serem seguidas
		r.coordenadas("LMLMLMLMM");

		Rover r2 = new Rover(3, 3, "L");
		r2.coordenadas("MMRMMRMRRM");

	}

	public static void coordenadasLimiteCantoSupDireito(int x, int y) {
		cSuperiorX = x;
		cSuperiorY = y;
	}

}
