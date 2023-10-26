package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Bankomat {

    ArrayList<Konto> kontoList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    private boolean loggedIN = false;
    private int currentID; // Konto IDt som man loggat in till

    Person user = new Person(); //Skapar användaren som jag vill ska ha alla mynt och sedlar på sig.

    public void run()
    {
        user.generateMoney(); // Genererar antal mynt och sedlar.0-10
        createKonto("Klas","1234");
        createKonto("Birger","4321");
        createKonto("Janne","1337");
        createKonto("InteKlas","12345"); // Denna skapas ej då den har 5 siffrig kod.
        logIn(3);
        menu();

        System.out.println("Ha en bra dag!");
        user.getMoneyInfo();
    }

    public void createKonto(String owner, String kod)
    {
        if(kod.length() == 4)
            kontoList.add(new Konto(owner,kod));
    }


    public void logIn(int tries)
    {
        boolean validID = false;
        int inputID = 0;
        String inputKod;
        while(!loggedIN) {
            int triesLeft = tries;
            System.out.println("Välj konto att logga in på. Skriv kontots ID.");
            for (Konto konton : kontoList) {
                System.out.println("KontoID: " + konton.getId() + ", Ägare: " + konton.getOwner()); //Loopar igenom alla konton som finns att logga in på
            }
            try
            {
                inputID = Integer.parseInt(scanner.nextLine());
                for (Konto inputKonto : kontoList) { //Loopar igenom och kollar om input idt finns kopplat till någon
                    if (inputKonto.getId() == inputID) {
                        validID = true;
                        break;
                    }
                }
                if(!validID)
                    System.out.println("Det där IDt finns inte.");

            }
            catch (Exception e){
                System.out.println("Nu blev de nog något fel! Försök igen.");
            }
            while(validID && triesLeft > 0)
            {
                System.out.println("Skriv nu in den 4-siffriga kod till ID nr: " + inputID);
                inputKod = scanner.nextLine();
                if(inputKod.length() == 4) {
                    if(kontoList.get(inputID).getCode().equals(inputKod)) {
                        loggedIN = true;
                        System.out.println("Inloggad till " + kontoList.get(inputID).getOwner()+"!");
                        currentID = inputID; // SÄTTER VILKET KONTO SOM MAN ÄR INLOGGAD TILL.
                        break;
                    }
                    else {
                        triesLeft--;
                        System.out.println(triesLeft + " Försök kvar!");
                    }
                }
                else System.out.println("4 Siffrig kod skulle det vara.");
            }
        }
    }

    public void menu()
    {
        boolean inSelectionMenu = true;
        while(inSelectionMenu) {
            System.out.println("Skriv 1 för att kolla konto information.");
            System.out.println("Skriv 2 för att sätta in pengar.");
            System.out.println("Skriv 3 för att ta ut pengar.");
            System.out.println("Skriv 4 för att logga ut.");

            try {
                int menuSelection = Integer.parseInt(scanner.nextLine());
                switch (menuSelection) {
                    case 1:
                        System.out.println("Ägare: " + kontoList.get(currentID).getOwner());
                        System.out.println(kontoList.get(currentID).getOwner()+ " har " + kontoList.get(currentID).getKr() + "kr på kontot.");
                        System.out.println();
                        break;
                    case 2:
                        depositMoney(); //Sätt in - Metod
                        break;
                    case 3:
                        withdrawMoney();//TA UT - Metod
                        break;
                    case 4:
                        inSelectionMenu = false;
                        //LÄMNA
                        break;
                    default:
                }
                Thread.sleep(1000);
            }
            catch (Exception e) {
                System.out.println("Ett nummer nästa gång tack <3");
            }
        }

    }

    public void coinsNBills(String moneyType, String toDo)
    {
        System.out.println("Hur många " + moneyType + " vill du " + toDo + "?");
    }
    public void depositMoney()
    {
        int amountDeposit;
        user.getMoneyInfo();

        if(user.getAntalenkronor() > 0)
        {
            coinsNBills("enkronor","sätta in");
            amountDeposit = getIntInput();
            if (amountDeposit <= user.getAntalenkronor() && amountDeposit >= 0)
            {
                kontoList.get(currentID).setKr(kontoList.get(currentID).getKr() + amountDeposit);
                user.setAntalenkronor(user.getAntalenkronor() - amountDeposit);
                System.out.println("Du har " + user.getAntalenkronor() + " kvar på dig.");
            } else System.out.println("Du har inte så många!");
        }


        if(user.getAntalfemkronor() > 0)
        {
            coinsNBills("femkronor","sätta in");
            amountDeposit = getIntInput();
            if (amountDeposit <= user.getAntalfemkronor())
            {
                kontoList.get(currentID).setKr(kontoList.get(currentID).getKr() + amountDeposit*5);
                user.setAntalfemkronor(user.getAntalfemkronor() - amountDeposit);
                System.out.println("Du har " + user.getAntalfemkronor() + " kvar på dig.");
            }   else System.out.println("Du har inte så många!");

        }

        if(user.getAntaltior() > 0)
        {
            coinsNBills("tior","sätta in");
            amountDeposit = getIntInput();
            if (amountDeposit <= user.getAntaltior())
            {
                kontoList.get(currentID).setKr(kontoList.get(currentID).getKr() + amountDeposit*10);
                user.setAntaltior(user.getAntaltior() - amountDeposit);
                System.out.println("Du har " + user.getAntaltior() + " kvar på dig.");
            }   else System.out.println("Du har inte så många!");
        }

        if(user.getAntaltjogolapp() > 0)
        {
            coinsNBills("tjogolappar","sätta in");
            amountDeposit = getIntInput();
            if (amountDeposit <= user.getAntaltjogolapp())
            {
                kontoList.get(currentID).setKr(kontoList.get(currentID).getKr() + amountDeposit*20);
                user.setAntaltjogolapp(user.getAntaltjogolapp() - amountDeposit);
                System.out.println("Du har " + user.getAntaltjogolapp() + " kvar på dig.");
            }   else System.out.println("Du har inte så många!");
        }

        if(user.getAntalfemtiolappar() > 0)
        {
            coinsNBills("femtiolappar","sätta in");
            amountDeposit = getIntInput();
            if (amountDeposit <= user.getAntalfemtiolappar())
            {
                kontoList.get(currentID).setKr(kontoList.get(currentID).getKr() + amountDeposit*50);
                user.setAntalfemtiolappar(user.getAntalfemtiolappar() - amountDeposit);
                System.out.println("Du har " + user.getAntalfemtiolappar() + " kvar på dig.");
            }   else System.out.println("Du har inte så många!");
        }
    }

    public void withdrawMoney()
    {
        int amountWithdraw;
        int optionType;
        boolean active = true;
        while (active)
        {
            System.out.println(kontoList.get(currentID).getKr()+"kr på kontot.");
            System.out.println("Hur mycket vill du ta ut?");
            amountWithdraw = getIntInput();

            if (amountWithdraw <= kontoList.get(currentID).getKr() && amountWithdraw >0)
            {
                System.out.println("Vad vill du ta ut pengarna i?");
                System.out.println("1, Enkronor");
                System.out.println("2, Femkronor");
                System.out.println("3, Tior");
                System.out.println("4, Tjogolappar");
                System.out.println("5, Femtiolappar");
                optionType = getIntInput();
                int remainingAmount;
                switch (optionType){
                    case 1:
                        user.setAntalenkronor(user.getAntalenkronor()+amountWithdraw);
                        System.out.println("Tar nu ut " + amountWithdraw +" stycken!");
                        kontoList.get(currentID).setKr(kontoList.get(currentID).getKr()-amountWithdraw);
                        break;
                    case 2:
                        int amountOfFemkronor = amountWithdraw / 5; // avrundas alltid neråt
                        remainingAmount = amountWithdraw % 5; // de resterande från delat med
                        System.out.println("Tar nu ut " + amountOfFemkronor +" stycken!");
                        user.setAntalfemkronor(user.getAntalfemkronor()+amountOfFemkronor);
                        kontoList.get(currentID).setKr(kontoList.get(currentID).getKr()-(amountWithdraw-remainingAmount));
                        if(remainingAmount > 0)
                        {
                            System.out.println("Resterande " + remainingAmount + "kr behålls kvar på kontot");
                        }
                        break;
                    case 3:
                        int amountOfTior = amountWithdraw / 10; // avrundas alltid neråt
                        remainingAmount = amountWithdraw % 10; // de resterande från delat med
                        System.out.println("Tar nu ut " + amountOfTior +" stycken!");
                        user.setAntaltior(user.getAntaltior()+amountOfTior);
                        kontoList.get(currentID).setKr(kontoList.get(currentID).getKr()-(amountWithdraw-remainingAmount));
                        if(remainingAmount > 0)
                        {
                            System.out.println("Resterande " + remainingAmount + "kr behålls kvar på kontot");
                        }
                        break;
                    case 4:
                        int amountOfTjugor = amountWithdraw / 20; // avrundas alltid neråt
                        remainingAmount = amountWithdraw % 20; // de resterande från delat med
                        System.out.println("Tar nu ut " + amountOfTjugor +" stycken!");
                        user.setAntaltjogolapp(user.getAntaltjogolapp()+amountOfTjugor);
                        kontoList.get(currentID).setKr(kontoList.get(currentID).getKr()-(amountWithdraw-remainingAmount));
                        if(remainingAmount > 0)
                        {
                            System.out.println("Resterande " + remainingAmount + "kr behålls kvar på kontot");
                        }
                        break;
                    case 5:
                        int amountOfFemtiolappar = amountWithdraw / 50; // avrundas alltid neråt
                        remainingAmount = amountWithdraw % 50; // de resterande från delat med
                        System.out.println("Tar nu ut " + amountOfFemtiolappar +" stycken!");
                        user.setAntalfemtiolappar(user.getAntalfemtiolappar()+amountOfFemtiolappar);
                        kontoList.get(currentID).setKr(kontoList.get(currentID).getKr()-(amountWithdraw-remainingAmount));
                        if(remainingAmount > 0)
                        {
                            System.out.println("Resterande " + remainingAmount + "kr behålls kvar på kontot");
                        }
                        break;
                    default:
                        System.out.println("De va inget val...");



                }
                active = false;
            } else System.out.println("Den summan ");
        }


    }
    public int getIntInput()
    {
        int amount = 0;
        boolean active = true;
        while (active)
        {
            try{
                amount = Integer.parseInt(scanner.nextLine());
                active = false;
            }catch (Exception c)
            {
                System.out.println("Försök igen.");
            }
        }
        return amount;
    }
}
