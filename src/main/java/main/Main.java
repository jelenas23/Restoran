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
		
		
	}

}