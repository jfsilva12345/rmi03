package s_gestion_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.CredencialDTO;
import cliente.sop_rmi.AdminCllbckInt;

//Hereda de la clase Remote, lo cual la convierte en interfaz remota
public interface GestionUsuariosInt extends Remote{

    public boolean registrarUsuario(PersonalDTO objUsuario) throws RemoteException; 
    public PersonalDTO consultarUsuario(int id) throws RemoteException;
    public void registrarCallback(AdminCllbckInt objAdmin) throws RemoteException;
    public int abrirSesion(CredencialDTO objCredencial) throws RemoteException;

}