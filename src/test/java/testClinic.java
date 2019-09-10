import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class testClinic {

    @Test
    public void testNewClientInQueue(){
        String ClientName = "Donald";

        // Given
        BaseClinic clinic = new Clinic();
        // When
        clinic.triagePatient(ClientName, 1, Clinic.VisibleSymptoms.BROKEN_BONE);
        // Then
       Assertions.assertEquals(ClientName, clinic.popFirstClientInDoctorLine().getName());
    }

    @Test
    public void testNewClientWithMigraine(){
        Client client = new Client("Alberto", 4, Clinic.VisibleSymptoms.MIGRAINE);

        BaseClinic clinic = new Clinic();
        clinic.triagePatient(client);

        Assertions.assertEquals(client, clinic.popFirstClientInDoctorLine());
        Assertions.assertNotEquals(client, clinic.popFirstClientInRadiologyLine());
    }

    @Test
    public void testTwoClientsInLine(){
        Client firstClient = new Client("Roberto", 2, Clinic.VisibleSymptoms.COLD);
        Client secondClient = new Client("Leonardo", 3, Clinic.VisibleSymptoms.EBOLA);

        BaseClinic clinic = new Clinic();
        clinic.triagePatient(firstClient);
        clinic.triagePatient(secondClient);

        Assertions.assertEquals(secondClient, clinic.peekClientInDoctorLine(1));
        Assertions.assertFalse(clinic.isClientInRadiologyLine(secondClient));
    }

    @Test
    public void testClientWithSprainInDoctorAndRadiologyLine(){
        Client client = new Client("Leonardo", 2, Clinic.VisibleSymptoms.SPRAIN);

        BaseClinic clinic = new Clinic();
        clinic.triagePatient(client);

        Assertions.assertEquals(client, clinic.popFirstClientInDoctorLine());
        Assertions.assertEquals(client, clinic.popFirstClientInRadiologyLine());
    }
}
