module empreinte_c {
	requires junit;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.media;
	requires com.google.gson;
	requires org.junit.jupiter.api;

	exports vue;
	exports controleur;
	exports consoCarbone;

	opens controleur;
	opens vue;
	opens consoCarbone;
}