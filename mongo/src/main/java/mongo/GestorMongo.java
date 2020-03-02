package mongo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import mongo.bdd.BaseDeDatosMongo;

public class GestorMongo {

	public static void main(String[] args) {

		int opcion;

		do {

			System.out.println();
			System.out.println("1. Mostrar todo");
			System.out.println("2. Mostrar alumno");
			System.out.println("3. Crear alumno");
			System.out.println("4. Modificar alumno");
			System.out.println("5. Eliminar alumno");
			System.out.println("6. Salir");

			System.out.println();

			opcion = Select(1, 6);

			switch (opcion) {

			case 1:

				List<String> todos = BaseDeDatosMongo.SearchAll();

				if (todos.isEmpty()) {
					System.out.println();
					System.out.println("No hay alumnos");

				} else {

					for (String a : todos) {
						System.out.println();
						System.out.println(a.toString());
					}
				}
				break;

			case 2:

				String alumno = BaseDeDatosMongo.SearchOne(Id());
				System.out.println(alumno);
				System.out.println();
				break;

			case 3:

				int alumnoNuevo = BaseDeDatosMongo.CreateAlumno(ReturnAlumno());
				System.out.println();

				if (alumnoNuevo != -1) {
					System.out.println("Se ha creado el alumno: ");
				} else {
					System.out.println("Error al crear alumno.");
				}
				break;

			case 4:
				System.out.println("Introduce el ID del alumno");
				BaseDeDatosMongo.ModifyAlumno(Id());
				System.out.println();

			case 5:
				System.out.println("Introduce el ID del alumno");
				BaseDeDatosMongo.DeleteAlumno(Id());

				break;
			}

		} while (opcion != 6);

	}

	public static int Select(int min, int max) {

		Scanner scanner = new Scanner(System.in);
		int num;

		System.out.print("Opción: ");
		try {
			num = scanner.nextInt();
			if (num < min || num > max) {
				System.out.println("Opción no válida");
				num = Select(min, max);
			}

		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Opción no válida");
			num = Select(min, max);
		}
		return num;
	}

	public static int Id() {

		Scanner scanner = new Scanner(System.in);
		int opcion;

		System.out.print("ID: ");
		try {
			opcion = scanner.nextInt();
		} catch (InputMismatchException e) {

			scanner.next();
			System.out.println("ID no válido");
			opcion = Id();
		}
		return opcion;
	}

	public static String Nombre() {

		Scanner scanner = new Scanner(System.in);
		String nombre;

		System.out.print("Nombre: ");
		try {
			nombre = scanner.nextLine();
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Nombre no válido");
			nombre = Nombre();
		}
		return nombre;
	}

	public static String Apellidos() {

		Scanner scanner = new Scanner(System.in);
		String apellidos;

		System.out.print("Apellidos: ");
		try {
			apellidos = scanner.nextLine();
		} catch (InputMismatchException e) {
			scanner.next();
			System.out.println("Apellido no válido");
			apellidos = Apellidos();
		}
		return apellidos;
	}

	public static String Grupo() {

		System.out.println("Grupo:");
		System.out.println("1. DAM");
		System.out.println("2. ASIR");
		System.out.println("3. SMR");

		switch (Select(1, 3)) {
		case 1:
			return "DAM";
		case 2:
			return "ASIR";
		case 3:
			return "SMR";
		}
		return null;
	}

	public static Alumno ReturnAlumno() {
		return new Alumno(0, Nombre(), Apellidos(), Grupo());
	}
}
