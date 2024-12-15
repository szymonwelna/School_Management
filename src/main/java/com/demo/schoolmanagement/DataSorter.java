package com.demo.schoolmanagement;


import com.demo.schoolmanagement.models.SchoolClass;

import java.util.ArrayList;
import java.util.HashMap;

// Głównym celem tej klasy jest porządkowanie danych w dataHolderze przed zapisem ich do JSON
public class DataSorter {

    private static final DataSorter instance = new DataSorter();

    public static DataSorter getInstance() {
        return instance;
    }

    DataHolder dataHolder = DataHolder.getInstance();

    //region Sortowanie klas po nazwie
    public void sortSchoolClasses() {
        HashMap<Integer, SchoolClass> schoolClasses = dataHolder.getSchoolClasses();

        ArrayList<SchoolClass> sortingList = new ArrayList<>(schoolClasses.values());

        // Sortowanie klas przy pomocy niestandardowego comparatora
        sortingList.sort((schoolClass1, schoolClass2) -> {
            String name1 = schoolClass1.getSchoolClassName();
            String name2 = schoolClass2.getSchoolClassName();

            // Porównanie pierwszych znaków (cyfr)
            int digitComparison = Character.compare(name1.charAt(0), name2.charAt(0));
            if (digitComparison != 0) {
                return digitComparison; // Jeśli cyfry są różne, zwróć wynik porównania cyfr
            }

            // Jeśli cyfry są takie same, porównaj drugi znak (litery)
            return Character.compare(name1.charAt(1), name2.charAt(1));
        });

        // Zaktualizowanie mapy schoolClasses
        int newId = 0;
        schoolClasses.clear();
        for (SchoolClass schoolClass : sortingList) {
            schoolClass.setSchoolClassId(newId);
            schoolClasses.put(newId++, schoolClass);
        }
    }
    //endregion
}
