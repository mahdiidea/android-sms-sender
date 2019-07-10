# Send You Website SMS message with android device
Server guid (php laravel) :

Create your project on firebase first then add server key and sender Id to project .env file

<pre>
SMS_SERVER_KEY=AIzaSyBotBIPEGCAUL5apDtNatIEMFU7YIUbGZw
SMS_SENDER_ID=825869099757
</pre>

now you can use this method to push message to your android device and then send with sms

<pre>
function send($phone, $text)
{
    $registration_ids = ["your_android_fcm_token"];
    $fields = array(
        "topic" => "message",
        "registration_ids" => $registration_ids,
        "data" => [
            "phone" => $phone,
            "text" => $text
        ]
    );
    $headers = array(
        'Authorization:key=' . env('SMS_SERVER_KEY'),
        'Content-Type:application/json'
    );

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, "https://fcm.googleapis.com/fcm/send");
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
    $result = curl_exec($ch);
    curl_close($ch);

    return $result;
}
</pre>

note: you can get android fcm token by clicking on `CLICK TO COPY FCM TOKEN` button on app main activity
