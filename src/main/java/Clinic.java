public class Clinic extends BaseClinic{
    public Clinic(){ }



    public void triagePatient(String name, int gravity, VisibleSymptoms visibleSymptoms){
        Client newClient = new Client(name, gravity, visibleSymptoms);
        triagePatient(newClient);
    }

    public void triagePatient(Client newClient){
        lstDoctorTriage.add(newClient);

        if (newClient.getVisibleSymptoms() == VisibleSymptoms.BROKEN_BONE || newClient.getVisibleSymptoms() == VisibleSymptoms.SPRAIN)
        {
            lstRadiologyTriage.add(newClient);
        }
    }


    public enum TriageType{
        FIFO
    }

    public enum VisibleSymptoms{
        COLD,
        FLU,
        EBOLA,
        BROKEN_BONE,
        CHEST_PAIN,
        MIGRAINE,
        SPRAIN
    }
}
