package mongo.bdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import mongo.Alumno;
import mongo.GestorMongo;

public class BaseDeDatosMongo {

	static String uri = "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=falsemongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false";
	static MongoClient mongoClient;
	static MongoClientURI clientURI;
	static DB db;

	public static void conection() {
		clientURI = new MongoClientURI(uri);
		mongoClient = new MongoClient(clientURI);
		db = mongoClient.getDB("mongodb");
	}

	public static List<String> SearchAll() {
		conection();
		DBCollection collection = db.getCollection("alumnos");
		List<String> list = new ArrayList<String>();
		DBCursor cursor = collection.find();
		String todo = "";
		
		while (cursor.hasNext()) {
			todo = cursor.next().toString();
			list.add(todo);
		}
		return list;
	}

	public static String SearchOne(int id) {
		conection();
		DBCollection collection = db.getCollection("alumnos");
		String test = null;
		try {
			DBObject alumno = collection.findOne(new BasicDBObject("ID", id));
			test = alumno.toString();
		} catch (Exception e) {
			System.out.println("Alumno no encontrado");
			SearchOne(id);
		}
		return test;

	}

	public static int CreateAlumno(Alumno alumno) {
		conection();
		DBCollection collection = db.getCollection("alumnos");
		long last = collection.count();

		DBObject document = new BasicDBObject("ID", (int) last + 1);
		document.put("Nombre", alumno.getNombre());
		document.put("Apellido", alumno.getApellidos());
		document.put("Grupo", alumno.getGrupo());
		collection.insert(document);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("ID", alumno.getId());

		if (collection.find(searchQuery) != null) {
			return alumno.getId();
		} else {
			return -1;
		}

	}

	public static void ModifyAlumno(int id) {
		conection();
		DBCollection collection = db.getCollection("alumnos");
		Scanner teclado = new Scanner(System.in);
		int opcion;
		try {
			DBObject uno = collection.findOne(new BasicDBObject("ID", id));
			do {
				System.out.println("Modificar: ");
				System.out.println("1. Nombre");
				System.out.println("2. Apellidos");
				System.out.println("3. Grupo");
				System.out.println("4. Salir al menu");
				opcion = GestorMongo.Select(1, 4);
				switch (opcion) {
				case 1:
				 collection.update(new BasicDBObject().append("ID", id), new BasicDBObject().append("$set", new BasicDBObject().append("Nombre",GestorMongo.Nombre())));
				 System.out.println("Alumno Modificado");
				 System.out.println();
					break;
				case 2:
					 collection.update(new BasicDBObject().append("ID", id), new BasicDBObject().append("$set", new BasicDBObject().append("Nombre",GestorMongo.Apellidos())));
					 System.out.println("Alumno Modificado");
					 System.out.println();
					break;
				case 3:
					 collection.update(new BasicDBObject().append("ID", id), new BasicDBObject().append("$set", new BasicDBObject().append("Nombre",GestorMongo.Grupo())));
					 System.out.println("Alumno Modificado");
					 System.out.println();
					break;
				}
			} while (opcion != 4);

		} catch (Exception e) {
			System.out.println("Alumno no encontrado");
			ModifyAlumno(id);
		}
	}
	
	public static void DeleteAlumno(int id) {

		conection();
		DBCollection collection = db.getCollection("alumnos");
		try {
			collection.remove(new BasicDBObject("ID", id));
			System.out.println("Alumno Eliminado");
		} catch (Exception e) {
			System.out.println("Alumno no encontrado");
			DeleteAlumno(id);
		}

	
	}
}
	
