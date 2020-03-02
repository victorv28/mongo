package mongo;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alumno {

    private int id;
    private String nombre;
    private String apellidos;
    private String grupo;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellidos, String grupo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.grupo = grupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", grupo='" + grupo + '\'' +
                '}';
    }

}