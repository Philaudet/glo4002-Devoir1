import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class testClinic {

    @Test
    public void testNewClientInQueue(){
        String ClientName = "Donald";

        // Given
        BaseClinic clinic = new Clinic(BaseClinic.TriageType.FIFO, BaseClinic.TriageType.FIFO);
        // When
        clinic.triagePatient(ClientName, 1, Clinic.VisibleSymptoms.BROKEN_BONE);
        // Then
       Assertions.assertEquals(ClientName, clinic.popFirstClientInDoctorLine().getName());
    }

    @Test
    public void testNewClientWithMigraine(){
        Client client = new Client("Alberto", 4, Clinic.VisibleSymptoms.MIGRAINE);

        BaseClinic clinic = new Clinic(BaseClinic.TriageType.FIFO, BaseClinic.TriageType.FIFO);
        clinic.triagePatient(client);

        Assertions.assertEquals(client, clinic.popFirstClientInDoctorLine());
        Assertions.assertNotEquals(client, clinic.popFirstClientInRadiologyLine());
    }

    @Test
    public void testTwoClientsInLine(){
        Client firstClient = new Client("Roberto", 2, Clinic.VisibleSymptoms.COLD);
        Client secondClient = new Client("Leonardo", 3, Clinic.VisibleSymptoms.EBOLA);

        BaseClinic clinic = new Clinic(BaseClinic.TriageType.FIFO, BaseClinic.TriageType.FIFO);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(secondClient);

        Assertions.assertEquals(secondClient, clinic.peekClientInDoctorLine(1));
        Assertions.assertFalse(clinic.isClientInRadiologyLine(secondClient));
    }

    @Test
    public void testClientWithSprainInDoctorAndRadiologyLine(){
        Client client = new Client("Leonardo", 2, Clinic.VisibleSymptoms.SPRAIN);

        BaseClinic clinic = new Clinic(BaseClinic.TriageType.FIFO, BaseClinic.TriageType.FIFO);
        clinic.triagePatient(client);

        Assertions.assertEquals(client, clinic.popFirstClientInDoctorLine());
        Assertions.assertEquals(client, clinic.popFirstClientInRadiologyLine());
    }

    @Test
    public void testNewClientWithHigherPriorityThanTheFirstOneInDoctorLine(){
        Client firstClient = new Client("Alejandro", 3, BaseClinic.VisibleSymptoms.EBOLA);
        Client highPriorityClient = new Client("Leonardo", 7, BaseClinic.VisibleSymptoms.FLU);

        BaseClinic clinic = new Clinic(BaseClinic.TriageType.GRAVITY, BaseClinic.TriageType.FIFO);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInDoctorLine());
    }

    @Test
    public void testNewClientWithHigherPriorityAlsoNeedsLIFORadiology(){
        Client firstClient = new Client("Alejandro", 3, BaseClinic.VisibleSymptoms.SPRAIN);
        Client highPriorityClient = new Client("Leonardo", 7, BaseClinic.VisibleSymptoms.BROKEN_BONE);

        BaseClinic clinic = new Clinic(BaseClinic.TriageType.GRAVITY, BaseClinic.TriageType.FIFO);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInDoctorLine());
        Assertions.assertEquals(highPriorityClient, clinic.peekClientInRadiologyLine(1));
    }

    @Test
    public void testNewClientWithHigherPriorityAlsoNeedsGravityRadiology(){
        Client firstClient = new Client("Alejandro", 3, BaseClinic.VisibleSymptoms.SPRAIN);
        Client highPriorityClient = new Client("Leonardo", 7, BaseClinic.VisibleSymptoms.BROKEN_BONE);

        BaseClinic clinic = new Clinic(BaseClinic.TriageType.GRAVITY, BaseClinic.TriageType.GRAVITY);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInDoctorLine());
        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInRadiologyLine());
    }
}














