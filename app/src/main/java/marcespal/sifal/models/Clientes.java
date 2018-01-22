package marcespal.sifal.models;

/**
 * Created by oculu on 09/01/2018.
 */

public class Clientes {

    private String Nombre;
    private String Representante;
    private String Ruc;
    private String Email;
    private String Telefono;
    private String Observacion;
    private String Provincia;
    private String Canton;
    private String Direccion;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRepresentante() {
        return Representante;
    }

    public void setRepresentante(String representante) {
        Representante = representante;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String canton) {
        Canton = canton;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
