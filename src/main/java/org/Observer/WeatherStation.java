package org.Observer;

public class WeatherStation {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		weatherData.setMeasurements(80,65,34.4f);
		weatherData.setMeasurements(80,65,34.4f);
		weatherData.setMeasurements(80,65,34.4f);

	}

}
