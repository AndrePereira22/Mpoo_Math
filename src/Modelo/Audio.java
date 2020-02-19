package Modelo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio  {

	AudioClip acerto,erro;

	public Audio() {

		URL url1 = getClass().getResource("/acerto.wav");
		URL url2 = getClass().getResource("/erro.wav");

		acerto= Applet.newAudioClip(url1);
		erro = Applet.newAudioClip(url2);

		
	}

	public AudioClip getAcerto() {
		return acerto;
	}

	public AudioClip getErro() {
		return erro;
	}
	
	
}
