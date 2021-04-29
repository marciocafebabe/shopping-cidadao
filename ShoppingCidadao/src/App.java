import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);

		final int qntCandid = 8;

		String[] cpf = new String[qntCandid];
		String[] url = new String[qntCandid];
		float[][] notas = new float[3][qntCandid];
		float[] media = new float[qntCandid];
		int[] vencedor = new int[qntCandid];
		boolean valido = false;
		int l = 0;

		System.out.println("Cadastro dos participantes:");
		for (int i = 0; i < qntCandid; i++) {
			System.out.println("\nPara o Candidato " + (i + 1));

			System.out.println("Digite o CPF: ");
			cpf[i] = leitor.next();

			System.out.println("Digite a URL");
			url[i] = leitor.next();
		}

		System.out.println("\nProcesso de avaliacao:");
		for (int i = 0; i < qntCandid; i++) {
			System.out.println("\nPara o Candidato " + (i + 1));

			for (int j = 0; j < 3; j++) {
				System.out.println("\nDigite a nota do avaliador " + (j + 1) + " (de 1 a 10):");
				notas[j][i] = leitor.nextFloat();

				if (notas[j][i] > 10 || notas[j][i] <= 0) {
					System.out.println("Digite uma nota valida.");
					j--;
				}
			}
			media[i] = (notas[0][i] + notas[1][i] + notas[2][i]) / 3;
		}

		System.out.println("\nResultados: ");
		for (int i = 0; i < qntCandid; i++) {
			System.out.println("\nCandidato " + (i + 1));
			System.out.println("Nota Global: " + media[i]);
			vencedor[i] = -1;
		}

		vencedor[0] = 0;

		for (int i = 1; i < qntCandid; i++) {
			if (media[vencedor[0]] < media[i]) {
				vencedor[0] = i;
			}
		}

		for (int i = 1, j = 0; i < qntCandid; i++) {
			if (media[vencedor[j]] == media[i] && vencedor[j] != i) {
				j++;
				vencedor[j] = i;
			}
		}

		if (vencedor[1] == -1) {
			System.out.println("\nO vencedor foi o integrante " + (vencedor[0] + 1) + "!");
			System.out.println("Site do projeto do vencedor: " + url[vencedor[0]]);

		} else {
			System.out.println("\nHouve um empate nas notas dos participantes!");
			System.out.println("O organizador precisa dar o voto de minerva: \n");

			for (int i = 0; i < qntCandid; i++) {
				if (vencedor[i] > -1) {
					System.out.println("Participante " + (vencedor[i] + 1));
				}
			}

			while (valido == false) {
				System.out.println("\nOrganizador, digite o numero do vencedor (numero valido): ");
				l = (leitor.nextInt() - 1);
				for (int i = 0; i < qntCandid; i++) {
					if (l == vencedor[i]) {
						valido = true;
					}
				}
			}

			System.out.println("\nParabens para o Participante " + (l + 1) + "!");
			System.out.println("Site do projeto do vencedor: " + url[l]);
		}

		leitor.close();
	}
}
