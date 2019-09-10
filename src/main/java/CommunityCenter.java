import java.util.ArrayList;
import java.util.List;

public class CommunityCenter {

    private List<Client> lstNurseTriage = new ArrayList();
    private TriageType triageType;

    public CommunityCenter(TriageType triageType){
        this.triageType = triageType;
    }
    public void triagePatient(String name, int gravity) {
        triagePatient(new Client(name, gravity));
    }

    public void triagePatient(Client newClient) {
        if (newClient.getGravity() > 1) {
            if(triageType == TriageType.GRAVITY && newClient.getGravity() > 5)
            {
                lstNurseTriage.add(0, newClient);
            }
            else {
                lstNurseTriage.add(newClient);
            }
        }
    }

    public Client popFirstClientInLine() {
        if(!lstNurseTriage.isEmpty()){
            return lstNurseTriage.remove(0);
        }
        else{
            return null;
        }
    }

    public Client peekClientInLine(int pos) {
        if(lstNurseTriage.size() >= pos){
            return lstNurseTriage.get(pos);
        }
        else{
            return null;
        }
    }
}
