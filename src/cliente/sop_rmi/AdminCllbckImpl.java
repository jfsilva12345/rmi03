package cliente.sop_rmi;

import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;


public class AdminCllbckImpl extends UnicastRemoteObject implements AdminCllbckInt{

    public AdminCllbckImpl() throws RemoteException{
        super();
    }

    @Override
    public void informarIngreso(String nombreC, int id) throws RemoteException{
        System.out.println("\t EN INFORMAR INGRESO");
        System.out.println("\t El usuario [ "+nombreC+" ] Identificado con id: [ "+id+" ] Ingresó a la aplicación.");
    }

}