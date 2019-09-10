import java.util.ArrayList;
import java.util.List;

public abstract class BaseClinic {

    protected List<Client> lstDoctorTriage = new ArrayList();
    protected List<Client> lstRadiologyTriage = new ArrayList();

    public abstract void triagePatient(String name, int gravity, Clinic.VisibleSymptoms visibleSymptoms);
    public abstract void triagePatient(Client newClient);

    public Client popFirstClientInDoctorLine(){
        if(!lstDoctorTriage.isEmpty()){
            return lstDoctorTriage.remove(0);
        }
        else{
            return null;
        }
    }

    public Client popFirstClientInRadiologyLine(){
        if(!lstRadiologyTriage.isEmpty()){
            return lstRadiologyTriage.remove(0);
        }
        else{
            return null;
        }
    }

    public Client peekClientInDoctorLine(int pos){
        if(!lstDoctorTriage.isEmpty() || lstDoctorTriage.size() <= pos){
            return lstDoctorTriage.get(pos);
        }
        else{
            return null;
        }
    }

    public Client peekClientInRadiologyLine(int pos){
        if(!lstRadiologyTriage.isEmpty() || lstRadiologyTriage.size() <= pos){
            return lstRadiologyTriage.get(pos);
        }
        else{
            return null;
        }
    }

    public boolean isClientInRadiologyLine(Client client){
        return !(lstRadiologyTriage.indexOf(client) == -1);
    }
}
