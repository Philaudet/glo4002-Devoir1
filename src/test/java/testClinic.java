import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class testClinic {

    @Test
    public void testNewClientInQueue(){
        String ClientName = "Donald";

        // Given
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        // When
        clinic.triagePatient(ClientName, 4, VisibleSymptoms.BROKEN_BONE);
        // Then
       Assertions.assertEquals(ClientName, clinic.popFirstClientInDoctorLine().getName());
    }

    @Test
    public void testNewClientWithMigraine(){
        Client client = new Client("Alberto", 4, VisibleSymptoms.MIGRAINE);

        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        clinic.triagePatient(client);

        Assertions.assertEquals(client, clinic.popFirstClientInDoctorLine());
        Assertions.assertNotEquals(client, clinic.popFirstClientInRadiologyLine());
    }

    @Test
    public void testTwoClientsInLine(){
        Client firstClient = new Client("Roberto", 2, VisibleSymptoms.COLD);
        Client secondClient = new Client("Leonardo", 3, VisibleSymptoms.EBOLA);

        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(secondClient);

        Assertions.assertEquals(secondClient, clinic.peekClientInDoctorLine(1));
        Assertions.assertFalse(clinic.isClientInRadiologyLine(secondClient));
    }

    @Test
    public void testClientWithSprainInDoctorAndRadiologyLine(){
        Client client = new Client("Leonardo", 2, VisibleSymptoms.SPRAIN);

        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        clinic.triagePatient(client);

        Assertions.assertEquals(client, clinic.popFirstClientInDoctorLine());
        Assertions.assertEquals(client, clinic.popFirstClientInRadiologyLine());
    }

    @Test
    public void testNewClientWithHigherPriorityThanTheFirstOneInDoctorLine(){
        Client firstClient = new Client("Alejandro", 3, VisibleSymptoms.EBOLA);
        Client highPriorityClient = new Client("Leonardo", 7, VisibleSymptoms.FLU);

        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInDoctorLine());
    }

    @Test
    public void testNewClientWithHigherPriorityAlsoNeedsLIFORadiology(){
        Client firstClient = new Client("Alejandro", 3, VisibleSymptoms.SPRAIN);
        Client highPriorityClient = new Client("Leonardo", 7, VisibleSymptoms.BROKEN_BONE);

        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInDoctorLine());
        Assertions.assertEquals(highPriorityClient, clinic.peekClientInRadiologyLine(1));
    }

    @Test
    public void testNewClientWithHigherPriorityAlsoNeedsGravityRadiology(){
        Client firstClient = new Client("Alejandro", 3, VisibleSymptoms.SPRAIN);
        Client highPriorityClient = new Client("Leonardo", 7, VisibleSymptoms.BROKEN_BONE);

        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.GRAVITY);
        clinic.triagePatient(firstClient);
        clinic.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInDoctorLine());
        Assertions.assertEquals(highPriorityClient, clinic.popFirstClientInRadiologyLine());
    }

    @Test
    public void testPopFirstClientInEmptyDoctorLine(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);
        Client client = clinic.popFirstClientInDoctorLine();

        Assertions.assertNull(client);
    }

    @Test
    public void testPeekClientInEmptyDoctorLine(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.FIFO);
        Client client = clinic.peekClientInDoctorLine(4);

        Assertions.assertNull(client);
    }

    @Test
    public void testPeekClientInEmptyRadiologyLine(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.GRAVITY);
        Client client = clinic.peekClientInRadiologyLine(2);

        Assertions.assertNull(client);
    }

    @Test
    public void testNewCommunityCenterFIFOClientWithLowPriorityFirstInLine(){
        Client client = new Client("Bob", 2);

        CommunityCenter communityCenter = new CommunityCenter(TriageType.FIFO);
        communityCenter.triagePatient(client);

        Assertions.assertEquals(client, communityCenter.popFirstClientInLine());
    }

    @Test
    public void testNewCommunityCenterClientFIFOClientWithHighPriorityAfterLowProprity(){
        Client firstClient = new Client("Bob", 2);
        Client highPriorityClient = new Client("Bob", 2);

        CommunityCenter communityCenter = new CommunityCenter(TriageType.FIFO);
        communityCenter.triagePatient(firstClient);
        communityCenter.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, communityCenter.peekClientInLine(1));
    }

    @Test
    public void testNewCommunityCenterGravityClientHighPriorityBeforeFirstLowPriorityClient(){
        Client highPriorityClient = new Client("Ronaldo", 7);

        CommunityCenter communityCenter = new CommunityCenter(TriageType.GRAVITY);
        communityCenter.triagePatient("Bob", 2);
        communityCenter.triagePatient(highPriorityClient);

        Assertions.assertEquals(highPriorityClient, communityCenter.popFirstClientInLine());
    }

    @Test
    public void testNewCommunityCenterEmptyLineReturnsNull(){
        CommunityCenter communityCenter = new CommunityCenter(TriageType.GRAVITY);
        Client client = communityCenter.popFirstClientInLine();

        Assertions.assertNull(client);
    }

    @Test
    public void testPeekClientInEmptyLineReturnsNull(){
        CommunityCenter communityCenter = new CommunityCenter(TriageType.FIFO);
        Client client = communityCenter.peekClientInLine(2);

        Assertions.assertNull(client);
    }

    @Test
    public void testClientWithGravity1IsNotAddedToDoctorLine(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        Client client = new Client("Ginette", 1, VisibleSymptoms.COLD);

        clinic.triagePatient(client);

        Assertions.assertNull(clinic.popFirstClientInDoctorLine());
    }

    @Test
    public void testClientWithGravity1IsNotAddedToRadiologyLine(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.GRAVITY);
        Client client = new Client("Ginette", 1, VisibleSymptoms.SPRAIN);

        clinic.triagePatient("George", 7, VisibleSymptoms.BROKEN_BONE);
        clinic.triagePatient(client);

        Assertions.assertFalse(clinic.isClientInRadiologyLine(client));
    }

    @Test
    public void testClientWithGravity1IsNotAddedToNurseList(){
        CommunityCenter communityCenter = new CommunityCenter(TriageType.GRAVITY);
        Client client = new Client("Ginette", 1);

        communityCenter.triagePatient(client);

        Assertions.assertNull(communityCenter.popFirstClientInLine());
    }
}














