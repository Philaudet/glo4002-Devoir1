public class Clinic extends BaseClinic{
    public Clinic(TriageType triageType){
        this.triageType = triageType;
    }

    public void triagePatient(String name, int gravity, VisibleSymptoms visibleSymptoms){
        triagePatient(new Client(name, gravity, visibleSymptoms));
    }

    public void triagePatient(Client newClient){
        if(triageType == TriageType.GRAVITY && newClient.getGravity() > 5)
        {
            lstDoctorTriage.add(0, newClient);
        }
        else {
            lstDoctorTriage.add(newClient);
        }


        if (newClient.getVisibleSymptoms() == VisibleSymptoms.BROKEN_BONE || newClient.getVisibleSymptoms() == VisibleSymptoms.SPRAIN)
        {
            lstRadiologyTriage.add(newClient);
        }
    }
}
