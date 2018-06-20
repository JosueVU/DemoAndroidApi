package INEC.MODEL;

import java.util.Date;

public class Usuario {

    public String id_usuario;
    public String nombre_usuario;
    public String apellido1_usuario;
    public String apellido2_usuario;
    public Date fecha_nacimiento_usuario;
    public String codig_usuario;
    public byte[] foto_usuario;
    public String creacion_usuario;
    public Date fecha_creacion_usuario;
    public String modificacion_usuario;
    public Date modificacion_fecha_usuario;
    public String ruta_foto;

    public Usuario() {

    }

    public String getRuta_Foto() {
        return ruta_foto;
    }

    public void setRuta_Foto(String ruta_foto) {
        this.ruta_foto = ruta_foto;
    }

    public String getID_USUARIO() {
        return id_usuario;
    }

    public void setID_USUARIO(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    public void setNombre_Usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido1_Usuario() {
        return apellido1_usuario;
    }

    public void setApellido1_Usuario(String apellido1_usuario) {
        this.apellido1_usuario = apellido1_usuario;
    }

    public String getApellido2_Usuario() {
        return apellido1_usuario;
    }

    public void setApellido2_Usuario(String apellido2_usuario) {
        this.apellido2_usuario = apellido2_usuario;
    }

    public Date getFecha_Nacimiento_Usuario() {
        return fecha_nacimiento_usuario;
    }

    public void setFecha_Nacimiento_Usuario(Date fecha_nacimiento_usuario) {
        this.fecha_nacimiento_usuario = fecha_nacimiento_usuario;
    }

    public String getCodigo_Usuario() {
        return codig_usuario;
    }

    public void setCodigo_Usuario(String codig_usuario) {
        this.codig_usuario = codig_usuario;
    }

    public byte[] getFoto_Usuario() {
        return foto_usuario;
    }

    public void setFoto_Usuario(byte[] foto_usuario) {
        this.foto_usuario = foto_usuario;
    }

    public String getCreacion_Usuario() {
        return creacion_usuario;
    }

    public void setCreacion_Usuario(String creacion_usuario) {
        this.creacion_usuario = creacion_usuario;
    }

    public Date getFecha_Creacion_Usuario() {
        return fecha_creacion_usuario;
    }

    public void setFecha_Creacion_Usuario(Date fecha_creacion_usuario) {
        this.fecha_creacion_usuario = fecha_creacion_usuario;
    }

    public String getModificacion_Usuario() {
        return modificacion_usuario;
    }

    public void setModificacion_Usuario(String modificacion_usuario) {
        this.modificacion_usuario = modificacion_usuario;
    }

    public Date getModificacion_Fecha_Usuario() {
        return modificacion_fecha_usuario;
    }

    public void setModificacion_Fecha_Usuario(Date modificacion_fecha_usuario) {
        this.modificacion_fecha_usuario = modificacion_fecha_usuario;
    }
}