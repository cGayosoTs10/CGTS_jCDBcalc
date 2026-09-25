package business;

public class Aplicacao implements IAplicacao{
	private float montante;
	
	@Override
	public void calcularRendimento(float aplic, int prazo, float taxa) {
		float i = taxa / 100f;
		this.montante = (float) (aplic * Math.pow(1 + i, prazo));
	}
	
	public float getMontante() {
		return montante;
	}
}
