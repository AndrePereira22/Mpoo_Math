package Modelo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio  {

	AudioClip acerto,erro,musica;

	public Audio() {

		URL url1 = getClass().getResource("/acerto.wav");
		URL url2 = getClass().getResource("/erro.wav");
		URL url3 = getClass().getResource("/musica.midi");

		acerto= Applet.newAudioClip(url1);
		erro = Applet.newAudioClip(url2);
		musica = Applet.newAudioClip(url3);

		
	}

	public AudioClip getAcerto() {
		return acerto;
	}

	public AudioClip getErro() {
		return erro;
	}

	public AudioClip getMusica() {
		return musica;
	}
	
	
}
