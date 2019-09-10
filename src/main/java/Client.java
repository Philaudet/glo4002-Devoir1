public class Client {
    public Client(String name, int gravity, Clinic.VisibleSymptoms visibleSymptoms){
        this.name = name;
        this.gravity = gravity;
        this.visibleSymptoms = visibleSymptoms;
    }

    private String name;
    private int gravity;
    private BaseClinic.VisibleSymptoms visibleSymptoms;

    //region Accessors
    public String getName(){
        return name;
    }
    public int getGravity(){
        return gravity;
    }
    public Clinic.VisibleSymptoms getVisibleSymptoms() {
        return visibleSymptoms;
    }

    //public void setName(String name) {
    //    this.name = name;
    //}
    //public void setGravity(int gravity) {
    //    this.gravity = gravity;
    //}
    //public void setVisibleSymptoms(Clinic.VisibleSymptoms visibleSymptoms) {
    //    this.visibleSymptoms = visibleSymptoms;
    //}
    //endregion
}
