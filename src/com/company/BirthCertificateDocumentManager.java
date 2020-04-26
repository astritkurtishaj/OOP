package com.company;

import java.time.LocalDate;

public class BirthCertificateDocumentManager {

    private DocumentManager documentManager;

    public BirthCertificateDocumentManager(DocumentManager manager) {
        this.documentManager = manager;
    }

    public void showMenu() {
        System.out.println("-------------------------------------");
        System.out.println("Type 1 to add new birth certificate");
        System.out.println("Type 2 to print your birth certificate");
        System.out.println("Type 3 to modify data for a person");
        System.out.println("Type 4 to remove certificate");
        System.out.println("Type X to exit");
        System.out.println("-------------------------------------");
        System.out.println("Please choose one of the numbers above:  ");

        String selectedMenu = KeyboardScanner.readString();

        switch (selectedMenu) {
            case "1":
                this.createBirthCertificate();
                break;
            case "2":
                this.findAndPrintCertificate();
                break;
            case "3":
                this.findAndModifyCertificate();
                break;
            case "4":
                this.findAndRemoveCertificate();
                break;
            default: return;
        }
    }

    private void findAndRemoveCertificate() {
        System.out.println("Shenoni numrin personl te personit.");
        var searchPersonalNo = KeyboardScanner.readInteger();

        BirthCertificate certificate = findBirthCertificate(searchPersonalNo);

        if (certificate == null){
            System.out.println("Certifikata e ketij personi nuk eshte gjetur.");
            return;
        }

        documentManager.remove(searchPersonalNo, RemoveType.BIRTH_CERTIFICATE);
        System.out.println("Certifikata e keteij eshte larguar me sukses..");

    }

    private BirthCertificate findBirthCertificate(int personalNo) {
        for (BirthCertificate doc: documentManager.getBirthCertificates()) {

            if (doc.getPerson().getPersonalNo() == personalNo) {
                return doc;
            }
        }

        return null;
    }


    private void findAndModifyCertificate() {

        System.out.println("Shenoni numrin personl te personit.");
        var searchPersonalNo = KeyboardScanner.readInteger();

        BirthCertificate certificate = findBirthCertificate(searchPersonalNo);

        if (certificate == null) {
            System.out.println("Certifikata e keteij personi nuk eshte gjetur.");
            return;
        }

        System.out.println("Shenoni te dhenat e reja te personit. Leni boshe nese nuk deshironi ti nderroni.");

        System.out.println("Shenoni emrin personit");
        var name = KeyboardScanner.readString();

        System.out.println("Shenoni mbiemrin personit");
        var surname = KeyboardScanner.readString();

        System.out.println("Shenoni numrin personal te personit");
        var personalNo = KeyboardScanner.readInteger();

        System.out.println("Shenoni gjinine personit (M ose F):");
        var gender = KeyboardScanner.readString();

        System.out.println("Shenoni nacionalitetin e personit: (KOSOVAR, TURK, BOSHNJAK, ROM, OSE GORAN) ");
        var nationality = KeyboardScanner.readString();

        System.out.println("Shenoni daten e lindjes se personit (yyyy-mm-dd): ");
        String birthdate = KeyboardScanner.readString();

        System.out.println("Shenoni vendin e lindjes se personit: ");
        String birthplace = KeyboardScanner.readString();

        var personi = certificate.getPerson();

        System.out.println("Certifikata eshte bere update me sukses.");

    }

    private void findAndPrintCertificate() {
        System.out.println("Shenoni numrin personl te personit.");
        var searchPersonalNo = KeyboardScanner.readInteger();

        BirthCertificate certificate = findBirthCertificate(searchPersonalNo);

        if (certificate == null) {
            System.out.println("Certifikata e keteij personi nuk eshte gjetur.");
            return;
        }

        System.out.println(certificate);

    }

    private void createBirthCertificate() {
        System.out.println("Shenoni emrin personit");
        var name = KeyboardScanner.validateInputString("Emri i personit eshte gabim, shenoni perseri.");
        //prof keshtu mundet me u validu te dhenat ndoshta me mire qe mos me lan me tajkalu userin
        // pa e marre vleren qe kerkohet.  nuk i kemi ba tjerat kete e kemi ba veq me demonstru

        System.out.println("Shenoni mbiemrin personit");
        var surname = KeyboardScanner.validateInputString("Mbiemri i personit eshte gabim, shenoni perseri.");

        System.out.println("Shenoni numrin personal te personit");
        var personalNo = KeyboardScanner.readInteger();

        System.out.println("Shenoni gjinine personit (M ose F):");
        var gender = KeyboardScanner.readString();

        System.out.println("Shenoni nacionalitetin e personit (KOSOVAR, TURK, BOSHNJAK, ROM, OSE GORAN): ");
        var nationality = KeyboardScanner.readString();

        System.out.println("Shenoni daten e lindjes se personit (yyyy-mm-dd): ");
        String birthdate = KeyboardScanner.readString();

        System.out.println("Shenoni vendin e lindjes se personit: ");
        String birthplace = KeyboardScanner.readString();

        System.out.println("Shenoni emrin e babait: ");
        String father = KeyboardScanner.readString();

        System.out.println("Shenoni emrin e nenes: ");
        String mother = KeyboardScanner.readString();

       try{
           var person = new Person(name,surname,personalNo,GenderBuild.create(gender), Nationality.valueOf(nationality), LocalDate.parse(birthdate), birthplace);

           var certificate = BirthCertificate.GenerateBirthCertificate(person,father,mother);

           documentManager.add(certificate);
           System.out.println("Certificata e lindjes eshte regjistruar me sukses.");
       } catch (Exception ex){
           System.out.println(ex.getMessage());
       }
    }
}
