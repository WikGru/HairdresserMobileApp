package com.example.grudzina_bakowski.Fryzjer.UmowWizyteFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.grudzina_bakowski.Fryzjer.Entity.UmowWizyteEncja;
import com.example.grudzina_bakowski.Fryzjer.Entity.Visit;
import com.example.grudzina_bakowski.Fryzjer.R;
import com.example.grudzina_bakowski.Fryzjer.SQLITE.SQLiteCRUD;
import com.example.grudzina_bakowski.Fryzjer.VisitUserViewActivities.VisitUserUmowWizyteActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UmowWizyteDateSelect extends Fragment {

    private Button nextButton;
    private NumberPicker numberPicker;
    private Button wybierzgodzine;
    private String[] hoursAvailable;
    private String godzina = "NaN";
    private String date;
    private CalendarView calendar;
    private Calendar calendarHelper;

    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;

    View myFragView;
    SimpleDateFormat sdf;

    private String[] hoursAll = {
            "10:00", "10:15", "10:30", "10:45",
            "11:00", "11:15", "11:30", "11:45",
            "12:00", "12:15", "12:30", "12:45",
            "13:00", "13:15", "13:30", "13:45",
            "14:00", "14:15", "14:30", "14:45",
            "15:00", "15:15", "15:30", "15:45",
            "16:00", "16:15", "16:30", "16:45",
            "17:00", "17:15", "17:30", "17:45",
            "18:00", "18:15", "18:30", "18:45",
            "19:00", "19:15", "19:30", "19:45"};

    private String[] hoursMonWen = {
            "10:00", "10:15", "10:30", "10:45",
            "11:00", "11:15", "11:30", "11:45",
            "12:00", "12:15", "12:30", "12:45",
            "13:00", "13:15", "13:30", "13:45",
            "14:00", "14:15", "14:30", "14:45",
            "15:00", "15:15", "15:30", "15:45",
            "16:00", "16:15", "16:30", "16:45",
            "17:00", "17:15", "17:30", "17:45"};

    private String[] hoursThuFri = {
            "12:00", "12:15", "12:30", "12:45",
            "13:00", "13:15", "13:30", "13:45",
            "14:00", "14:15", "14:30", "14:45",
            "15:00", "15:15", "15:30", "15:45",
            "16:00", "16:15", "16:30", "16:45",
            "17:00", "17:15", "17:30", "17:45",
            "18:00", "18:15", "18:30", "18:45",
            "19:00", "19:15", "19:30", "19:45"};

    private String[] hoursSat = {
            "9:00", "9:15", "9:30", "9:45",
            "10:00", "10:15", "10:30", "10:45",
            "11:00", "11:15", "11:30", "11:45",
            "12:00", "12:15", "12:30", "12:45"};

    private String[] hoursSun = {};

    private String[] hours;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        myFragView = inflater.inflate(R.layout.fragment_umow_wizyte_date_select, container, false);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        wybierzgodzine = myFragView.findViewById(R.id.wybierz_godzine);
        nextButton = myFragView.findViewById(R.id.nextButton2);

        calendarHelper = Calendar.getInstance();
        calendar = myFragView.findViewById(R.id.calendarView2);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                String mon;
                if (month < 10) {
                    mon = "0" + month;
                } else {
                    mon = String.valueOf(month);
                }
                date = year + "-" + mon + "-" + dayOfMonth;
                calendarHelper.clear();
                calendarHelper.set(year, month - 1, dayOfMonth);

                //Assign available hours to selected dayOfWeek
                UpdateHourSelection();
                hoursAvailable = FilterAvailableHours(date).toArray(new String[0]);
//                System.out.println("DATE: " + date + "\t" + calendarHelper.get(Calendar.YEAR) + '-' + calendarHelper.get(Calendar.MONTH) + '-' + calendarHelper.get(Calendar.DAY_OF_MONTH));
            }
        });


        date = sdf.format(new Date(calendar.getDate()));
        calendarHelper.setTime(new Date(calendar.getDate()));
        UpdateHourSelection();

//        System.out.println("DATE: " + date + "\t" + calendarHelper.get(Calendar.YEAR) + '-' + calendarHelper.get(Calendar.MONTH) + '-' + calendarHelper.get(Calendar.DAY_OF_MONTH));


        wybierzgodzine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPicker = new NumberPicker(getActivity());

                if (hoursAvailable.length <= 0) {
                    String[] NaN = {"Brak dostępnych godzin\nSpróbuj w innym terminie"};
                    numberPicker.setMinValue(0);
                    numberPicker.setMaxValue(0);
                    numberPicker.setDisplayedValues(NaN);
                    numberPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                } else {
                    numberPicker.setMinValue(0);
                    numberPicker.setMaxValue(hoursAvailable.length - 1);
                    numberPicker.setDisplayedValues(hoursAvailable);
                    numberPicker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                }

                new AlertDialog.Builder(getContext())
                        .setTitle("Wybierz godzinę")
                        .setView(numberPicker)
                        .setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    godzina = hoursAvailable[numberPicker.getValue()];
                                } catch (Exception e) {
                                    godzina = "NaN";
                                }
                                dialog.cancel();
                                System.out.println(String.valueOf(godzina));
                            }
                        }).show();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UmowWizyteEncja umowWizyteEncja = UmowWizyteEncja.getUmowWizyteEncja(); //singleton
                umowWizyteEncja.setDate(date);
                if (!godzina.equals("NaN")) {
                    umowWizyteEncja.setTimeBeg(godzina);
                    ((VisitUserUmowWizyteActivity) getActivity()).GoToSummary();
                } else{
                    Toast toast = Toast.makeText(myFragView.getContext(),"Wybierz godzinę",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        return myFragView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }

    }

    private List<String> FilterAvailableHours(String date) {
        //FILTERS BASING ON VISITLIST
        SQLiteCRUD sqLiteCRUD = new SQLiteCRUD(myFragView.getContext());
        List<Visit> visitList = sqLiteCRUD.getAllVisits();
        List<String> hoursTaken = new ArrayList<>();
        for (Visit visit : visitList) {
            if (visit.getDate().contains(date)) {
                boolean isDeletingActive = false;
                for (String hour : hoursAvailable) {
                    if (hour.equals(visit.getTimeBeg())) {
                        if (!hoursTaken.contains(hour)) {
                            hoursTaken.add(hour);
                        }
                        isDeletingActive = true;
                    }
                    if (!hour.equals(visit.getTimeEnd()) && isDeletingActive) {
                        if (!hoursTaken.contains(hour)) {
                            hoursTaken.add(hour);
                        }
                    }
                    if (hour.equals(visit.getTimeEnd())) {
                        if (!hoursTaken.contains(hour)) {
                            hoursTaken.add(hour);
                        }
                        break;
                    }
                }
            }
        }
        List<String> filteredHours = new ArrayList<>();
        for (String hour : hoursAvailable) {
            if (!hoursTaken.contains(hour)) filteredHours.add(hour);
        }

        //FILTER BASED ON SELECTED SERVICE
        int timeDuration = Integer.valueOf(sqLiteCRUD.getPrice(UmowWizyteEncja.getUmowWizyteEncja().getService()).getTime());
        int iterations = timeDuration / 15;
        System.out.println("DURATION:\t" + timeDuration + '\t' + iterations);

        List<String> helperHoursToDelete = new ArrayList<>();
        String minutes;
        for (String hour : filteredHours) {
            int[] intedHour = {Integer.valueOf(hour.split(":")[0]), Integer.valueOf(hour.split(":")[1])};
            List<String> sprintHoursToDelete = new ArrayList<>();

            for (int i = 0; i < iterations; i++) {
                if (intedHour[1] == 0) minutes = "00";
                else minutes = String.valueOf(intedHour[1]);
                sprintHoursToDelete.add(intedHour[0] + ":" + minutes);
                if (!filteredHours.contains(intedHour[0] + ":" + minutes)) {
                    for (String sprinter : sprintHoursToDelete) {
                        if (!helperHoursToDelete.contains(sprinter)) {
                            helperHoursToDelete.add(sprinter);
                        }
                    }
                    break;
                }

                if (intedHour[1] >= 45) {
                    intedHour[0]++;
                    intedHour[1] = 0;
                } else {
                    intedHour[1] += 15;
                }
            }
        }
        for (String hour : helperHoursToDelete) {
            if (filteredHours.contains(hour)) {
                filteredHours.remove(hour);
            }
        }

        return filteredHours;
    }

    private void UpdateHourSelection() {
        Calendar todayCalendar = Calendar.getInstance();
        Date todayDate = todayCalendar.getTime();
        String strDate = sdf.format(todayDate);
        int todayYear = Integer.valueOf(strDate.substring(0, 4));
        int todayMonth = Integer.valueOf(strDate.substring(5, 7));
        int todayDay = Integer.valueOf(strDate.substring(8, 10));
        String date = sdf.format(calendarHelper.getTime());
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));
        if (year < todayYear ||
                (year == todayYear && month < todayMonth) ||
                (year == todayYear && month == todayMonth && day <= todayDay)) {
            nextButton.setEnabled(false);
            wybierzgodzine.setEnabled(false);
            wybierzgodzine.getBackground().setAlpha(100);
        } else if (calendarHelper.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ||
                calendarHelper.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY ||
                calendarHelper.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            hoursAvailable = hoursMonWen;
            nextButton.setEnabled(true);
            wybierzgodzine.setEnabled(true);
            wybierzgodzine.getBackground().setAlpha(255);
        } else if (calendarHelper.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY ||
                calendarHelper.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            hoursAvailable = hoursThuFri;
            nextButton.setEnabled(true);
            wybierzgodzine.setEnabled(true);
            wybierzgodzine.getBackground().setAlpha(255);
        } else if (calendarHelper.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            hoursAvailable = hoursSat;
            nextButton.setEnabled(true);
            wybierzgodzine.setEnabled(true);
            wybierzgodzine.getBackground().setAlpha(255);
        } else {
            nextButton.setEnabled(false);
            wybierzgodzine.setEnabled(false);
            wybierzgodzine.getBackground().setAlpha(100);
        }
    }
}
