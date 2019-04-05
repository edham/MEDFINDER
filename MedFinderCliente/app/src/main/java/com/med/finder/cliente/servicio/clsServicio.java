
package com.med.finder.cliente.servicio;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.med.finder.cliente.conexion.ListarRespuestaPreguntaPacienteHTTP;
import com.med.finder.cliente.dao.clsCasosSaludDAO;
import com.med.finder.cliente.dao.clsCitaPacienteDAO;
import com.med.finder.cliente.dao.clsClinicaDAO;
import com.med.finder.cliente.dao.clsClinicaEspecialidadDAO;
import com.med.finder.cliente.dao.clsClinicaSeguroDAO;
import com.med.finder.cliente.dao.clsDoctorDAO;
import com.med.finder.cliente.dao.clsEspecialidadDAO;
import com.med.finder.cliente.dao.clsPreguntaPacienteDAO;
import com.med.finder.cliente.dao.clsRespuestaCasosSaludDAO;
import com.med.finder.cliente.dao.clsRespuestaPreguntaPacienteDAO;
import com.med.finder.cliente.dao.clsSeguroDAO;
import com.med.finder.cliente.dao.clsUsuarioDAO;
import com.med.finder.cliente.entidades.clsCasosSalud;
import com.med.finder.cliente.entidades.clsCitaPaciente;
import com.med.finder.cliente.entidades.clsClinica;
import com.med.finder.cliente.entidades.clsClinicaEspecialidad;
import com.med.finder.cliente.entidades.clsClinicaSeguro;
import com.med.finder.cliente.entidades.clsDoctor;
import com.med.finder.cliente.entidades.clsEspecialidad;
import com.med.finder.cliente.entidades.clsPreguntaPaciente;
import com.med.finder.cliente.entidades.clsRespuestaCasosSalud;
import com.med.finder.cliente.entidades.clsRespuestaPreguntaPaciente;
import com.med.finder.cliente.entidades.clsSeguro;
import com.med.finder.cliente.entidades.clsUsuario;
import com.med.finder.cliente.utilidades.Utilidades;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class clsServicio extends Service {

	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 1000*60;// timer cada minuto
	
        private int contador=1;
        private clsUsuario objUsuario=null;
	
        
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
            super.onCreate();         
            objUsuario=clsUsuarioDAO.Buscar(this.getApplicationContext());            
            if(objUsuario!=null)
            {
                   _startService();
            }
                      else
                onDestroy();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();               
		_shutdownService();           
	}
	
	private void _startService() {
           
                final Context context=this.getApplicationContext();
		timer.scheduleAtFixedRate(
			new TimerTask() {
				public void run() {
                                    
                                    clsPreguntaPaciente objPreguntaPaciente =clsPreguntaPacienteDAO.BuscarXEstado(context);
                                    if(objPreguntaPaciente!=null)
                                    {

                                        ListarRespuestaPreguntaPacienteHTTP listar = new ListarRespuestaPreguntaPacienteHTTP();
                                        listar.execute(objPreguntaPaciente.getInt_id_pregunta_paciente(), clsRespuestaPreguntaPacienteDAO.getMaxId(context, objPreguntaPaciente.getInt_id_pregunta_paciente()));
                                        String respuesta= "0";
                                        try {
                                            respuesta = listar.get();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        }

                                        if(!respuesta.trim().equals("0"))
                                        {
                                             String [] entidad = respuesta.trim().split("\\<+entidad+>");
                                             for(int i=0;i<entidad.length;i++)
                                                clsRespuestaPreguntaPacienteDAO.Agregar(context, new clsRespuestaPreguntaPaciente(entidad[i]));
                                             Notificacion("Nuevas Respuestas",objPreguntaPaciente.getStr_asunto(),1,objPreguntaPaciente.getInt_id_pregunta_paciente());
                                            
                                        }
                                        
                                        String datos="0";///listar.verificaPreguntaPaciente(objPreguntaPaciente.getInt_id_pregunta_paciente());
                                        if(datos.trim().equals("0"))
                                        {
                                            objPreguntaPaciente.setInt_estado(1);
                                            clsPreguntaPacienteDAO.Actualizar(context,objPreguntaPaciente);
                                            Notificacion("Consulta Cerrada",objPreguntaPaciente.getStr_asunto(),1,objPreguntaPaciente.getInt_id_pregunta_paciente());
                                            
                                        }
                                        else
                                        {
                                            if(Utilidades.minutos(objPreguntaPaciente.getDat_inicio())>5)
                                            {
                                               // listar.cancelaPreguntaPaciente(objPreguntaPaciente.getInt_id_pregunta_paciente());
                                                objPreguntaPaciente.setInt_estado(2);
                                                clsPreguntaPacienteDAO.Actualizar(context,objPreguntaPaciente);
                                                Notificacion("Consulta Cerrada por Tiempo",objPreguntaPaciente.getStr_asunto(),1,objPreguntaPaciente.getInt_id_pregunta_paciente());
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                    clsCitaPaciente objCitaPaciente = clsCitaPacienteDAO.BuscarXEstado(context,1);
                                    if(objCitaPaciente!=null)
                                    {
                                        String cita="0";///http.buscarCitaPaciente(objCitaPaciente.getInt_id_cita_paciente());
                                        if(!cita.trim().equals("0"))
                                        {
                                            objCitaPaciente=new clsCitaPaciente(cita.trim());
                                            if(objCitaPaciente.getInt_estado()==1)
                                            {
                                                clsCitaPacienteDAO.Actualizar(context, objCitaPaciente);
                                                 Notificacion("Cita Aceptada",objCitaPaciente.getStr_detalle(),2,0);
                                            }
                                            else if(objCitaPaciente.getInt_estado()==2)
                                            {
                                                clsCitaPacienteDAO.BorrarXId(context, objCitaPaciente.getInt_id_cita_paciente());
                                                Notificacion("Cita Rechazada",objCitaPaciente.getStr_detalle(),2,0);
                                            }
                                            
                                        }
                                        
                                    }
                                    
                                    if(contador%3==0)
                                    {
                                         String casos="0";///http.actualizarData(objUsuario.getInt_id_usuario());
                                        if(!casos.trim().equals("0"))
                                        {
                                                String [] datos = casos.split("\\<+parametro+>");   
                                               clsEspecialidadDAO.Borrar(context);
                                                 if(!datos[0].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[0].trim().split("\\<+entidad+>");
                                                     for(int i=0;i<entidad.length;i++)
                                                         clsEspecialidadDAO.Agregar(context, new clsEspecialidad(entidad[i]));
                                                }
                                                 clsClinicaDAO.Borrar(context);
                                                if(!datos[1].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[1].trim().split("\\<+entidad+>");
                                                     for(int i=0;i<entidad.length;i++)
                                                         clsClinicaDAO.Agregar(context, new clsClinica(entidad[i]));
                                                }
                                                clsDoctorDAO.Borrar(context);
                                                if(!datos[2].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[2].trim().split("\\<+entidad+>");
                                                    // for(int i=0;i<entidad.length;i++)
                                                    //    clsDoctorDAO.Agregar(context, new clsDoctor(entidad[i]));
                                                }
                                                clsClinicaEspecialidadDAO.Borrar(context);
                                                if(!datos[3].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[3].trim().split("\\<+entidad+>");
                                                     for(int i=0;i<entidad.length;i++)
                                                        clsClinicaEspecialidadDAO.Agregar(context, new clsClinicaEspecialidad(entidad[i]));
                                                }
                                              clsSeguroDAO.Borrar(context);
                                                if(!datos[4].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[4].trim().split("\\<+entidad+>");
                                                     for(int i=0;i<entidad.length;i++)
                                                         clsSeguroDAO.Agregar(context,new clsSeguro(entidad[i]) );
                                                }
                                                clsClinicaSeguroDAO.Borrar(context);
                                                if(!datos[5].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[5].trim().split("\\<+entidad+>");
                                                     for(int i=0;i<entidad.length;i++)
                                                         clsClinicaSeguroDAO.Agregar(context, new clsClinicaSeguro(entidad[i]));
                                                }
                                                clsCasosSaludDAO.Borrar(context);
                                                if(!datos[6].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[6].trim().split("\\<+entidad+>");
                                                     for(int i=0;i<entidad.length;i++)
                                                         clsCasosSaludDAO.Agregar(context, new clsCasosSalud(entidad[i]));
                                                }
                                                clsRespuestaCasosSaludDAO.Borrar(context);
                                                 if(!datos[7].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[7].trim().split("\\<+entidad+>");
                                                     for(int i=0;i<entidad.length;i++)
                                                         clsRespuestaCasosSaludDAO.Agregar(context,new clsRespuestaCasosSalud(entidad[i]));
                                                }

                                                if(!datos[8].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[8].trim().split("\\<+entidad+>");
                                                    // for(int i=0;i<entidad.length;i++)
                                                      //   clsRespuestaCasosSaludDAO.Favorito(context,Integer.parseInt(entidad[i].trim()),true);
                                                }
                                                 if(!datos[9].trim().equals("0"))
                                                {
                                                     String [] entidad = datos[9].trim().split("\\<+entidad+>");
                                                   //  for(int i=0;i<entidad.length;i++)
                                                    //     clsDoctorDAO.Favorito(context, Integer.parseInt(entidad[i].trim()), true);
                                                }

                                                Notificacion("Actualizacion Completa","Se actualizo Correctamente",0,0);

                                        }
                                        
                                    }
                                    
                                    
                                   
                                    contador++;
                                    
				}
			},
			0,
			UPDATE_INTERVAL);      
                
                

	}
	
	private void _shutdownService() {            
		if (timer != null) timer.cancel();
                borrarNotificacion();
	}
	
public void borrarNotificacion()
 {
     String ns = Context.NOTIFICATION_SERVICE;
    NotificationManager notManager = 
    (NotificationManager) getSystemService(ns);
    notManager.cancelAll();
 }
public void borrarNotificacionxId(int Id)
 {
     String ns = Context.NOTIFICATION_SERVICE;
    NotificationManager notManager = 
    (NotificationManager) getSystemService(ns);
    notManager.cancel(Id);
 }

public void Notificacion(String Titulo,String Motivo,int id,int idCosulta)
{
    /*
    String ns = Context.NOTIFICATION_SERVICE;
    NotificationManager notManager = 
        (NotificationManager) getSystemService(ns);

    int icono = R.drawable.logo;
    CharSequence textoEstado = Titulo;
    long hora = System.currentTimeMillis();

    Notification notif = 
        new Notification(icono, textoEstado, hora);

    Context contexto = getApplicationContext();
    CharSequence titulo = Titulo;
    CharSequence descripcion = Motivo;
    Intent notIntent=null;
    if(id==0)
    notIntent = new Intent(contexto, MainActivity.class);
    else if(id==1)
    {
        notIntent = new Intent(contexto,MainActivity.class);
        notIntent.putExtra("Id",""+idCosulta);
    }
     else if(id==2)         
        notIntent = new Intent(contexto,MainActivity.class);
    
    PendingIntent contIntent = PendingIntent.getActivity(
                contexto, 0, notIntent, 0);

    notif.setLatestEventInfo(
                contexto, titulo, descripcion, contIntent);

    notif.flags |= Notification.FLAG_AUTO_CANCEL;
    notif.flags |= Notification.FLAG_ONGOING_EVENT;
    notif.defaults |= Notification.DEFAULT_SOUND;


    notManager.notify(id, notif);                    */
}


         
         
    
}
