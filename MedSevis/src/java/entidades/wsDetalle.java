package entidades;

import java.util.ArrayList;
import java.util.List;


public class wsDetalle
{
  private String email;
  
  private String cregional;
  
 
  private String estado;
  
  private String ruta;
  
  private String meddet;
  
  private List<wsEspecialidad> especialidad;

  public wsDetalle() {
  this.especialidad = new ArrayList<wsEspecialidad>();
  }
  
  public String getEmail()
  {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getCregional() {
    return cregional;
  }
  
  public void setCregional(String cregional) {
    this.cregional = cregional;
  }

  public String getEstado() {
    return estado;
  }
  
  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  public String getRuta() {
    return ruta;
  }
  
  public void setRuta(String ruta) {
    this.ruta = ruta;
  }

    public String getMeddet() {
        return meddet;
    }

    public void setMeddet(String meddet) {
        this.meddet = meddet;
    }



    public List<wsEspecialidad> getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(List<wsEspecialidad> especialidad) {
        this.especialidad = especialidad;
    }
  
}

