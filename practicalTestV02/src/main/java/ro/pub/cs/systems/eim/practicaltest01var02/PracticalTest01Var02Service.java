package ro.pub.cs.systems.eim.practicaltest01var02;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class PracticalTest01Var02Service extends Service {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ProcessingThread pt = new ProcessingThread(this);
        pt.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private class ProcessingThread extends Thread {

        private Context context;

        public ProcessingThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {

            for (int i = 0; i < 5; i++) {
                sendMessage();
                sleep();
            }

        }

        private void sleep() {
            try {
                Thread.sleep(Constants.SLEEP_TIME);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

        private void sendMessage() {
            Intent intent = new Intent();
            Random random = new Random();
            int val = random.nextInt();

            intent.putExtra(Constants.RANDOM_NUMBER, val);
            val = random.nextInt();
            intent.putExtra(Constants.RANDOM_NUMBER, val);
            val = random.nextInt();
            intent.putExtra(Constants.RANDOM_NUMBER, val);
            val = random.nextInt();
            intent.putExtra(Constants.RANDOM_NUMBER, val);

            context.sendBroadcast(intent);
        }

    }
}
