package dragos.com.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button notif_button;
    Button add_button;
    static final int NOTIFICATION_ID = 112;
    NotificationCompat.Builder mBuilder;
    NotificationManager notificationManager;
    private int numMessage = 1;
    NotificationCompat.InboxStyle inboxStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
        setListener();
    }

    void setUi() {
        notif_button = (Button) findViewById(R.id.notif_button);
        add_button = (Button) findViewById(R.id.add);
    }


    void setListener() {
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noticationTwo();
            }
        });


        notif_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progress();
//                notification();
                notificationInboxStyle();
            }
        });


    }

    void progress() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gicapetrecu.ro"));
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        mBuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(MainActivity.this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Wake up")
                        .setContentText("Downloading").setProgress(100, 0, false).setContentTitle("Progress").setOngoing(false).setAutoCancel(true)
                        .setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        new Thread(new Runnable() {

            @Override
            public void run() {

                for (int i = 0; i <= 100; i += 10) {

                    mBuilder.setProgress(100, i, false);
                    notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                mBuilder.setContentText("Download Finish").setProgress(0, 0, false);
                notificationManager.notify(NOTIFICATION_ID, mBuilder.build());


            }
        }).start();


    }

    void notificationInboxStyle() {
        inboxStyle = new NotificationCompat.InboxStyle();
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this).setSmallIcon(R.mipmap.ic_launcher);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String[] events = new String[6];
        events[0] = "Event 0";
        events[1] = "Event 1";
        events[2] = "Event 2";

        inboxStyle.setBigContentTitle("Inbox Style");
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        mBuilder.setStyle(inboxStyle);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }


    void notification() {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gicapetrecu.ro"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Wake up")
                        .setContentText("Haloo Hallo").setProgress(100, 10, false).setContentTitle("Progress").setOngoing(false).setAutoCancel(true)
                        .setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());

    }

    void noticationTwo() {
        if (mBuilder == null) {
            notification();
        } else {
            mBuilder.setContentText("currentText").setNumber(++numMessage);
            notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }


    }

}
