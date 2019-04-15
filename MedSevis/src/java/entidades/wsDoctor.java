package entidades;



public class wsDoctor
{

    public wsDoctor(String cmp, String nombres, String paterno, String materno) {
        this.cmp = cmp;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
    }
    
    
  private String cmp;
  

  private String nombres;
  

  private String paterno;
  
   private String materno;

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    @Override
    public String toString() {
        return "Doctor{" + "cmp=" + cmp + ", nombres=" + nombres + ", paterno=" + paterno + ", materno=" + materno + '}';
    }
  



}