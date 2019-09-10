public class Clinic extends BaseClinic{
    public Clinic(TriageType DoctortriageType, TriageType radiologyTriageType){
        this.doctorTriageType = DoctortriageType;
        this.radiologyTriageType = radiologyTriageType;
    }

    public void triagePatient(String name, int gravity, VisibleSymptoms visibleSymptoms){
        triagePatient(new Client(name, gravity, visibleSymptoms));
    }

    public void triagePatient(Client newClient){
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
