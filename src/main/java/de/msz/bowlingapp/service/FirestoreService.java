package de.msz.bowlingapp.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.msz.bowlingapp.domain.Event;
import de.msz.bowlingapp.domain.Game;
import de.msz.bowlingapp.domain.Player;

@Service
public class FirestoreService {

	private FirebaseDatabase db;

    private Map<String, Player> players = new HashMap<>();

    private Map<String, Event> events = new HashMap<>();
    private Event lastEvent;

    private Map<String, Game> games = new HashMap<>();

	public Map<String, Player> getPlayers() {
		return Collections.unmodifiableMap(players);
	}
	
	public Map<String, Event> getEvents() {
		return Collections.unmodifiableMap(events);
	}
	
	public Event getLastEvent() {
		return lastEvent;
	}
	
	public Map<String, Game> getGames() {
		return Collections.unmodifiableMap(games);
	}
	
	@PostConstruct
	private void init() throws IOException {
		db = getFirebaseDatabase();
		initPlayers();
		initEvents();
		initGames();
	}

	private FirebaseDatabase getFirebaseDatabase() throws IOException {

		InputStream serviceAccount;

		File firestoreConfFile = new File("src\\\\main\\\\resources\\\\mszbowling-service-account.json");
		if (firestoreConfFile.exists()) {	// local configuration file
			serviceAccount = new FileInputStream(firestoreConfFile);
		} else {							// environment variable FIREBASE_CONFIG for cloud deployment
			String serviceAccountJson = System.getenv("SERVICE_ACCOUNT_JSON");
			serviceAccount = new ByteArrayInputStream(serviceAccountJson.getBytes(StandardCharsets.UTF_8));
		}

		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(credentials)
				.setDatabaseUrl("https://mszbowling.firebaseio.com")
				.build();
		FirebaseApp.initializeApp(options);

		return FirebaseDatabase.getInstance();
	}

	private void initPlayers() {

		db.getReference("spieler").addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
				
				Player player = snapshot.getValue(Player.class);
				player.setId(snapshot.getKey());
				players.put(player.getId(), player);
			}
			
			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void initEvents() {

		db.getReference("termin").addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
				
				Event event = snapshot.getValue(Event.class);
				event.setId(snapshot.getKey());
				events.put(event.getId(), event);
				
				snapshot.getChildren().forEach(eventChild -> {
					if (eventChild.getKey().equals("spieler")) {
						eventChild.getChildren().forEach(p -> {
							event.getPlayers().add(players.get(p.getKey()));
						});
					}
				});
				
				db.getReference("aktuellerTermin").addListenerForSingleValueEvent(new ValueEventListener() {
					
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						// TODO Auto-generated method stub
						String id = (String) snapshot.getValue();
						lastEvent = events.get(id);
					}
					
					@Override
					public void onCancelled(DatabaseError error) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void initGames() {

		db.getReference("spiel").addChildEventListener(new ChildEventListener() {
			
			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
				
				Game game = snapshot.getValue(Game.class);
				game.setId(snapshot.getKey());
				games.put(game.getId(), game);
			}
			
			@Override
			public void onCancelled(DatabaseError error) {
				// TODO Auto-generated method stub
			}
		});
	}
}
