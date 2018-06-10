package com.pharmacy.service.demo;

import com.pharmacy.entity.*;
import com.pharmacy.service.MedicationService;
import com.pharmacy.service.PharmaciesMedicationsService;
import com.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.pharmacy.service.ChainService;

import java.sql.Time;
import java.util.*;

@Component
public class ServiceMain {

    @Autowired
    private ChainService chainService;
    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private PharmaciesMedicationsService pharmaciesMedicationsService;

    public static void main(String[] args) throws InterruptedException {
        /*Chain chain = new Chain();
        chain.setName("New");
        chain.setEmployees(50);
        chain.setYear(2000);
        chainService.create(chain);*/
        System.out.println("test");
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring/root-context.xml");
        System.out.println("ctx>>"+ctx);
        ServiceMain myDemo=ctx.getBean(ServiceMain.class);
        System.out.println(myDemo);
        myDemo.callService(ctx);
    }

    public void callService(ApplicationContext ctx) throws InterruptedException {
        // TODO Auto-generated method stub
        System.out.println("---callService---");
        System.out.println(chainService);
        /*Chain chain = new Chain();
        chain.setName("New");
        chain.setEmployees(50);
        chain.setYear(2000);
        chainService.create(chain);*//*
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setChain(chainService.findById(1));
        pharmacy.setClosingTime(Time.valueOf("23:00:00"));
        pharmacy.setOpeningTime(Time.valueOf("08:00:00"));
        pharmacy.setHouseNum(6);
        pharmacy.setWorkDays("Monday");
        pharmacy.setStreet("Рядова");
        pharmacyService.create(pharmacy);
*//*
        Pharmacy pharmacy = pharmacyService.findByIdInitialized(31);
        List<Medication> medications = pharmacy.getMedications();
        if(medications==null)medications = new ArrayList<>();
        medications.add(medicationService.findById(3));
        pharmacyService.update(pharmacy);
        /*Pharmacy pharmacy = pharmacyService.findByIdInitialized(1);
        List<Medication> medications = pharmacy.getMedications();
        if(medications==null)medications = new ArrayList<>();
        medications.add(medicationService.findById(1));
        pharmacyService.update(pharmacy);*/
       /* Medication medication = medicationService.findById(1);
        System.out.println(medication.getPharmacies());*/
      /* Medication medication = new Medication();
       medication.setName("Куркод3");
       medication.setReleaseForm("Капсули");
       medication.setActiveSub("Кадеїн");
       medicationService.create(medication);*/
       /*Medication medication = medicationService.findByIdInitialized(4);
       List<Pharmacy> pharmacies = medication.getPharmacies();
       if(pharmacies == null) pharmacies = new ArrayList<>();
       pharmacies.add(pharmacyService.findById(10));
       medicationService.update(medication);
       /*pharmacies.add(pharmacyService.findById(27));
        pharmacies.add(pharmacyService.findById(28));
        medicationService.update(medication);*/
        //System.out.println(medicationService.findByLocation("Куркод2", (float) 49.836948, (float) 24.023740).toString());
        //System.out.println(medicationService.findPharmacyAndPrice("Куркод2"));
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<100;i++) {
            Random random = new Random();
            int iNumber = random.nextInt(198) + 32;
            set.add(iNumber);
        }

        Medication medication = medicationService.findByIdInitialized(0);
        float midPrice = 21f;

        Iterator<Integer> integerIterator = set.iterator();
        int count = 0;

        while (integerIterator.hasNext())
        {
            Pharmacy pharmacy = pharmacyService.findById(integerIterator.next());
            //PharmaciesMedications pharmaciesMedications = new PharmaciesMedications(new PharmaciesMedicationsId(pharmacy.getId(), medication.getId()), pharmacy, medication, midPrice);
            if(pharmacy != null)
                pharmaciesMedicationsService.updatePrice(pharmacy, medication, midPrice);
            if(count==10 || count == 20 || count == 30 || count == 40 || count == 50 || count == 60 || count == 70 || count == 80 || count == 90)
                midPrice += 0.45f;
            count++;
            Thread.currentThread().sleep(10);
        }

        //System.out.println(medicationService.findByActiveSubs(medication).toString());
       // pharmaciesMedicationsService.updatePrice(pharmacyService.findById(1), medicationService.findById(1), 10);
         //pharmacyService.findPharmacyAndPrice(3);
        /*Pharmacy pharmacy = pharmacyService.findById(27);
        System.out.println(pharmacy.getId());*/
    }

}
