import java.util.ArrayList;
import java.util.List;

public class Clinic {

    private List<Client> lstDoctorTriage = new ArrayList();
    private List<Client> lstRadiologyTriage = new ArrayList();

    private TriageType doctorTriageType;
    private TriageType radiologyTriageType;

    public Clinic(TriageType DoctortriageType, TriageType radiologyTriageType){
        this.doctorTriageType = DoctortriageType;
        this.radiologyTriageType = radiologyTriageType;
    }

    public void triagePatient(String name, int gravity, VisibleSymptoms visibleSymptoms){
        triagePatient(new Client(name, gravity, visibleSymptoms));
    }

    public void triagePatient(Client newClient){
        if(newClient.getGravity() > 1){
            if(doctorTriageType == TriageType.GRAVITY && newClient.getGravity() > 5)
            {
                lstDoctorTriage.add(0, newClient);
            }
            else {
                lstDoctorTriage.add(newClient);
            }

            if (newClient.getVisibleSymptoms() == VisibleSymptoms.BROKEN_BONE || newClient.getVisibleSymptoms() == VisibleSymptoms.SPRAIN)
            {
                if(radiologyTriageType == TriageType.GRAVITY && newClient.getGravity() > 5)
                {
                    lstRadiologyTriage.add(0, newClient);
                }
                else{
                    lstRadiologyTriage.add(newClient);
                }
            }
        }
    }

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
        if(!lstDoctorTriage.isEmpty() || lstDoctorTriage.size() >= pos){
            return lstDoctorTriage.get(pos);
        }
        else{
            return null;
        }
    }

    public Client peekClientInRadiologyLine(int pos){
        if(!lstRadiologyTriage.isEmpty() || lstRadiologyTriage.size() >= pos){
            return lstRadiologyTriage.get(pos);
        }
        else{
            return null;
        }
    }

    public boolean isClientInDoctorLine(Client client){
        return !(lstDoctorTriage.indexOf(client) == -1);
    }

    public boolean isClientInRadiologyLine(Client client){
        return !(lstRadiologyTriage.indexOf(client) == -1);
    }
}
