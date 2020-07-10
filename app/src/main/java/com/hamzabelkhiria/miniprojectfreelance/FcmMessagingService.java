package com.hamzabelkhiria.miniprojectfreelance;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.hamzabelkhiria.miniprojectfreelance.Employer.DashboardEmployer;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.DashboardFreelancer;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

public class FcmMessagingService extends FirebaseMessagingService {
    String type = "";
    SessionHandler session;
    String jobtitle,notiftype,sendertoken;
    int jobid;
    public FcmMessagingService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
super.onMessageReceived(remoteMessage);
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        //Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload1: " + remoteMessage.getData().toString());
            type= "json";
            if (remoteMessage.getData().get("type")!=null && remoteMessage.getData().get("type").equals("message") ){
                notiftype=remoteMessage.getData().get("type");
                sendertoken=remoteMessage.getData().get("sendertoken");
                Log.d(TAG, "notif type: " + remoteMessage.getData().get("type"));
                Log.d(TAG, "sender token: " + sendertoken);

                sendNotification(remoteMessage.getData().toString());
                //sendMessageNotification(remoteMessage.getData().toString());
            }else{
if (remoteMessage.getData().get("jobtitle")!=null){
            jobtitle=remoteMessage.getData().get("jobtitle");

            jobid= Integer.parseInt(remoteMessage.getData().get("jobid"));}
            sendNotification(remoteMessage.getData().toString());
            if (remoteMessage.getNotification() != null) {
                type = "message";
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                Log.d(TAG, "Message data payload2: " + remoteMessage.getNotification().getBody());
                sendNotification(remoteMessage.getNotification().getBody());

            }

        }}

      }



    private void sendNotification(String messageBody){
        String id="",message="",title=""; //messageBody.substring(messageBody.indexOf("jobtitle=") ,messageBody.indexOf("jobid=")-2);;

          session = new SessionHandler(getApplicationContext());
          if (type.equals("json")){
            try {
                JSONObject jsonObject = new JSONObject(messageBody);
                id = jsonObject.getString("id");
                message = jsonObject.getString("message");
                title = jsonObject.getString("title");




            } catch (JSONException e){

            }}else {if (type.equals("message")){
                message = messageBody;
        }
              Intent intent;
        if (session.getUserDetails().getRole().equals("freelancer")){
         intent = new Intent(getApplicationContext(), DashboardFreelancer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("data", "fromoutside");}else{
            intent = new Intent(getApplicationContext(), DashboardEmployer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            if (notiftype!=null && notiftype.contains("message")){
                intent.putExtra("type",notiftype);
                intent.putExtra("sendertoken",sendertoken);
            }else{
            intent.putExtra("jobid", jobid);
            intent.putExtra("jobtitle", jobtitle);}
        }
            PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this);
            notificationBuilder.setContentTitle(getString(R.string.app_name));
            notificationBuilder.setContentText(message);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(soundUri);
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
            notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_launcher));
            notificationBuilder.setAutoCancel(true);
            //Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            //v.vibrate(1000);
              String channelId = "Default";
            notificationBuilder.setContentIntent(pendingIntent);
            NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                  NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
                  notificationManager.createNotificationChannel(channel);
              }
            notificationManager.notify(0,notificationBuilder.build());

        }}



    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
      //  sendRegistrationToServer(token);
         }
}
