package com.example.prescribed.addMed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class medRecord {

    private static List<Medication> morningMeds = new ArrayList<Medication>(); //500 to 1100
    private static List<Medication> dayMeds = new ArrayList<Medication>(); //1100 to 1700
    private static List<Medication> eveningMeds = new ArrayList<Medication>(); //1700 to 2300
    private static List<Medication> nightMeds = new ArrayList<Medication>(); //2300 to 500


    public static void storeMedication(ArrayList<String> medInfo){
        String name = medInfo.get(0);

        String[] freqStrings = medInfo.get(1).split("~");
        int times = Integer.parseInt(freqStrings[0]);
        Frequency freq;
        if(freqStrings[1].contains("Hour")) freq = Frequency.HOURS;
        else if(freqStrings[1].contains("Day")) freq = Frequency.DAYS;
        else freq = Frequency.WEEKS;

        String[] dateStrings = medInfo.get(2).split("~");
        String[] timeString = dateStrings[0].split(":");
        String[] dateString = dateStrings[1].split("/");
        int hour = Integer.parseInt(timeString[0]);
        int minute = Integer.parseInt(timeString[1]);
        int day = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);

        Date date = new Date(year, month, day, hour, minute);

        String notes = medInfo.get(3);

        Medication medication = new Medication(name, (Date)date.clone(), times, freq, notes);

        int newTime = (hour*100) + minute;

        if(newTime >= 500 && newTime < 1100){
            morningMeds.add(medication);
        }
        else if(newTime >= 1100 && newTime < 1700){
            dayMeds.add(medication);
        }
        else if(newTime >= 1700 && newTime < 2300){
            eveningMeds.add(medication);
        }
        else{
            nightMeds.add(medication);
        }

        if(freq == Frequency.HOURS && times < 24){
            while(true){
                hour += times;

                if(hour > 24) break;

                date.setHours(hour);

                Medication newMed = new Medication(name, (Date)date.clone(), times, freq, notes);

                newTime = (hour*100) + minute;

                if(newTime >= 500 && newTime < 1100){
                    morningMeds.add(newMed);
                }
                else if(newTime >= 1100 && newTime < 1700){
                    dayMeds.add(newMed);
                }
                else if(newTime >= 1700 && newTime < 2300){
                    eveningMeds.add(newMed);
                }
                else{
                    nightMeds.add(newMed);
                }


            }
        }

    }

    public static List<Medication> getMorningMeds() {
        return morningMeds;
    }

    public static List<Medication> getDayMeds() {
        return dayMeds;
    }

    public static List<Medication> getEveningMeds() {
        return eveningMeds;
    }

    public static List<Medication> getNightMeds() {
        return nightMeds;
    }
}
