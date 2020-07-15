package com.example.airportbordingpassapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.airportbordingpassapp.databinding.ActivityMainBinding;
import com.example.airportbordingpassapp.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // Create a data binding instance called mBinding of type ActivityMainBinding
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Set the Content View using DataBindingUtil to the activity_main layout
        /*
         * DataBindUtil.setContentView replaces our normal call of setContent view.
         * DataBindingUtil also created our ActivityMainBinding that we will eventually use to
         * display all of our data.
         */
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Load a BoardingPassInfo object with fake data using FakeDataUtils
        BoardingPassInfo fakeBoardingInfo = FakeDataUtils.generateFakeBoardingPassInfo();

        //  Call displayBoardingPassInfo and pass the fake BoardingInfo instance
        displayBoardingPassInfo(fakeBoardingInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info){
        //  Use mBinding to set the Text in all the textViews using the data in info
        mBinding.textViewPassengerName.setText(info.passengerName);
        //  Use the flightInfo attribute in mBinding below to get the appropriate text Views
        mBinding.flightInfo.textViewOriginAirport.setText(info.originCode);
        mBinding.flightInfo.textViewFlightCode.setText(info.flightCode);
        mBinding.flightInfo.textViewDestinationAirport.setText(info.destCode);

        //  Use a SimpleDateFormat formatter to set the formatted value in time text views
        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

        mBinding.textViewBoardingTime.setText(boardingTime);
        mBinding.textViewDepartureTime.setText(departureTime);
        mBinding.textViewArrivalTime.setText(arrivalTime);

        // Use TimeUnit methods to format the total minutes until boarding
        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding, minutesLessHoursUntilBoarding);

        mBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
        // Use the boardingInfo attribute in mBinding below to get the appropriate text Views
        mBinding.boardingInfo.textViewTerminal.setText(info.departureTerminal);
        mBinding.boardingInfo.textViewGate.setText(info.departureGate);
        mBinding.boardingInfo.textViewSeat.setText(info.seatNumber);


    }
}