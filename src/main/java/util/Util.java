package util;

public class Util {
	/**
	 * Retorna a string correspondente aa prioridade a partir do seu número.
	 * 
	 * @param cdPrioridade
	 *            Número constante da prioridade.
	 */
	public static String getPrioridadeString(int cdPrioridade) {
	
		String prioridade = "Indefinido";

		switch (cdPrioridade) {

		case Constantes.DOCUMENTO_PRIORIDADE_NORMAL:
			prioridade = "NORMAL";
			break;
		case Constantes.DOCUMENTO_PRIORIDADE_URGENTE:
			prioridade = "URGENTE";
			break;
		case Constantes.DOCUMENTO_PRIORIDADE_URGENTISSIMO:
			prioridade = "URGENTÍSSIMO";
			break;
		}

		return prioridade;

	}
}
