package servicio;

import entidades.wsDetalle;
import entidades.wsDoctor;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import utilidades.http;

@WebService(serviceName="medico")
public class medico
{
  public medico() {}
  
  @WebMethod(operationName="consultaDoctor")
  public List<wsDoctor> consultaDoctor(@WebParam(name="cmp") String cmp, @WebParam(name="paterno") String paterno, @WebParam(name="materno") String materno,@WebParam(name="nombres") String nombres)
  {
    if (null == cmp)
      cmp = "";
    if (null == paterno)
      paterno = "";
    if (null == materno) {
      materno = "";
    }
    if (null == nombres) {
      nombres = "";
    }
    return http.cosulta(cmp, paterno, materno,nombres);
  }
  
  @WebMethod(operationName="consultaDetalle")
  public wsDetalle consultaDetalle(@WebParam(name="cmp") String cmp)
  {
    if (null == cmp)
      cmp = "";
    
    return http.consultaDetalle(cmp);
  }
//  @WebMethod(operationName="consultaEspecialidad")
//  public List<Especialidad> consultaEspecialidad(@WebParam(name="cmp") String cmp) {
//    String Scmp = cmp;
//    if (null == cmp)
//      Scmp = "";
//    return http.cosultaEspecialidad(Scmp);
//  }
//  
//  @WebMethod(operationName="consultaDetalle")
//  public Detalle consultaDetalle(@WebParam(name="cmp") String cmp) {
//    String Scmp = cmp;
//    if (null == cmp)
//      Scmp = "";
//    return http.consultaDetalle(Scmp);
//  }
}

