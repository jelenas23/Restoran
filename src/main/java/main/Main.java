package main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.cj.xdevapi.JsonValue;

import domen.Konobar;
import domen.Recenzija;
import so.recenzija.UcitajRecenzijeSO;


public class Main {
	
	public static void main(String[] args) {
		
		try(FileWriter file = new FileWriter("recenzije.json")){
			ArrayList<Recenzija> recenzije = new ArrayList<>();
			
			UcitajRecenzijeSO so = new UcitajRecenzijeSO();
			so.izvrsavanje(new Recenzija());
			
			recenzije = so.getLista();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(recenzije);
			
			file.write(json);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://the-mexican-food-db.p.rapidapi.com/"))
					.header("X-RapidAPI-Key", "08a0eddce1msh4ca7338522c4a39p1f0612jsn5151dd4d8b3c")
					.header("X-RapidAPI-Host", "the-mexican-food-db.p.rapidapi.com")
					.method("GET", HttpRequest.BodyPublishers.noBody())
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			 String odgovor = response.body();
			 
			    Gson gson = new GsonBuilder().setPrettyPrinting().create();
			    JsonArray jsonArr = gson.fromJson(odgovor,JsonArray.class);
			    try(FileWriter file = new FileWriter("mexicanFoodAPI.json")){
					
					
			    	for (int i = 0; i < jsonArr.size(); i++) {  
				          Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
				          JsonObject obj = gson1.fromJson(jsonArr.get(i), JsonObject.class);
				          obj.remove("image");
				          file.write(gson1.toJson(obj, JsonObject.class) + "\n");
			              
			        } 
			
				} catch (Exception e) {
					// TODO: handle exception
				}
			     
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}