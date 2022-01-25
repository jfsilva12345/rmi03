package s_gestion_usuarios.sop_rmi;


import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.utilidades.UtilidadesRegistroC;
import cliente.sop_rmi.AdminCllbckInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

public class GestionUsuariosImpl extends UnicastRemoteObject implements GestionUsuariosInt{
    private ArrayList<PersonalDTO> personal;
    private ArrayList<AdminCllbckInt> listaActivos;
    private AdminCllbckInt objCllBck;

    
    public GestionUsuariosImpl() throws RemoteException{
        super();

        listaActivos=new ArrayList<>();
        this.personal = new ArrayList<>();
        String tipoId = "CC";
        int id = 6536;
        String nombrecompleto = "Josefino Eusebio De las Nieves";
        String ocupacion = "Admin";
        String usuario = "admin12345";
        String clave = "12345678";
        PersonalDTO admin = new PersonalDTO(tipoId, id, nombrecompleto, ocupacion, usuario, clave);
        personal.add(admin);
    }

    @Override
    public boolean registrarUsuario(PersonalDTO objUsuario) throws RemoteException{
        System.out.println("Entrando a registrar usuario");
        boolean bandera=false;
        
        if(personal.size() < 3)
        {            
            bandera=personal.add(objUsuario);
            System.out.println("Usuario registrado: \n \t identificación: " + objUsuario.getId() + ",\n \t  nombres: " + objUsuario.getNombreCompleto());
        }
        
        return bandera;
    }

    @Override
    public PersonalDTO consultarUsuario(int id) throws RemoteException{
        
        System.out.println("Entrando a consultar usuario");
        PersonalDTO objUsuario=null;
        int contador = 0;
        while(contador<personal.size()){
            if(personal.get(contador).getId()==id){
                
                objUsuario=personal.get(contador);

                break;
            }
            contador++;
        }
        return objUsuario;  
    }

    @Override
    public int abrirSesion(CredencialDTO objCredencial) throws RemoteException{
        PersonalDTO tmpPersonalDTO = ocupacionBuscadaCredenciales(objCredencial);

        String ocupacion = tmpPersonalDTO.getOcupacion();
        switch(ocupacion){
            case "Admin":
                return 0;
                
            case "Paf":
           
                objCllBck.informarIngreso(tmpPersonalDTO.getNombreCompleto(),tmpPersonalDTO.getId());
                return 1;
            case "Secretaria":
                objCllBck.informarIngreso(tmpPersonalDTO.getNombreCompleto(),tmpPersonalDTO.getId());
                return 2;
        }
        return -1;
    }
    @Override   
    public void registrarCallback(AdminCllbckInt objAdmin) throws RemoteException{

        objCllBck=objAdmin;

    }


    public boolean usuarioExiste(CredencialDTO objCredencial){
        String tmpUsuario=objCredencial.getUsuario();
        int contador = 0;
        while(contador < personal.size()){

            if(personal.get(contador).getUsuario()==tmpUsuario){
                return true;
            }
            contador++;
        }
        return false;
    }

    public PersonalDTO ocupacionBuscadaCredenciales(CredencialDTO objCredencial){
        if(usuarioExiste(objCredencial)){
            return null;
        }
        String tmpUsuario=objCredencial.getUsuario();
        for (PersonalDTO personalDTO : personal) {
            if(personalDTO.getUsuario().equals(tmpUsuario)){
                PersonalDTO retorno=new PersonalDTO(personalDTO.getTipo_id(), personalDTO.getId(), personalDTO.getNombreCompleto(), personalDTO.getOcupacion(), personalDTO.getUsuario(), personalDTO.getClave());
                return retorno;
            }
        }
        return null;
    }


}
